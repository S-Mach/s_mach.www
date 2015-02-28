package controllers

/**
 * A controller for a fixed public path AssetResolverController that is a simply
 * a thin-layer over the default controllers.Assets controller.
 * @param path the fixed path to search for assets
 */
class PublicAssetResolverController(
  path: String
) extends AssetResolverController(
  path = path,
  resolver = PublicAssetResolver
)
