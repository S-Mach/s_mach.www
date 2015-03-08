package views

import scala.concurrent.Future
import s_mach.concurrent._
import _root_.util.{HtmlAsset, ExecutionContextComponent, CDNAsset}
import service._

object Customers {
  case class Images(
    CustomerExample1: CDNAsset,
    CustomerExample2: CDNAsset,
    CustomerExample3: CDNAsset,
    CustomerExample4: CDNAsset,
    CustomerExample5: CDNAsset,
    CustomerExample6: CDNAsset
  )
  case class Text(
    Customers:HtmlAsset
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
          CDNAsset('CustomerExample1),
          CDNAsset('CustomerExample2),
          CDNAsset('CustomerExample3),
          CDNAsset('CustomerExample4),
          CDNAsset('CustomerExample5),
          CDNAsset('CustomerExample6)
        ).map((Images.apply _).tupled),
        HtmlAsset("Customers").map(Text.apply)
      ).map((Input.apply _).tupled)
    }
  }
}
