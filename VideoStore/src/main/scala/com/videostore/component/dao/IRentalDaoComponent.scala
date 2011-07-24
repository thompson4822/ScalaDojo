package com.videostore.component.dao

import com.videostore.model.{Customer, Rental}
import sun.reflect.generics.reflectiveObjects.NotImplementedException

/** Created on 7/23/11 at 12:51 PM by Steve */
 
trait IRentalDaoComponent {
  val rentalDao: IRentalDao

  trait IRentalDao {
    def save(rental: Rental): Unit
    def findRentalsBy(customer: Customer): Seq[Rental]
  }

}

trait RentalDaoComponent extends IRentalDaoComponent {
  class RentalDao extends IRentalDao {
    def save(rental: Rental): Unit = throw new NotImplementedException
    def findRentalsBy(customer: Customer): Seq[Rental] = throw new NotImplementedException
  }
}