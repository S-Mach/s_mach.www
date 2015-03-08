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
package util

import service.impl.{CDNServiceImpl, TextServiceImpl}
import service.{CDNService, TextService}
import scala.concurrent.{Future, ExecutionContext}
import s_mach.concurrent._

trait Components extends
  ExecutionContextComponent with
  TextService.Component with
  CDNService.Component {
  implicit val executionContext: ExecutionContext
  // Note: this must be a val since it uses a path-dependent type
  implicit val textService : TextService
  implicit val cdnService: CDNService
}

object Components {
  def apply() : Future[Components] = {
    implicit val _executionContext = play.api.libs.concurrent.Execution.Implicits.defaultContext
    val _textService = new TextServiceImpl
    val _cdnService = new CDNServiceImpl
    
    new Components {
      override val executionContext = _executionContext
      override val cdnService = _cdnService
      override val textService = _textService
    }.future
  }
}
