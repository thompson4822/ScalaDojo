package com.videostore

/** Created on 7/18/11 at 8:33 PM by Steve */

object MovieType extends Enumeration {
  type MovieType = Value
  val LadyInTheWater = Value(0, "Lady In The Water")
  val SnakesOnAPlane = Value("Snakes On A Plane")
  val JustMyLuck = Value("Just My Luck")
  val SupermanReturns = Value("Superman Returns")
  val YouMeDupree = Value("You, Me and Dupree")
  val TheNightListener = Value("The Night Listener")
}

object Critic extends Enumeration {
  type Critic = Value
  val Rose = Value("Lisa Rose")
  val Seymour = Value("Gene Seymour")
  val Phillips = Value("Michael Phillips")
  val Puig = Value("Claudia Puig")
  val LaSalle = Value("Mick LaSalle")
  val Matthews = Value("Jack Matthews")
  val Toby = Value("Toby")
}

import model.Account
import model.Customer
import model.DatabaseHelper
import model.Movie
import model.Review
import MovieType._
import Critic._
import collection.immutable.Map
import collection.mutable.{Map => MMap}

object Correlation {
  import scala.math._

/*
  type CriticPrefs = Map[Critic.Value, Map[MovieType.Value, Double]]
  type MoviePrefs = Map[MovieType.Value, Map[Critic.Value, Double]]

  val critics: CriticPrefs = Map(
    Rose -> Map(LadyInTheWater -> 2.5, SnakesOnAPlane -> 3.5, JustMyLuck -> 3.0, SupermanReturns -> 3.5, YouMeDupree -> 2.5,
      TheNightListener -> 3.0),
    Seymour -> Map(LadyInTheWater -> 3.0, SnakesOnAPlane -> 3.5, JustMyLuck -> 1.5, SupermanReturns -> 5.0, TheNightListener -> 3.0,
      YouMeDupree -> 3.5),
    Phillips -> Map(LadyInTheWater -> 2.5, SnakesOnAPlane -> 3.0, SupermanReturns -> 3.5, TheNightListener -> 4.0),
    Puig -> Map(SnakesOnAPlane -> 3.5, JustMyLuck -> 3.0, TheNightListener -> 4.5, SupermanReturns -> 4.0, YouMeDupree -> 2.5),
    LaSalle -> Map(LadyInTheWater -> 3.0, SnakesOnAPlane -> 4.0, JustMyLuck -> 2.0, SupermanReturns -> 3.0, TheNightListener -> 3.0,
      YouMeDupree -> 2.0),
    Matthews -> Map(LadyInTheWater -> 3.0, SnakesOnAPlane -> 4.0, TheNightListener -> 3.0, SupermanReturns -> 5.0, YouMeDupree -> 3.5),
    Toby -> Map(SnakesOnAPlane -> 4.5, YouMeDupree -> 1.0, SupermanReturns -> 4.0)
  )
*/

  type MoviePrefs = Map[Movie, Map[Account, Double]]

  lazy val moviePreferences: MoviePrefs = DatabaseHelper.reviews.toList.groupBy(review => review.movie).map{
    x =>
    val (movie, reviews) = x
    (movie, reviews.map(review => (review.account, review.rating.toDouble)).toMap)
  }

  val simDistance = (prefs: MoviePrefs, movie1: Movie, movie2: Movie) => {
    def reviewDifference(account: Account) = pow(prefs(movie1)(account) - prefs(movie2)(account), 2)
    val commonReviews = prefs(movie1).keySet.par.intersect(prefs(movie2).keySet).seq.toList
    1 / (1 + sqrt(commonReviews.map(reviewDifference).sum))
  }

/*
  val simDistance = (prefs: CriticPrefs, critic1: Critic, critic2: Critic) => {
    def reviewDifference(movie: MovieType) = pow(prefs(critic1)(movie) - prefs(critic2)(movie), 2)
    val commonReviews = prefs(critic1).keySet.intersect(prefs(critic2).keySet).toList
    1 / (1 + sqrt(commonReviews.map(reviewDifference).sum))
  }
*/


/*

  val simPearson = (prefs: CriticPrefs, critic1: Critic, critic2: Critic) => {
    val commonReviews = prefs(critic1).keySet.intersect(prefs(critic2).keySet).toList
    val reviewCount = commonReviews.length
    def sumF(critic: Critic, fun: Double => Double) = commonReviews.map(review => prefs(critic)(review)).map(x => fun(x)).sum
    val sum1 = sumF(critic1, x => x)
    val sum2 = sumF(critic2, x => x)
    val sum1Sq = sumF(critic1, x => pow(x, 2))
    val sum2Sq = sumF(critic2, x => pow(x, 2))
    val psum = commonReviews.map(review => prefs(critic1)(review) * prefs(critic2)(review)).sum
    val numerator = psum - (sum1 * sum2 / reviewCount)
    val denominator = sqrt((sum1Sq - pow(sum1, 2) / reviewCount) * (sum2Sq - pow(sum2, 2) / reviewCount))
    if (denominator == 0) 0.0 else numerator/denominator
  }

*/

  type SimFunc = (MoviePrefs, Movie, Movie) => Double

  def topMatches(prefs: MoviePrefs, movie: Movie, resultCount: Int = 5, similarity: SimFunc = simDistance) =
    prefs.keySet.par.filter(item => item != movie).toList.map(item => (similarity(prefs, movie, item), item)).seq.sortWith(_._1 > _._1).take(resultCount)


/*
  def topMatches(prefs: CriticPrefs, person: Critic, resultCount: Int = 5, similarity: SimFunc = simPearson) =
    prefs.keySet.filter(critic => critic != person).toList.map(critic => (similarity(prefs, person, critic), critic)).sortWith(_._1 > _._1).take(resultCount)
*/

  case class WeightedRecommendation(similarity: Double, weighted: Double, movie: MovieType)

/*
  def getRecommendations(prefs: CriticPrefs, person: Critic, similarityF: SimFunc = simPearson) = {
    def unseenMovieRecommendations(critic: Critic): List[WeightedRecommendation] =
    {
      val similarity = similarityF(prefs, person, critic)
      if(similarity > 0) {
        val unseenMovies = prefs(critic).filter{ x => val (movie, _) = x; prefs(person).contains(movie) == false }
        unseenMovies.map {
          x =>
          val (movie, rating) = x
          WeightedRecommendation(similarity, rating * similarity, movie)
        }.toList
      }
      else Nil
    }
    def cumulativeForMovie(collection: (MovieType, Seq[WeightedRecommendation])) = {
      val (movie, recommendations) = collection
      recommendations.reduceLeft((x, y) => WeightedRecommendation(x.similarity + y.similarity, x.weighted + y.weighted, movie))
    }
    val otherCritics = prefs.keySet.toList.filter(p => p != person)
    otherCritics.par.map(unseenMovieRecommendations).seq.flatten.
      groupBy(_.movie).toList.map(cumulativeForMovie).
      sortWith((x, y) => x.weighted / x.similarity > y.weighted / y.similarity)
  }
*/

  def main(args: Array[String]) {
    val porkys = DatabaseHelper.movies.par.filter(x => x._2.title == "Porky's").map(x => x._2).seq.headOption
    porkys match {
      case Some(movie) => println(topMatches(moviePreferences, movie, 300))
      case None => println("The movie you specified could not be found!")
    }
  }


}