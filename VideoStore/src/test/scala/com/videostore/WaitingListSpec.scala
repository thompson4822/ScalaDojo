package com.videostore

/** Created on 7/23/11 at 8:05 AM by Steve */
 
class WaitingListSpec extends CommonFeatureSpec {
  feature("If a movie is not available, customers can be added to a queue and notified when it arrives") {
    info("As a customer")
    info("I want to be added to a waiting list for a movie if all copies are in circulation")
    info("So that I can be notified when the title is available")

    scenario("Customer can be added to waiting list when a movie is in circulation") {
      given("a rental exists")
      and("at least one customer other than the renter exists")
      when("that customer wants to rent the movie")
      then("the customer should be added to the waiting list for that movie")
      pending
    }

    scenario("Customer can be added to a waiting list when a movie is reserved") {
      given("a movie exists")
      and("a customer exists")
      and("the movie is currently reserved")
      when("the customer wants to rent the movie")
      then("the customer should be added to the waiting list for that movie")
      pending
    }

    scenario("First customer in the waiting list should be sent an email when the rental is returned") {
      given("there is at least one customer in the waiting list for a movie")
      and("all copies of the movie are in circulation")
      when("a rental for the movie is returned")
      then("the first customer in the waiting list should be sent an email")
      and("the movie should be reserved for that customer")
      and("the size of the waiting list should decrease by one")
      pending
    }

    scenario("First customer in the waiting list should be sent an email when a movie is no longer being reserved") {
      given("there is at least one customer in the waiting list for a movie")
      when("a reservation on the movie no longer exists")
      then("the first customer in the waiting list should be sent an email")
      and("the movie should be reserved for that customer")
      and("the size of the waiting list should decrease by one")
      pending
    }

    scenario("Reservations expire after 24 hours") {
      given("a reservation exists")
      when("a day has elapsed")
      then("the reservation should expire")
      pending
    }

    scenario("When a reservation expires, the next customer on the waiting list is notified") {
      given("there is at least one customer in the waiting list for a movie")
      and("the movie is currently being reserved")
      when("the reservation expires")
      then("the first customer in the waiting list should be sent an email")
      and("the movie should be reserved for that customer")
      and("the size of the waiting list should decrease by one")
      pending
    }
  }
}