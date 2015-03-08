package views

import util.{ExecutionContextComponent, HtmlAsset, CDNAsset }
import s_mach.concurrent._
import scala.concurrent.Future
import service._

object Body {
  case class Input(
    notUsedFixCompilerIssue: Int,
    implicit val __navbarInput: Navbar.Input,
    implicit val __footerInput: Footer.Input
  )
  object Input {
    type Components =
      ExecutionContextComponent with
      TextService.Component with
      CDNService.Component
    def fetch(components: Components) : Future[Input] = {
      import components._
      async.par.run(
        0.future,
        Navbar.Input.fetch(components),
        Footer.Input.fetch(components)
      ).map((Input.apply _).tupled)
    }
  }
}
