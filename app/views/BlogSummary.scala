package views

import scala.concurrent.Future
import s_mach.concurrent._
import _root_.util.{HtmlAsset, ExecutionContextComponent, CDNAsset}
import service._

object BlogSummary {
  case class Images(
    BlogExample1: CDNAsset,
    BlogExample2: CDNAsset,
    BlogExample3: CDNAsset
  )
  case class Text(
    BlogSummary: HtmlAsset
  )
  case class Input(
    images: Images,
    text: Text
  )
  object Input {
    type Components =
      ExecutionContextComponent with
      TextService.Component with
      CDNService.Component
    def fetch(components: Components) : Future[Input] = {
      import components._
      async.par.run(
        async.par.run(
          CDNAsset('BlogExample1),
          CDNAsset('BlogExample2),
          CDNAsset('BlogExample3)
        ).map((Images.apply _).tupled),
        HtmlAsset("BlogSummary").map(Text.apply)
      ).map((Input.apply _).tupled)
    }
  }
}
