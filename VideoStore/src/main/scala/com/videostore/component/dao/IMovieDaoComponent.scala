package com.videostore.component.dao

import com.videostore.model.Movie
import sun.reflect.generics.reflectiveObjects.NotImplementedException

/** Created on 7/23/11 at 10:40 AM by Steve */
 
trait IMovieDaoComponent {
  val movieDao: IMovieDao

  trait IMovieDao {
    def findByTitle(title: String): Movie
  }
}

trait MovieDaoComponent extends IMovieDaoComponent {
  class MovieDao extends IMovieDao {
    def findByTitle(title: String): Movie = throw new NotImplementedException
  }
}