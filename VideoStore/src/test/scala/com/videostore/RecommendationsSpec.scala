package com.videostore

/** Created on 7/23/11 at 8:53 AM by Steve */
 
class RecommendationsSpec extends CommonFeatureSpec {
  feature("Can provide recommendations") {
    info("As a system")
    info("I want to provide recommendations to users")
    info("So that they are more likely to rent movies they will enjoy")

    scenario("Movies can be recommended based on the rental history of the customer") {
      given("a customer exists")
      and("that customer has some number of rentals in their history")
      when("asked for a recommendation")
      then("the system should use that history to recommend other movie titles")
      pending
    }

    scenario("Movies can be recommended based on the ratings the user has provided in the past") {
      given("a customer exists")
      and("that customer has provided some number of movie reviews")
      when("the system is asked for recommendations")
      then("the system should use those ratings to recommend other movie titles")
      pending
    }

  }

}