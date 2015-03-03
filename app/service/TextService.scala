package service

import scala.concurrent.Future

trait TextService {
  type Token

  def find(id: String) : Future[Option[Token]]

  def renderHtmlFragment(token: Token) : Future[String]
}

