package com.videostore.component.dao

import com.videostore.model.{Account, Movie, Review}
import sun.reflect.generics.reflectiveObjects.NotImplementedException

/** Created on 7/23/11 at 1:23 PM by Steve */
 
trait IReviewDaoComponent {
  val reviewDao: IReviewDao

  trait IReviewDao {
    def save(review: Review): Unit
    def update(review: Review): Unit
    def reviewAlreadyGiven(account: Account, movie: Movie): Boolean
    def exists(review: Review): Boolean
    def findReview(account: Account, movie: Movie): Option[Review]
  }
}

trait ReviewDaoComponent extends IReviewDaoComponent {
  class ReviewDao extends IReviewDao {
    def save(review: Review): Unit = throw new NotImplementedException
    def update(review: Review): Unit = throw new NotImplementedException
    def reviewAlreadyGiven(account: Account, movie: Movie): Boolean = throw new NotImplementedException
    def exists(review: Review): Boolean = throw new NotImplementedException
    def findReview(account: Account, movie: Movie): Option[Review] = throw new NotImplementedException
  }
}