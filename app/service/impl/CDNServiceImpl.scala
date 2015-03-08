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
package service.impl

import play.api.Play
import service.CDNService
import util._

import scala.concurrent.Future

class CDNServiceImpl extends CDNService {
  val configKey = "cdn.name-to-url"
  val assetNameToUrl = {
    Play.current.configuration.underlying.getStringMap(configKey)
  }

  override def urlFor(assetName: String): Future[Option[String]] =
    Future.successful(assetNameToUrl.get(assetName))

  // TODO:
//  override def urlsFor(assetNames: Seq[String]): Future[Seq[(String, String)]] = ???
}
