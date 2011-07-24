package com.videostore

import model._
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.mock.MockitoSugar
import org.scalatest.{BeforeAndAfterEach, GivenWhenThen, FeatureSpec}
import java.util.Date

/** Created on 7/23/11 at 6:18 AM by Steve */
 
trait CommonFeatureSpec extends FeatureSpec with GivenWhenThen with ShouldMatchers with BeforeAndAfterEach with MockitoSugar with TestingEnvironment {
  var customer: Customer = _
  var movie: Movie = _
  var movieInstance: MovieInstance = _
  var rental: Rental = _
  var account: Account = _
  var authorization: Authorization = _
  var review: Review = _


  override def beforeEach() = {
    customer = Customer(0, "Steve", "Nelson", "snelson@rocket.com", dob = new Date)
    movie = Movie(0, "The Best Movie Evar", 2011)
    movieInstance = MovieInstance(0, movie, new Date, MediaType.BluRay, 20.00)
    rental = Rental(0, movieInstance, customer, charge = 1.00)
    authorization = Authorization(0, "rocketman", "pa$$w0rd")
    account = Account(0, customer, authorization)
    review = Review(0, account, movie, 4.5, title="These guys aren't kidding", text="This really is the BEST MOVIE EVAR!!!")
  }
}