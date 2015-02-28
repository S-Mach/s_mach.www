package controllers

import play.api.Play
import util._

object Fonts extends AssetResolverController(
  path = "",
  resolver = {
    RewritePathAssetResolver(
      Play.current.configuration.underlying.getStringMap("fonts.rewritePath")
    ) {
      PublicAssetResolver
    }
  }
)