package service

trait CDNService {
  def urlFor(assetName: String) : Option[String]
}
