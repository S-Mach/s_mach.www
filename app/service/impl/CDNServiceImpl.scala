package service.impl

import play.api.Play
import service.CDNService
import util._

class CDNServiceImpl extends CDNService {
  val configKey = "cdn.name-to-url"
  val assetNameToUrl = {
    Play.current.configuration.underlying.getStringMap(configKey)
  }

  override def urlFor(assetName: String): Option[String] =
    assetNameToUrl.get(assetName)
}
