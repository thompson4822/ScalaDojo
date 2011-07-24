package com.videostore.component.dao

import com.videostore.model.{LateFee, Customer}
import sun.reflect.generics.reflectiveObjects.NotImplementedException

/** Created on 7/24/11 at 10:33 AM by Steve */
 
trait ILateFeeDaoComponent {
  val lateFeeDao: ILateFeeDao

  trait ILateFeeDao {
    def outstandingForCustomer(customer: Customer): Seq[LateFee]
    def allForCustomer(customer: Customer): Seq[LateFee]
    def save(lateFee: LateFee): Unit
  }
}

trait LateFeeDaoComponent extends ILateFeeDaoComponent {
  class LateFeeDao extends ILateFeeDao {
    def outstandingForCustomer(customer: Customer): Seq[LateFee] = throw new NotImplementedException
    def save(lateFee: LateFee): Unit = throw new NotImplementedException
    def allForCustomer(customer: Customer): Seq[LateFee] = throw new NotImplementedException
  }
}