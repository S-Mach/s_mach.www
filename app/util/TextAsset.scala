package util

import play.twirl.api.Html
import service.TextService
import s_mach.string._

class TextAsset {
  // Note: object class names end in $ by default
  val name: String = {
    this.getClass.getSimpleName
      .dropRight(1)
      .toWords(WordSplitter.CamelCase)
      .map(_.toLowerCase)
      .mkString("-")
  }
  def html(implicit textService:TextService): Html = {
    Html(textService.renderHtmlFragment(
      textService.find(name).getOrDie(
        s"Failed to find URL for asset $name!"
      )
    ))
  }
}
