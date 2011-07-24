package com.videostore

/** Created on 7/23/11 at 7:22 AM by Steve */
 
class ReviewPointsSpec extends CommonFeatureSpec {
  feature("Customer earns review points for reviewing movies") {
    info("As a business owner")
    info("I want to incentivize customers to review movies by offering them review points")
    info("So that they will actually take the time to do this")

    scenario("Writing a review earns a reward point") {
      given("one or more customers")
      when("the customer writes a review for a movie")
      then("their review points should increase by 1")
      pending
    }

    scenario("Reward points influence rental cost") {
      given("a customer with review points")
      when("the customer tries to rent a movie")
      then("the review points value should be deducted from the cost of the rental")
      and("there should no longer be review points for the customer")
      pending
    }

  }

}