package views

import scala.concurrent.Future
import s_mach.concurrent._
import _root_.util._
import service._

object Navbar {
  case class Images(
    S_MachLogo : CDNAsset,
    EmailIcon : CDNAsset,
    TelephoneIcon : CDNAsset,
    GithubIcon : CDNAsset,
    NavShadow: CDNAsset
  )
  case class Text(
    Phone : TextAsset,
    Email : TextAsset,
    Github : TextAsset,
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
    def fetch(components:Components) : Future[Input] = {
      import components._
      async.par.run(
        async.par.run(
          CDNAsset('S_MachLogo),
          CDNAsset('EmailIcon),
          CDNAsset('TelephoneIcon),
          CDNAsset('GithubIcon),
          CDNAsset('NavShadow)
        ).map((Images.apply _).tupled),
        async.par.run(
          TextAsset("navbar/Phone"),
          TextAsset("navbar/Email"),
          TextAsset("navbar/Github"),
          HtmlAsset("navlinks")
        ).map((Text.apply _).tupled)
      ).map((Input.apply _).tupled)
    }
  }
}
