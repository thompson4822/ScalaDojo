package com.videostore.component.service

import com.videostore.model.{Rental, Customer, MovieInstance}
import sun.reflect.generics.reflectiveObjects.NotImplementedException
import java.util.Date
import com.videostore.component.dao.{IStorePolicyDaoComponent, IRentalDaoComponent, ICustomerDaoComponent, IMovieInstanceDaoComponent}

/** Created on 7/23/11 at 12:36 PM by Steve */
 
trait IRentalServiceComponent {
  val rentalService: IRentalService

  trait IRentalService {
    def isRented(movie: MovieInstance): Boolean
    def whoRentedTo(movie: MovieInstance): Customer
    def rentalFor(movie: MovieInstance): Rental
    def rentalsFor(customer: Customer): Seq[Rental]
    def inCirculation: Map[Customer, Seq[MovieInstance]]
    def late: Map[Customer, Seq[Rental]]
    def lateFor(customer: Customer): Seq[Rental]
    def rent(customer: Customer, movieInstance: MovieInstance): Option[Rental]
    def returnRental(rental: Rental): Unit
  }
}

// What is the rental history?
trait RentalServiceComponent extends IRentalServiceComponent {
  this: IMovieInstanceDaoComponent with ICustomerDaoComponent with IRentalDaoComponent
    with ICostServiceComponent with ILogServiceComponent with IStorePolicyDaoComponent =>

  class RentalService extends IRentalService {
    def isRented(movie: MovieInstance): Boolean = throw new NotImplementedException
    def whoRentedTo(movie: MovieInstance): Customer = throw new NotImplementedException
    def rentalFor(movie: MovieInstance): Rental = throw new NotImplementedException
    def rentalsFor(customer: Customer): Seq[Rental] = rentalDao.findRentalsBy(customer)
    def inCirculation: Map[Customer, Seq[MovieInstance]] = throw new NotImplementedException
    def late: Map[Customer, Seq[Rental]] = throw new NotImplementedException
    def lateFor(customer: Customer): Seq[Rental] = throw new NotImplementedException

    def rent(customer: Customer, movieInstance: MovieInstance): Option[Rental] = {
      val rental = Rental(0, movieInstance, customer, costService.cost(movieInstance, customer))
      canRent(customer) match {
        case true =>
          rentalDao.save(rental)
          Some(rental)
        case false =>
          logService.error("Store policy states that only 1 movie can be rented to a customer at one time")
          None
      }
    }

    private def canRent(customer: Customer): Boolean = rentalsFor(customer).length < storePolicyDao.rentalLimit

    def returnRental(rental: Rental): Unit = {
      rental.returnDate = Some(new Date)
      rentalDao.save(rental)
    }

  }
}