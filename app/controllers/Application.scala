package controllers

import play.api.mvc.{Action, Controller}
import service.impl.{CDNServiceImpl, TextServiceImpl}
import util.Components

object Application extends Controller {
  implicit val components = new Components {
    override implicit val executionContext =
      play.api.libs.concurrent.Execution.Implicits.defaultContext

    override val textService = new TextServiceImpl
    override val cdnService = new CDNServiceImpl
  }

  def index = Action {
    Ok(views.html.home())
  }

  def howsItWork = Action {
    Ok(views.html.howsItWork())
  }
}
