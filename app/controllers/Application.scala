/*
                    ,i::,
               :;;;;;;;
              ;:,,::;.
            1ft1;::;1tL
              t1;::;1,
               :;::;               _____       __  ___              __
          fCLff ;:: tfLLC         / ___/      /  |/  /____ _ _____ / /_
         CLft11 :,, i1tffLi       \__ \ ____ / /|_/ // __ `// ___// __ \
         1t1i   .;;   .1tf       ___/ //___// /  / // /_/ // /__ / / / /
       CLt1i    :,:    .1tfL.   /____/     /_/  /_/ \__,_/ \___//_/ /_/
       Lft1,:;:       , 1tfL:
       ;it1i ,,,:::;;;::1tti      s_mach.www
         .t1i .,::;;; ;1tt        Copyright (c) 2015 S-Mach, Inc.
         Lft11ii;::;ii1tfL:       Author: lance.gatlin@gmail.com
          .L1 1tt1ttt,,Li
            ...1LLLL...
*/
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
