package controllers

import play.api.mvc.{Action, Controller}
import play.twirl.api.Html
import service.impl.TextServiceImpl

object Text extends Controller {
  // TODO: DI
  val textService = new TextServiceImpl()
  def renderHtml(id: String) = render(id,"html")
  def render(id: String, format: String) = Action {
    textService.find(id) match {
      case Some(token) => Ok(Html(textService.renderHtmlFragment(token)))
      case _ => NotFound(s"Text asset $id not found")
    }
  }
}
