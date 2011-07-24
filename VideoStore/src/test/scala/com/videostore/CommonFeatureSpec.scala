package com.videostore

import org.scalatest.matchers.ShouldMatchers
import org.scalatest.mock.MockitoSugar
import org.scalatest.{BeforeAndAfterEach, GivenWhenThen, FeatureSpec}

/** Created on 7/23/11 at 6:18 AM by Steve */
 
trait CommonFeatureSpec extends FeatureSpec with GivenWhenThen with ShouldMatchers with BeforeAndAfterEach with MockitoSugar with TestingEnvironment {

}