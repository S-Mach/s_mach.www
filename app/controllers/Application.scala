package controllers

import play.api.mvc.{Action, Controller}

object Application extends Controller {
  def index = Action {
    Ok(views.html.home())
  }

  def howsItWork = Action {
    Ok(views.html.howsItWork())
  }
}
