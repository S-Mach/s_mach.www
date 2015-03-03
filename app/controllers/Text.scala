package controllers

import play.api.mvc.{Action, Controller}
import play.twirl.api.Html
import scala.concurrent.Future

object Text extends Controller {
  import Application.components._

  def renderHtml(id: String) = render(id,"html")
  def render(id: String, format: String) = Action.async {
    for {
      optToken <- textService.find(id)
      result <- optToken match {
        case Some(token) =>
          for {
            html <- textService.renderHtmlFragment(token)
          } yield Ok(Html(html))
        case _ => Future.successful(NotFound(s"Text asset $id not found"))
      }
    } yield result
  }
}
