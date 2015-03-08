package views

import scala.concurrent.Future
import s_mach.concurrent._
import _root_.util.{HtmlAsset, ExecutionContextComponent, CDNAsset}
import service._

object Footer {
  case class Images(
    S_MachGearLogo: CDNAsset,
    ButtonToTop: CDNAsset
  )
  case class Text(
    navlinks: HtmlAsset
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
          CDNAsset('S_MachGearLogo),
          CDNAsset('ButtonToTop)
        ).map((Images.apply _).tupled),
        HtmlAsset("navlinks").map(Text.apply)
      ).map((Input.apply _).tupled)
    }
  }
}
