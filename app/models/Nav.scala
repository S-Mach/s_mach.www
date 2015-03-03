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
package models

import scala.collection.JavaConverters._
import play.api.Play
import util._

object Nav {
  val configKey="nav.links"
  val links = {
    def stripQuotes(s: String) : String = {
      // Note: for strings that have whitespace config returns them with quotes
      if(s.head == '"' && s.last == '"') {
        s.drop(1).dropRight(1)
      } else {
        s
      }
    }

    Play.current.configuration.underlying
      .getObjectList(configKey)
      .iterator
      .asScala
      .map { o =>
        val inner = o.toConfig
        val entry = inner.entrySet().asScala.head
        val key = entry.getKey
        val value = inner.getString(key)
        (stripQuotes(key),stripQuotes(value))
      }
      .toSeq
  }
}
