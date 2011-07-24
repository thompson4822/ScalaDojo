package com.videostore.component.dao

import com.videostore.model.Comment
import sun.reflect.generics.reflectiveObjects.NotImplementedException

/** Created on 7/24/11 at 5:56 AM by Steve */
 
trait ICommentDaoComponent {
  val commentDao: ICommentDao

  trait ICommentDao {
    def save(comment: Comment): Unit
  }

}

trait CommentDaoComponent extends ICommentDaoComponent {
  class CommentDao extends ICommentDao {
    def save(comment: Comment): Unit = throw new NotImplementedException
  }
}