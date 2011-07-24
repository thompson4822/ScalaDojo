package com.videostore

/** Created on 7/23/11 at 8:46 AM by Steve */
 
class SearchSpec extends CommonFeatureSpec {
  feature("Search") {
    info("As a customer/store clerk")
    info("I want to be able to search for titles")
    info("So that I know their availability")

    scenario("Search can be done based on movie title") {
      given("at least one movie exists")
      when("I search on the title of that movie")
      then("I should see the correct number of matching results")
      pending
    }

    scenario("Search can be done based on movie title and media type") {
      given("at least one movie exists")
      when("I search on the title of that movie")
      and("I use the movie's media type")
      then("I should see the correct number of matching results")
      pending
    }

    scenario("Search can be done based on movie genre") {
      given("at least one movie exists")
      when("I search on one of the movie genres associated with that movie")
      then("I should see the correct number of matching results")
      pending
    }

  }
}