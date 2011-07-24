package com.videostore.component.dao

/** Created on 7/23/11 at 12:46 PM by Steve */
 
trait IMovieInstanceDaoComponent {
  val movieInstanceDao: IMovieInstanceDao

  trait IMovieInstanceDao {

  }
}

trait MovieInstanceDaoComponent extends IMovieInstanceDaoComponent {
  class MovieInstanceDao extends IMovieInstanceDao {

  }
}