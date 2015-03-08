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

import play.twirl.api.Html
import service.TextService

import scala.concurrent.{ExecutionContext, Future}

case class HtmlAsset(id: String, html: Html)

object HtmlAsset {
  def apply(
    id: String
  )(implicit
    execution:ExecutionContext,
    textService:TextService
  ) : Future[HtmlAsset] = {
    val hyphenCase = id.camelToHyphenCase
    for {
      token <- textService.find(hyphenCase).getOrDie(s"Html asset $hyphenCase not found!")
      html <- textService.renderHtmlFragment(token)
    } yield HtmlAsset(id,Html(html))
  }
}

case class TextAsset(id: String, txt: String)

object TextAsset {
  def apply(
    id: String
  )(implicit
    execution:ExecutionContext,
    textService:TextService
  ) : Future[TextAsset] = {
    val hyphenCase = id.camelToHyphenCase
    for {
      token <- textService.find(hyphenCase).getOrDie(s"Text asset $hyphenCase not found!")
      html <- textService.renderText(token)
    } yield TextAsset(id,html)
  }
}
