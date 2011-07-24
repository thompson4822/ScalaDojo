package com.videostore.component.dao

import sun.reflect.generics.reflectiveObjects.NotImplementedException

/** Created on 7/23/11 at 3:13 PM by Steve */
 
trait CommonDao[T] {
  implicit val t: Manifest[T]
  def save(item: T): Unit = throw new NotImplementedException

}