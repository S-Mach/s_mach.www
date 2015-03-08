package views

import util.{ExecutionContextComponent, HtmlAsset, CDNAsset }
import s_mach.concurrent._
import scala.concurrent.Future
import service._

object Home {
  case class Images(
    NavShadow : CDNAsset,
    Cloud1 : CDNAsset,
    Cloud2 : CDNAsset,
    Recruit : CDNAsset,
    Ribbon : CDNAsset,
    Train : CDNAsset,
    Place : CDNAsset,
    WhyScala : CDNAsset,
    ChooseTheBest : CDNAsset,
    BlogExample1 : CDNAsset,
    BlogExample2 : CDNAsset,
    BlogExample3 : CDNAsset,
    CustomerExample1 : CDNAsset,
    CustomerExample2 : CDNAsset,
    CustomerExample3 : CDNAsset,
    CustomerExample4 : CDNAsset,
    CustomerExample5 : CDNAsset,
    CustomerExample6 : CDNAsset,
    S_MachGearLogo : CDNAsset,
    ButtonToTop : CDNAsset
  )
  case class Text(
    SiteTitle : HtmlAsset,
    WhyScala : HtmlAsset,
    BuildYourScalaTeam : HtmlAsset
  )
  case class Input(
    images: Images,
    text: Text,
    implicit val __bodyInput: Body.Input,
    implicit val __blogSummaryInput: BlogSummary.Input,
    implicit val __customersInput: Customers.Input
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
          CDNAsset('NavShadow),
          CDNAsset('Cloud1),
          CDNAsset('Cloud2),
          CDNAsset('Recruit),
          CDNAsset('Ribbon),
          CDNAsset('Train),
          CDNAsset('Place),
          CDNAsset('WhyScala),
          CDNAsset('ChooseTheBest),
          CDNAsset('BlogExample1),
          CDNAsset('BlogExample2),
          CDNAsset('BlogExample3),
          CDNAsset('CustomerExample1),
          CDNAsset('CustomerExample2),
          CDNAsset('CustomerExample3),
          CDNAsset('CustomerExample4),
          CDNAsset('CustomerExample5),
          CDNAsset('CustomerExample6),
          CDNAsset('S_MachGearLogo),
          CDNAsset('ButtonToTop)
        ).map((Images.apply _).tupled),
        async.par.run(
          HtmlAsset("/home",'SiteTitle),
          HtmlAsset("/home",'WhyScala),
          HtmlAsset("/home",'BuildYourScalaTeam)
        ).map((Text.apply _).tupled),
        Body.Input.fetch(components),
        BlogSummary.Input.fetch(components),
        Customers.Input.fetch(components)
      ).map((Input.apply _).tupled)
    }
  }
}
