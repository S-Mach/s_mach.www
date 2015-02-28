import play.api.mvc._

package object controllers {
  /**
   * A function that will attempt to resolve if a path and asset is valid. If it
   * is valid then Some(Action) is returned otherwise None.
   */
  type AssetResolver = (String,String) => Option[Action[AnyContent]]

  // Used to chain asset resolvers together (i.e. resolver1 orElse resolver2)
  implicit class PimpMyFunction2Option[A,B](val self: A => Option[B]) extends AnyVal {
    def orElse(next: A => Option[B]) : A => Option[B] = { a:A =>
      self(a) orElse next(a)
    }
  }

}
