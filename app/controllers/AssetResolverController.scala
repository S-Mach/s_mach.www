package controllers

import play.api.mvc._

/**
 * A controller that uses an asset resolver to resolve an asset to an Action
 * @param path the fixed path of the asset
 * @param resolver the underlying resolver
 */
class AssetResolverController(
  path: String,
  resolver: AssetResolver
) extends Controller {

  def at(assetName: String) : Action[AnyContent] =
    resolver(path, assetName).getOrElse(Action { Results.NotFound })
}
