package util

import play.twirl.api.Html
import service.TextService

class TextAsset(path: String = "") {
  // Note: object class names end in $ by default
  val name: String = {
    s"$path/${
      this.getClass.getSimpleName
        .dropRight(1)
        .camelToHyphenCase
    }"
  }
  def html(implicit textService:TextService): Html = {
    Html(textService.renderHtmlFragment(
      textService.find(name).getOrDie(
        s"Failed to find URL for asset $name!"
      )
    ))
  }
}
