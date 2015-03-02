package service.impl

import java.io.File
import play.api.Play
import play.utils.UriEncoding
import service.TextService
import service.impl.TextTypeEnum.Asciidoc
import util._
import TextTypeEnum._
import scala.io.Source

class TextServiceImpl extends TextService {
  // TODO: caching

  case class Token(resource: String, textType: TextTypeEnum)

  // Note: order text type enums are tried
  val textTypes = {
    Seq(
      Asciidoc,
      PlainText
    )
  }

  override def find(id: String): Option[Token] = {
    def extFor(textType: TextTypeEnum) : String = textType match {
      case Asciidoc => ".asciidoc"
      case PlainText => ".txt"
    }
    def find(fileName: String, textType: TextTypeEnum) : Option[Token] = {
      resourceNameAt("/public/text",fileName)
        .collect {
          case resourceName
            if Play.current.resource(resourceName).nonEmpty
          =>
          Token(resourceName, textType)
        }
    }
    // TODO: find a better way to do this other than just trying different
    // TODO: extensions
    textTypes
      .iterator
      .map(t => find(id + extFor(t), t))
      .collectFirst {
        case Some(token) => token
      }
  }

  private val dblSlashPattern = """//+""".r
  private def resourceNameAt(path: String, file: String): Option[String] = {
    val decodedFile = UriEncoding.decodePath(file, "utf-8")
    def dblSlashRemover(input: String): String = dblSlashPattern.replaceAllIn(input, "/")
    val resourceName = dblSlashRemover(s"/$path/$decodedFile")
    val resourceFile = new File(resourceName)
    val pathFile = new File(path)
    if (!resourceFile.getCanonicalPath.startsWith(pathFile.getCanonicalPath)) {
      None
    } else {
      Some(resourceName)
    }
  }

  override def renderHtmlFragment(token: Token): String = {
    val url =
      Play.current
        .resource(token.resource)
        .getOrDie("Failed to find resource")

    import TextTypeEnum._
    token.textType match {
      case Asciidoc =>
        import sys.process._
        url #> "asciidoc --backend=wordpress -" !!
      case PlainText =>
        s"<p>${Source.fromURL(url).mkString}</p>"
    }

  }
}