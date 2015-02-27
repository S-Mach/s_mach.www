package models

import scala.collection.JavaConverters._
import play.api.Play
import util._

object Nav {
  val configKey="nav.links"
  val links = {
    def stripQuotes(s: String) : String = {
      // Note: for strings that have whitespace config returns them with quotes
      if(s.head == '"' && s.last == '"') {
        s.drop(1).dropRight(1)
      } else {
        s
      }
    }

    Play.current.configuration.underlying
      .getObjectList(configKey)
      .iterator
      .asScala
      .map { o =>
        val inner = o.toConfig
        val entry = inner.entrySet().asScala.head
        val key = entry.getKey
        val value = inner.getString(key)
        (stripQuotes(key),stripQuotes(value))
      }
      .toSeq
  }
}
