package com.videostore.component.service

import com.videostore.model.{Rental, Customer, Movie}
import sun.reflect.generics.reflectiveObjects.NotImplementedException
import com.videostore.component.dao.{ICustomerDaoComponent, IMovieDaoComponent}

/** Created on 7/23/11 at 9:58 AM by Steve */
 
trait IInventoryServiceComponent {
  val inventoryService: IInventoryService

  trait IInventoryService {
    def rentalCharge(customer: Customer, movie: Movie): Double
    def rent(customer: Customer, movie: Movie): Unit
    def rentalsFor(customer: Customer): Seq[Rental]
  }
}

// The purpose of this service is pretty sketchy in my mind ...
trait InventoryServiceComponent extends IInventoryServiceComponent {
  this: IMovieDaoComponent with ICustomerDaoComponent =>

  class InventoryService extends IInventoryService {
    def rentalCharge(customer: Customer, movie: Movie): Double = throw new NotImplementedException
    def rent(customer: Customer, movie: Movie) = throw new NotImplementedException
    def rentalsFor(customer: Customer): Seq[Rental] = throw new NotImplementedException
  }
}