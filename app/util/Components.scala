package util

import service.{CDNService, TextService}

trait Components {
  implicit def textService : TextService
  implicit def cdnService: CDNService
}
