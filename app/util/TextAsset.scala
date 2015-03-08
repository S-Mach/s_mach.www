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

case class HtmlAsset(path: String, id: String, html: Html)

object HtmlAsset {
  def apply(
    path: String,
    id: Symbol
  )(implicit
    execution:ExecutionContext,
    textService:TextService
  ) : Future[HtmlAsset] =
    apply(path, id.name.camelToHyphenCase)

  def apply(
    path: String,
    id: String
  )(implicit
    execution:ExecutionContext,
    textService:TextService
  ) : Future[HtmlAsset] = {
    for {
      token <- textService.find(path,id).getOrDie(s"Html asset $path/$id not found!")
      html <- textService.renderHtmlFragment(token)
    } yield HtmlAsset(path,id,Html(html))
  }
}

case class TextAsset(path: String, id: String, txt: String)

object TextAsset {
  def apply(
    path: String,
    id: Symbol
  )(implicit
    execution:ExecutionContext,
    textService:TextService
  ) : Future[TextAsset] =
    apply(path, id.name.camelToHyphenCase)

  def apply(
    path: String,
    id: String
  )(implicit
    execution:ExecutionContext,
    textService:TextService
  ) : Future[TextAsset] = {
    for {
      token <- textService.find(path,id).getOrDie(s"Text asset $path/$id not found!")
      html <- textService.renderText(token)
    } yield TextAsset(path,id,html)
  }
}
