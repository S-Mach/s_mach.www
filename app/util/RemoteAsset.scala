package util

import service.CDNService
import s_mach.string._

class RemoteAsset {
  // Note: object class names end in $ by default
  val name: String = {
    this.getClass.getSimpleName
      .dropRight(1)
      .toWords(WordSplitter.CamelCase)
      .map(_.toLowerCase)
      .mkString("-")
  }
  def url(implicit cdn:CDNService): String = {
    cdn.urlFor(name).getOrDie(
      s"Failed to find URL for asset $name!"
    )
  }
}
