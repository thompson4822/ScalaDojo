package com.videostore.component.service

import sun.reflect.generics.reflectiveObjects.NotImplementedException

/** Created on 7/23/11 at 4:02 PM by Steve */
 
trait ILogServiceComponent {
  val logService: ILogService

  trait ILogService {
    def error(message: String): Unit
  }
}

trait LogServiceComponent extends ILogServiceComponent {
  class LogService extends ILogService {
    def error(message: String): Unit = throw new NotImplementedException
  }
}