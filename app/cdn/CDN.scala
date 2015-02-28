package cdn

import play.api.Play
import util._

object CDN {
  val configKey = "cdn.name-to-url"
  val assetNameToUrl = {
    Play.current.configuration.underlying.getStringMap(configKey)
  }
}
