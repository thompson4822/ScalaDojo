package com.videostore

import model._
import org.mockito.Mockito._
import org.mockito.BDDMockito.{given => givenThat}
import org.mockito.Matchers._
import java.util.Date

/** Created on 7/23/11 at 5:48 AM by Steve */
 
class RentalServiceSpec extends CommonFeatureSpec {

  override val rentalService = new RentalService

  // Get some common init/mocking out of the way
  override def beforeEach() = {
    super.beforeEach()
    givenThat(storePolicyDao.rentalLimit).willReturn(1)
  }


  feature("A customer can rent a movie if it is available") {
    info("As a video store owner")
    info("I want to be able to rent out movies")
    info("So that I make profit")

    scenario("Customer can rent a movie") {
      given("there is at least one customer and one movie")
        givenThat(rentalDao.findRentalsBy(customer)).willReturn(Seq[Rental]())
      when("the customer tries to rent the movie")
        val newRental = rentalService.rent(customer, movieInstance).get
      then("the system should recognize that the movie has been rented")
        verify(rentalDao).save(newRental)
      and("the movie should not be available for rental")
        // This would mean checking the inventory, which would mean using a separate service.  Desirable?
    }

    scenario("Customer returns a rental") {
      given("There is at least one rental")
        givenThat(rentalDao.findRentalsBy(customer)).willReturn(Seq(rental))
      when("the customer returns the movie")
        rentalService.returnRental(rental)
      then("the system should recognize that the movie is no longer being rented")
        rental.returnDate should not be(None)
        verify(rentalDao).save(rental)
      and("the movie should be available for rental once more")
        // This would mean checking the inventory, which would mean using a separate service.  Desirable?
    }

    // I don't think this should be here.  Rather, there should be another story where the system is periodically checking the status of the inventory
/*
    scenario("Customer is sent an email if a rental is late") {
      given("There is at least one rental")
      when("the rental period has expired")
      then("the system should notify the customer via email that the movie needs returned")
      pending
    }
*/

    scenario("Customer cannot rent more movies at once than store policy allows") {
      given("the store policy is a single movie rental")
      and("there are at least two movies")
        val secondMovieInstance = MovieInstance(0, movie, new Date, MediaType.DVD, 15.00)
      and("one movie has already been rented to the customer")
        givenThat(rentalDao.findRentalsBy(customer)).willReturn(Seq(rental))
      when("the customer rents the second movie")
        rentalService.rent(customer, secondMovieInstance)
      then("the system should not allow the second movie to be rented")
        verify(logService).error("Store policy states that only 1 movie can be rented to a customer at one time")
      and("no rental for the second movie should have been created")
        verify(rentalDao, never()).save(rental)
    }


  }
}