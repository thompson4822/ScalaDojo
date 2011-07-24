package com.videostore.component.dao

import sun.reflect.generics.reflectiveObjects.NotImplementedException

/** Created on 7/24/11 at 7:56 AM by Steve */
 
trait IStorePolicyDaoComponent {
  val storePolicyDao: IStorePolicyDao

  trait IStorePolicyDao {
    def rentalLimit: Int
    def lateFee: Double
    def rentalFee: Double
  }

}

trait StorePolicyDaoComponent extends IStorePolicyDaoComponent {
  class StorePolicyDao extends IStorePolicyDao {
    def rentalLimit: Int = throw new NotImplementedException
    def lateFee: Double = throw new NotImplementedException
    def rentalFee: Double = throw new NotImplementedException
  }
}