package com.videostore.component

import dao._
import service._

/** Created on 7/23/11 at 11:01 AM by Steve */
 
object ComponentRegistry extends MovieDaoComponent with CustomerDaoComponent with MovieInstanceDaoComponent with AccountDaoComponent  with ReviewDaoComponent with RentalDaoComponent
                        with InventoryServiceComponent with RentalServiceComponent with ReservationServiceComponent with CostServiceComponent with AccountServiceComponent with ReviewServiceComponent with CustomerServiceComponent
                        with LogServiceComponent with CommentDaoComponent with StorePolicyDaoComponent {
  val movieDao = new MovieDao
  val customerDao = new CustomerDao
  val inventoryService = new InventoryService
  val rentalService = new RentalService
  val movieInstanceDao = new MovieInstanceDao
  val reservationService = new ReservationService
  val costService = new CostService
  val accountService = new AccountService
  val accountDao = new AccountDao
  val reviewService = new ReviewService
  val reviewDao = new ReviewDao
  val customerService = new CustomerService
  val rentalDao = new RentalDao
  val logService = new LogService
  val commentDao = new CommentDao
  val storePolicyDao = new StorePolicyDao
}