import com.typesafe.config.Config
import scala.collection.JavaConverters._

package object util {
  implicit class PimpMyOption[A](val self: Option[A]) extends AnyVal {
    def getOrDie(error: String) : A =
      self.getOrElse(throw new RuntimeException(error))
  }

  implicit class PimpMyConfig(val self: Config) extends AnyVal {
    def getStringMap(configKey: String) : Map[String,String] = {
      val inner = self.getConfig(configKey)
      inner.entrySet().asScala.map { entry =>
        val key = entry.getKey
        // Note: this config.getString will never be null since we are
        // iterating keys
        val url = inner.getString(key)
        (key,url)
      }.toMap
    }
  }
}
