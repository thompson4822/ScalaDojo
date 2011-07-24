package com.videostore.component.service

import com.videostore.model.{MovieInstance, Customer}
import sun.reflect.generics.reflectiveObjects.NotImplementedException

/** Created on 7/23/11 at 1:08 PM by Steve */
 
trait ICostServiceComponent {
  val costService: ICostService

  trait ICostService {
    def cost(movieInstance: MovieInstance, customer: Customer): Double
  }
}

// How much will rental cost?
trait CostServiceComponent extends ICostServiceComponent {
  this: IRentalServiceComponent =>

  class CostService extends ICostService {
    def cost(movieInstance: MovieInstance, customer: Customer): Double = throw new NotImplementedException
  }
}