package com.videostore.component.service

import com.videostore.model.{Comment, Movie, Account, Review}
import com.videostore.component.dao.{ICommentDaoComponent, IReviewDaoComponent}

/** Created on 7/23/11 at 1:20 PM by Steve */
 
trait IReviewServiceComponent {
  val reviewService: IReviewService

  trait IReviewService {
    def addReview(review: Review): Unit
    def reviewFor(account: Account, movie: Movie): Option[Review]
    def updateReview(review: Review, account: Account): Unit
    def canEdit(review: Review, otherAccount: Account): Boolean
    def addComment(comment: Comment): Unit
  }

}

// Manage all review related functionality
trait ReviewServiceComponent extends IReviewServiceComponent {
  this: ILogServiceComponent with IReviewDaoComponent with ICommentDaoComponent =>

  class ReviewService extends IReviewService {
    def addReview(review: Review): Unit = {
      if(reviewDao.reviewAlreadyGiven(review.account, review.movie))
        logService.error("An account may only provide a single movie review");
      else
        reviewDao.save(review)
    }

    def reviewFor(account: Account, movie: Movie): Option[Review] = {
      val result = reviewDao.findReview(account, movie)
      if(result == None) logService.error("Could not find a review for the account and movie given")
      result
    }

    def updateReview(review: Review, account: Account): Unit = reviewDao.exists(review) && canEdit(review, account) match {
      case true => reviewDao.update(review)
      case false => logService.error("Cannot update an unknown review")
    }

    def canEdit(review: Review, otherAccount: Account): Boolean = review.account == otherAccount

    def addComment(comment: Comment) = commentDao.save(comment)
  }
}