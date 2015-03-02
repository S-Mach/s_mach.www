package service

trait TextService {
  type Token

  def find(id: String) : Option[Token]

  def renderHtmlFragment(token: Token) : String
}

