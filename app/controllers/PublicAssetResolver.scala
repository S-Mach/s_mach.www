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
