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

import service.CDNService
import s_mach.string._

class RemoteAsset {
  // Note: object class names end in $ by default
  val name: String = {
    this.getClass.getSimpleName
      .dropRight(1)
      .toWords(WordSplitter.CamelCase)
      .map(_.toLowerCase)
      .mkString("-")
  }
  def url(implicit cdn:CDNService): String = {
    cdn.urlFor(name).getOrDie(
      s"Failed to find URL for asset $name!"
    )
  }
}
