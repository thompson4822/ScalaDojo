package com.videostore

/** Created on 7/23/11 at 9:12 AM by Steve */
 
class AccountServiceSpec extends CommonFeatureSpec {
  feature("Accounts can be created and used") {
    info("As a system")
    info("I want to allow customers to create an account")
    info("So that they have a way of providing movie reviews, checking their history, etc.")

    scenario("Account can be created") {
      given("a customer exists")
      and("the customer doesn't already have an account")
      when("the customer creates an account with a user name and password")
      then("they should be tracked by the system")
      and("they should be sent a welcoming email")
      pending
    }

    scenario("Only one account per customer allowed") {
      given("a customer exists")
      and("the customer already has an account")
      when("the customer creates an account with a user name and password")
      then("the operation should be rejected")
      and("the customer told they are only allowed one account in the system")
      pending
    }

    scenario("The user can alter their account password") {
      given("a customer account exists")
      when("the customer wants to change the password for their account")
      then("the system should use the new password for their account")
      pending
    }

    scenario("The user can alter their account user name, as long as it is unique") {
      given("a customer account exists")
      when("the customer wants to change the user name for their account")
      and("the user name is unique")
      then("the system should use the new user name for their account")
      pending
    }

    scenario("The user cannot alter their account user name to be something non-unique") {
      given("a customer account exists")
      when("the customer wants to change the user name for their account")
      and("the user name is not unique")
      then("the operation should be rejected")
      and("the customer told that they are only allowed to use unique user names")
      pending
    }

    scenario("The customer account can log in") {
      given("a customer account exists")
      when("someone tries to log in with the correct user name and password")
      then("they should be recognized by the system as having the account")
      pending
    }

    scenario("The customer account can't log in with incorrect credentials") {
      given("a customer account exists")
      when("someone tries to log in with incorrect credentials")
      then("the operation should be rejected")
      and("the user should be told that their login credentials were incorrect")
      pending
    }


  }

}