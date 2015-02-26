package cdn

class Asset {
  // Note: object class names end in $ by default
  def name: String = this.getClass.getSimpleName.dropRight(1)
  lazy val url: String = {
    CDN.assetNameToUrl.getOrElse(
      name,
      throw new RuntimeException(
        s"Failed to find URL for asset $name in application.conf/${CDN.configKey}!"
      )
    )
  }
}
