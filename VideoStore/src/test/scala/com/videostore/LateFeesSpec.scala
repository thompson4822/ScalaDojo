package com.videostore

import model.{LateFee, Rental}
import org.mockito.Mockito._
import org.mockito.BDDMockito.{given => givenThat}
import java.util.{Date, Calendar}

/** Created on 7/23/11 at 6:17 AM by Steve */

object Implicits {
  implicit def intToSmartDate(interval: Int): SmartDate = new SmartDate(interval)

  class SmartDate(interval: Int) {
    import java.util.Date
    val daysAgo: Date = adjustBy(0-interval, Calendar.DATE)
    val monthsAgo: Date = adjustBy(0-interval, Calendar.MONTH)

    private def adjustBy(amount: Int, intervalType: Int): Date = {
      val result = Calendar.getInstance()
      result.add(intervalType, amount)
      result.getTime()
    }
  }
}

class LateFeesSpec extends CommonFeatureSpec {
  import com.videostore.Implicits._

  override val costService = new CostService

  override def beforeEach() = {
    super.beforeEach()
    givenThat(storePolicyDao.lateFee).willReturn(1)
    givenThat(storePolicyDao.rentalFee).willReturn(1)
  }

  feature("When rentals are returned late, the customer is charged according to store policy") {
    info("As a video store owner")
    info("I want to charge a fee for rentals that are late")
    info("So that customers have an incentive to return my products on time")

    scenario("Late fees are figured into the rental cost for customers") {
      given("a customer with a previous rental that was late")
        givenThat(lateFeeDao.outstandingForCustomer(customer)).willReturn(Seq(new LateFee(0, rental, 3, None)))
      when("the customer rents another movie")
      then("the cost of their late fees should be calculated according to store policy")
      and("their total rental cost should include the cost of the rental and the accumulated late fees")
        costService.cost(movieInstance, customer) should be(3.00 + 1.00)
    }

    scenario("Late fees will never exceed the cost of the movie") {
      given("a customer with a previous rental that was obscenely late")
        givenThat(lateFeeDao.outstandingForCustomer(customer)).willReturn(Seq(new LateFee(0, rental, 300, None)))
      when("the customer rents another movie")
      then("the cost of their late fee for the previous rental should be equal to the value of the movie")
        costService.cost(movieInstance, customer) should be(20.00 + 1.00)
    }

    scenario("Once paid, late fees are no longer levied against the customer") {
      given("a customer with outstanding late fees")
        val lateFee = new LateFee(0, rental, 300, None)
        givenThat(lateFeeDao.outstandingForCustomer(customer)).willReturn(Seq(lateFee))
      when("the customer rents another title")
        costService.completeTransaction(customer, 20.00 + 1.00, movieInstance)
      then("there should no longer be any late fees for the customer")
        verify(lateFeeDao).save(lateFee)
        lateFee.paidOn should not be(None)
    }

    scenario("Customer risk can be assessed") {
      given("a customer who has had one late fee in the past")
        givenThat(lateFeeDao.allForCustomer(customer)).willReturn(Seq(new LateFee(0, rental, 300, None)))
      and("a total of 2 rentals")
        givenThat(rentalService.rentalsFor(customer)).willReturn(Seq(rental, Rental(1, movieInstance, customer, 1, new Date(1010101010))))
      when("risk is assessed")
        val risk = costService.customerRiskScore(customer)
      then("the result should be 0.5")
        risk should be(0.5)
    }

  }
}