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

import play.api.mvc.{AnyContent, Action}

/**
 * An AssetResolver wrapper that allows replacing the path for specified assets
 * with another path for a downstream AssetResolver
 * @param assetNameToPathRewrite lookup possible rewrite path for an asset
 * @param downstream the downstream asset resolver
 */
case class RewritePathAssetResolver(
  assetNameToPathRewrite: Map[String,String]
)(
  downstream: AssetResolver
) extends AssetResolver {
  override def apply(
    path: String,
    assetName: String
  ): Option[Action[AnyContent]] = {
    // TODO: once asset versioning is enabled, the fingerprint will need to be
    // TODO: ignored when looking up path rewrite
    downstream(assetNameToPathRewrite.getOrElse(assetName,path), assetName)
  }
}