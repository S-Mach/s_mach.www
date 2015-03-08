package views

import util.{ExecutionContextComponent, HtmlAsset, CDNAsset }
import s_mach.concurrent._
import scala.concurrent.Future
import service._

object HowsItWork {
  case class Images(
    ScalaMachine : CDNAsset,
    TrainingPlan : CDNAsset,
    Cloud2 : CDNAsset,
    WeRecruitHorizontal : CDNAsset,
    WeRecruitVertical : CDNAsset,
    Mentor : CDNAsset,
    WhiteBkgRibbon : CDNAsset,
    WhiteBkgTrain : CDNAsset,
    Certify : CDNAsset,
    YouInterviewThem : CDNAsset,
    WePlace : CDNAsset
  )
  case class Text(
    WeLearn : HtmlAsset,
    WeBuild : HtmlAsset,
    WeRecruit : HtmlAsset,
    WeMentorTrainCertify: HtmlAsset,
    YouInterviewThem : HtmlAsset,
    WePlace: HtmlAsset
  )
  case class Input(
    images: Images,
    text: Text,
    implicit val __bodyInput: Body.Input,
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
          CDNAsset('ScalaMachine),
          CDNAsset('TrainingPlan),
          CDNAsset('Cloud2),
          CDNAsset('WeRecruitHorizontal),
          CDNAsset('WeRecruitVertical),
          CDNAsset('Mentor),
          CDNAsset('WhiteBkgRibbon),
          CDNAsset('WhiteBkgTrain),
          CDNAsset('Certify),
          CDNAsset('YouInterviewThem),
          CDNAsset('WePlace)
        ).map((Images.apply _).tupled),
        async.par.run(
          HtmlAsset("/hows-it-work",'WeLearn),
          HtmlAsset("/hows-it-work",'WeBuild),
          HtmlAsset("/hows-it-work",'WeRecruit),
          HtmlAsset("/hows-it-work",'WeMentorTrainCertify),
          HtmlAsset("/hows-it-work",'YouInterviewThem),
          HtmlAsset("/hows-it-work",'WePlace)
        ).map((Text.apply _).tupled),
        Body.Input.fetch(components),
        Customers.Input.fetch(components)
      ).map((Input.apply _).tupled)
    }
  }
}
