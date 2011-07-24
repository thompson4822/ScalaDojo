package com.videostore.component.dao

import com.videostore.model.Customer
import sun.reflect.generics.reflectiveObjects.NotImplementedException

/** Created on 7/23/11 at 10:40 AM by Steve */
 
trait ICustomerDaoComponent {
  val customerDao: ICustomerDao

  trait ICustomerDao {
    def findByName(first: String, last: String): Customer
  }
}

trait CustomerDaoComponent extends ICustomerDaoComponent {
  class CustomerDao extends ICustomerDao {
    def findByName(first: String, last: String): Customer = null
  }

}