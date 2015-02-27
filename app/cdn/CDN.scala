package cdn

import play.api.Play
import scala.collection.JavaConverters._
import util._

object CDN {
  val configKey = "cdn.name-to-url"
  val assetNameToUrl = {
    val config = Play.current.configuration.underlying.getConfig(configKey)
    config.entrySet().asScala.map { entry =>
      val key = entry.getKey
      // Note: this config.getString will never be null since we are
      // iterating keys
      val url = config.getString(key)
      (key,url)
    }.toMap
  }
}
