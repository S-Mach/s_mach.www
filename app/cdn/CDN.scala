package cdn

import play.api.Play

import scala.collection.JavaConverters._

object CDN {
  val configKey = "cdn.name-to-url"
  val assetNameToUrl = {
    Play.current.configuration.getObject(configKey) match {
      case Some(configObject) =>
        val config = configObject.toConfig
        config.entrySet().asScala.map { entry =>
          val key = entry.getKey
          // Note: this config.getString will never be null since we are
          // iterating keys
          val url = config.getString(key)
          (key,url)
        }.toMap
      case None => throw new RuntimeException(s"Failed to find $configKey in application.conf!")
    }
  }
}
