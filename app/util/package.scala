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
import com.typesafe.config.Config
import scala.collection.JavaConverters._
import s_mach.string._

package object util {
  implicit class PimpMyOption[A](val self: Option[A]) extends AnyVal {
    def getOrDie(error: String) : A =
      self.getOrElse(throw new RuntimeException(error))
  }

  implicit class PimpMyConfig(val self: Config) extends AnyVal {
    def getStringMap(configKey: String) : Map[String,String] = {
      val inner = self.getConfig(configKey)
      inner.entrySet().asScala.map { entry =>
        val key = entry.getKey
        // Note: this config.getString will never be null since we are
        // iterating keys
        val url = inner.getString(key)
        (key,url)
      }.toMap
    }
  }

  implicit class PimpMyString(val self: String) extends AnyVal {
    def camelToHyphenCase : String = {
      self.toWords(WordSplitter.CamelCase)
        .map(_.toLowerCase)
        .mkString("-")
    }
  }
}
