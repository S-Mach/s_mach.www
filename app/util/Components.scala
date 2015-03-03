package util

import service.{CDNService, TextService}

import scala.concurrent.ExecutionContext

trait Components {
  implicit def executionContext: ExecutionContext
  implicit def textService : TextService
  implicit def cdnService: CDNService
}
