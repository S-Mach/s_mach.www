package controllers

import play.api.mvc.{Action, Controller}
import service.impl.{CDNServiceImpl, TextServiceImpl}
import util.Components

object Application extends Controller {
  implicit val components = new Components {
    override def textService = new TextServiceImpl
    override def cdnService = new CDNServiceImpl
  }

  def index = Action {
    Ok(views.html.home())
  }

  def howsItWork = Action {
    Ok(views.html.howsItWork())
  }
}
