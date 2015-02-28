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