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
import play.api.mvc._

package object controllers {
  /**
   * A function that will attempt to resolve if a path and asset is valid. If it
   * is valid then Some(Action) is returned otherwise None.
   */
  type AssetResolver = (String,String) => Option[Action[AnyContent]]

  // Used to chain asset resolvers together (i.e. resolver1 orElse resolver2)
  implicit class PimpMyFunction2Option[A,B](val self: A => Option[B]) extends AnyVal {
    def orElse(next: A => Option[B]) : A => Option[B] = { a:A =>
      self(a) orElse next(a)
    }
  }

}
