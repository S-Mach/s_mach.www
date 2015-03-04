/*
                    ,i::,
               :;;;;;;;
              ;:,,::;.
            1ft1;::;1tL
              t1;::;1,
               :;::;               _____       __  ___              __
          fCLff ;:: tfLLC         / ___/      /  |/  /____ _ _____ / /_
         CLft11 :,, i1tffLi       \__ \ ____ / /|_/ // __ `// ___// __ \
         1t1i   .;;   .1tf       ___/ //___// /  / // /_/ // /__ / / / /
       CLt1i    :,:    .1tfL.   /____/     /_/  /_/ \__,_/ \___//_/ /_/
       Lft1,:;:       , 1tfL:
       ;it1i ,,,:::;;;::1tti      s_mach.www
         .t1i .,::;;; ;1tt        Copyright (c) 2015 S-Mach, Inc.
         Lft11ii;::;ii1tfL:       Author: lance.gatlin@gmail.com
          .L1 1tt1ttt,,Li
            ...1LLLL...
*/
package service.impl

import java.io.File
import play.api.Play
import play.utils.UriEncoding
import service.TextService
import service.impl.TextTypeEnum.Asciidoc
import util._
import TextTypeEnum._
import scala.concurrent.Future
import scala.io.Source

class TextServiceImpl extends TextService {
  // TODO: caching

  case class Token(resource: String, textType: TextTypeEnum)

  // Note: order text type enums are tried
  val textTypes = {
    Seq(
      Html,
      Asciidoc,
      PlainText
    )
  }

  override def find(id: String): Future[Option[Token]] = Future.successful {
    def extFor(textType: TextTypeEnum) : String = textType match {
      case Html => ".html"
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

  override def renderHtmlFragment(token: Token): Future[String] = Future.successful {
    val url =
      Play.current
        .resource(token.resource)
        .getOrDie("Failed to find resource")

    import TextTypeEnum._
    token.textType match {
      case Html =>
          Source.fromURL(url).mkString
      case Asciidoc =>
        import sys.process._
        url #> "asciidoc --backend=wordpress -" !!
      case PlainText =>
        s"<p>${Source.fromURL(url).mkString}</p>"
    }

  }

  override def renderText(token: Token): Future[String] = Future.successful {
    val url =
      Play.current
        .resource(token.resource)
        .getOrDie("Failed to find resource")

    import TextTypeEnum._
    token.textType match {
      case Html =>
        // TODO: extract all text within <p>
        throw new UnsupportedOperationException
      case Asciidoc =>
        // TODO: extract all text within <p>
        throw new UnsupportedOperationException
      case PlainText =>
        Source.fromURL(url).mkString
    }

  }
}