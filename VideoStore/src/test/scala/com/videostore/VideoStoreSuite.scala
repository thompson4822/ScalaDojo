package com.videostore

/** Created on 7/23/11 at 7:40 AM by Steve */

import org.scalatest.Suite
import org.scalatest.junit.JUnitRunner
import org.junit.runner.RunWith

@RunWith(classOf[JUnitRunner])
class VideoStoreSuite extends Suite {
  override def nestedSuites = List(
    new RentalServiceSpec,
    new LateFeesSpec,
    new MovieReviewServiceSpec,
    new ReviewPointsSpec,
    new WaitingListSpec,
    new RestockSpec,
    new SearchSpec,
    new RecommendationsSpec,
    new AccountServiceSpec
  )
}