package service.impl

sealed trait TextTypeEnum
object TextTypeEnum {
  case object Asciidoc extends TextTypeEnum
  case object PlainText extends TextTypeEnum
  val values = Seq(Asciidoc,PlainText)
}
