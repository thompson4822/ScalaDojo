package com.videostore

/** Created on 7/23/11 at 8:38 AM by Steve */
 
class RestockSpec extends CommonFeatureSpec {
  feature("Movies can be restocked") {
    info("As a system")
    info("I want to update my inventory when rentals return")
    info("So that my inventory stays up to date")

    scenario("Returned movie is in stock") {
      given("at least one rental")
      when("the rental is returned to the store")
      then("the rental is no longer recognized as being in circulation")
      and("the inventory should reflect that the movie is in store")
      pending
    }

  }
}