package com.videostore

import model.Rental

/** Created on 7/23/11 at 6:17 AM by Steve */
 
class LateFeesSpec extends CommonFeatureSpec {

  var lateRental: Rental = _
  var obscenelyLateRental: Rental = _

  feature("When rentals are returned late, the customer is charged according to store policy") {
    info("As a video store owner")
    info("I want to charge a fee for rentals that are late")
    info("So that customers have an incentive to return my products on time")

    scenario("Late fees are figured into the rental cost for customers") {
      given("a customer with a previous rental that was late")
      when("the customer rents another movie")
      then("the cost of their late fees should be calculated according to store policy")
      and("their total rental cost should include the cost of the rental and the accumulated late fees")
      pending
    }

    scenario("Late fees will never exceed the cost of the movie") {
      given("a customer with a previous rental that was obscenely late")
      when("the customer rents another movie")
      then("the cost of their late fee for the previous rental should be equal to the value of the movie")
      pending
    }

    scenario("Once paid, late fees are no longer levied against the customer") {
      given("a customer with outstanding late fees")
      when("the customer rents another title")
      then("there should no longer be any late fees for the customer")
      pending
    }

    scenario("Customer risk can be assessed") {
      given("a customer who has had late fees in the past")
      and("a customer with no late fees in the past")
      when("risk is assessed")
      then("the customer with late fees should be shown to be a greater risk than the customer without")
      pending
    }

  }
}