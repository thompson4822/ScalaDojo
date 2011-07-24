package com.videostore

import java.util.Date
import model._
import org.mockito.Mockito._
import org.mockito.BDDMockito.{given => givenThat}
import org.mockito.Matchers._

/** Created on 7/23/11 at 6:54 AM by Steve */
 
class MovieReviewServiceSpec extends CommonFeatureSpec {

  var account: Account = _
  var customer: Customer = _
  var authorization: Authorization = _
  var movie: Movie = _
  var review: Review = _

  override val reviewService = new ReviewService

  override def beforeEach() = {
    customer = Customer(0, "Steve", "Nelson", "rocketman@genie.net", Gender.Male, new Date)
    authorization = Authorization(0, "rocketman", "pa$$w0rd")
    account = Account(0, customer, authorization)
    movie = Movie(0, "The Greatest Movie Evar", 2011)
    review = Review(0, account, movie, 4.5, title="These guys aren't kidding", text="This really is the BEST MOVIE EVAR!!!")
  }

  feature("Customers with an account can review the movies they have rented or seen") {
    info("As a video store owner")
    info("I want to encourage customers to review movies")
    info("So that I can gather statistics to better seve my customers")
    info("And build a body of useful material similar to what Amazon and Netflix do")

    scenario("Customer can provide a review of a movie") {
      given("there is at least one account")
      and("there is at least one movie")
      when("the account customer reviews the movie")
        reviewService.addReview(review)
      then("the system should keep track of that review")
        verify(reviewDao).save(review)
    }

    scenario("Customer cannot review a movie more than once") {
      given("there is at least one review")
      and("the account customer has already written a review of the movie")
        givenThat(reviewDao.reviewAlreadyGiven(account, movie)).willReturn(true)
      when("the account customer tries to write a second review for a movie")
        val secondReview = Review(0, account, movie, 5.0, "Did I mention how much I liked this movie?")
        reviewService.addReview(secondReview)
      then("the review should be rejected")
        verify(logService).error("An account may only provide a single movie review")
      and("it should be unknown to the system")
         verify(reviewDao, never()).save(secondReview);
    }

    scenario("Customer should be able to edit their review of a movie") {
      given("there is at least one review")
      when("the account customer edits this review")
        givenThat(reviewDao.findReview(account, movie)).willReturn(Some(review))
        givenThat(reviewDao.exists(review)).willReturn(true)
        val newReview: Review = reviewService.reviewFor(account: Account, movie: Movie).get
        review.text = "This movie really really rocks!  Check it out!"
      then("the system should keep track of the revised review")
        reviewService.updateReview(review, account)
        verify(reviewDao).update(review)
    }

    scenario("Customers can only edit their own reviews") {
      given("there is at least one review")
      and("there is at least one account customer other than the account customer who wrote the review")
        val differentAccount = Account(0, Customer(0, "Jan", "Duncan", "jdunks@wallyworld.com", Gender.Female, new Date), Authorization(0, "jdunks", "pa$$w0rd"))
      then("the system does not provide the option of editing the review")
        reviewService.canEdit(review, differentAccount) should be(false)
        reviewService.updateReview(review, differentAccount)
        verify(reviewDao, never()).update(review);
    }

    scenario("Customer can comment on a review") {
      given("there is at least one review")
      and("there is at least one account customer other than the account customer who wrote the review")
      when("the other customer writes a comment for the review")
        val comment = Comment(0, review, account, "Way off base", "This is so completely not the Best Movie Evar!")
        reviewService.addComment(comment)
      then("the system should keep track of the comment")
        verify(commentDao).save(comment)
    }


  }

}