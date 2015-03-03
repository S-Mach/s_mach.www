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

import play.api.mvc._

/**
 * An asset resolver that uses the default Assets controller to resolve assets
 */
object PublicAssetResolver extends AssetResolver {
  override def apply(path: String, assetName: String): Option[Action[AnyContent]] =
  // TODO: figure a way to find if an asset exists OR
  // TODO: figure out a way to test if the result from controllers.Asset is
  // TODO: a failure so that this method can return None properly if asset
  // TODO: doesn't exist
    Some(controllers.Assets.at(path, assetName))
}
