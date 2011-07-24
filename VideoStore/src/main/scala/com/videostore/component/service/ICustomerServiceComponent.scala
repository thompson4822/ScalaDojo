package com.videostore.component.service

import com.videostore.component.dao.ICustomerDaoComponent

/** Created on 7/23/11 at 1:26 PM by Steve */
 
trait ICustomerServiceComponent {
  val customerService: ICustomerService

  trait ICustomerService {

  }

}

trait CustomerServiceComponent extends ICustomerServiceComponent {
  this: ICustomerDaoComponent =>

  class CustomerService extends ICustomerService {

  }
}