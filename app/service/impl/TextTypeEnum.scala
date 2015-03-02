package service.impl

sealed trait TextTypeEnum
object TextTypeEnum {
  case object Asciidoc extends TextTypeEnum
  case object PlainText extends TextTypeEnum
  case object Html extends TextTypeEnum
  val values = Seq(Asciidoc,PlainText)
}
