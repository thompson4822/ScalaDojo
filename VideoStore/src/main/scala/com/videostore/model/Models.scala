package com.videostore.model

import collection.immutable.Map
import util.Random
import java.util.{Calendar, Date}
import sun.reflect.generics.reflectiveObjects.NotImplementedException
//import com.videostore.model.enumeration._

/** Created on 7/13/11 at 6:56 PM by Steve */


// Here are the types that represent our customers and their activities

// Here is how the customer might log in to our online website to submit reviews

// Here is the stuff for soliciting reviews
object CustomerBuilder {
  val rand: Random = new Random
  val maleFirstNames = List("Jacob", "Michael", "Joshua", "Matthew", "Ethan", "Andrew", "Daniel", "Anthony", "Christopher", "Joseph",
        "William", "Alexander", "Ryan", "David", "Nicholas", "Tyler", "James", "John", "Jonathan", "Nathan")

  val femaleFirstNames = List("Emily", "Emma", "Madison", "Abigail", "Olivia", "Isabella", "Hannah", "Samantha", "Ava", "Ashley", "Sophia",
        "Elizabeth", "Alexis", "Grace", "Sarah", "Alyssa", "Mia", "Natalie", "Chloe", "Brianna")

  val lastNames = List("Smith", "Jones", "Johnson", "Williams", "Brown", "Davis", "Miller", "Wilson", "Moore", "Taylor", "Anderson",
        "Thomas", "Jackson", "White", "Harris", "Martin", "Thompson", "Garcia", "Martinez", "Robinson", "Clark", "Rodriguez", "Lewis",
        "Lee", "Walker", "Hall", "Allen", "Young", "Hernandez", "King", "Wright", "Lopez", "Hill", "Scott", "Green", "Adams")

  val emailDomains = List("@111mail.com", "@2die4.com", "@37.com", "@420email.com", "@4degreez.com", "@4-music-today.com", "@adexec.com",
        "@africamail.com", "@alex4all.com", "@allergist.com", "@allhiphop.com", "@alumnidirector.com", "@anatomicrock.com", "@animeone.com",
        "@anjungcafe.com", "@archaeologist.com", "@arcticmail.com", "@artlover.com", "@asia.com", "@autopm.com", "@berlin.com",
        "@bicycling.com", "@bigheavyworld.com", "@bigmailbox.net", "@bikermail.com", "@blackcity.net", "@blackvault.com", "@boarderzone.com",
        "@boatnerd.com", "@bolbox.com", "@bongmail.com", "@bowl.com", "@calle22.com", "@catlover.com", "@chatway.com", "@cheerful.com",
        "@chemist.com", "@chillymail.com", "@darkfear.com", "@darkforces.com")

  def nextFor(list: List[String]): String = list(rand.nextInt(list.length))

  def apply(id: Long, gender: Gender.Value, age: Int, occupation: Occupation.Value): Customer = {
    val firstName = if(gender == Gender.Female) nextFor(femaleFirstNames) else nextFor(maleFirstNames)
    val lastName = nextFor(lastNames)
    val email = (firstName.charAt(0) + lastName + nextFor(emailDomains)).toLowerCase
    val dob = Calendar.getInstance();
    dob.add(Calendar.YEAR, 0 - age);
    Customer(id, firstName, lastName, email, gender, new Date(dob.getTimeInMillis), occupation)
  }
}

// What would we need for calculating similarities?


object DatabaseHelper {
  lazy val movies: Map[Long, Movie] = {
    val parseMovies = """^(\d+)::(.*)\s*\((\d{4})\)::(.*)$""".r
    val parseGenres = """([a-zA-Z \'\-]+)\|?""".r
    io.Source.fromFile("VideoStore/data/movies.dat", "iso-8859-1").getLines().map {
      line =>
      val parseMovies(id, title, releaseYear, genresString) = line
      val genres = parseGenres.findAllIn(genresString).map{ case parseGenres(genre) => Genre.withName(genre) }
      (id.toLong, Movie(id.toLong, title.trim(), releaseYear.toInt, genres.toList))
    }.toMap
  }

  lazy val users: Map[Long, Customer] = {
    val parseUsers = """^(\d+)::(M|F)::(\d+)::(\d+)::([0-9\-]+)$""".r  //6040::M::25::6::11106
    io.Source.fromFile("VideoStore/data/users.dat", "iso-8859-1").getLines().map {
      line =>
      val parseUsers(id, gender, age, occupation, zip) = line
      (id.toLong, CustomerBuilder(id.toLong, Gender.withName(gender), age.toInt, Occupation(occupation.toInt)))
     }.toMap
  }

  lazy val accounts: Map[Long, Account] = {
    def accountFor(customer: Customer): Account = {
      val username = (customer.firstName.charAt(0)+customer.lastName).toLowerCase
      val authorization = Authorization(customer.id, username, "pa$$w0rd")
      Account(customer.id, customer, authorization)
    }
    users.values.map(customer => (customer.id, accountFor(customer))).toMap
  }

  lazy val reviews: Iterator[Review] = {
    val parseReviews = """^(\d+)::(\d+)::(\d)::(\d+)$""".r  //1::1193::5::978300760
    val result = io.Source.fromFile("VideoStore/data/ratings.dat", "iso-8859-1").getLines().map {
      line =>
      val parseReviews(userId, movieId, rating, timestamp) = line
      new Review(0, accounts(userId.toLong), movies(movieId.toLong), rating.toDouble, ratingDate = new Date(timestamp.toLong * 1000))
    }
    println("Loaded all the reviews!")
    result
  }
}

object Main {

  // There is a story for cost somewhere
  // Basically, cost is a function of the popularity of a movie and its age, such that older movies that
  // are still popular are potentially worth more than newer movies with very little interest.  Is the
  // complexity worth introducing into the system though?


  /*
    We incentivize reviews so that the customer will create content for Dino's.  Each review helps discount their next
    rental, and so this function figures out how many review points they have racked up since the last time they
    rented
  */
  def currentReviewPoints(customer: Customer): Int = throw new NotImplementedException

  /*
    This story involves several things:
    - Looking at the customer's Rental history to find
      - any late fees they owe
      - any incentives that might impact their total cost
    - Looking at the age of the movie to determine base cost
    - Creating and storing Rental records on successful transaction.
   */
  def rent(customer: Customer, movies: List[MovieInstance]) {
    def totalCost: BigDecimal = { throw new NotImplementedException }
  }

  // Represents a criteria for special rental prices.  For instance, all action movies on VHS might have a $1.00 discount
  case class SpecialOffer(discount: BigDecimal, movie: Option[Movie] = None, genres: Option[List[Genre.Value]] = None, mediaTypes: Option[List[MediaType.Value]] = None, start: Date, end: Option[Date] = None)

  def specialOffers: List[SpecialOffer] = { throw new NotImplementedException }

  /*
    - Does the store have any copies of the movie for the preferred media type?
   */
  def hasMovie(movie: Movie, media: MediaType.Value): Boolean = { throw new NotImplementedException }

  /*
    Can similarity be used to determine other factors than just recommendations?
    - Which types of movies/number should be purchased and of what media type
    - What media should be sold and when .
   */

  def main(args: Array[String]) {

    val movies = DatabaseHelper.movies.values
    movies.foreach(println)
    println("Total: " + movies.size)

    val users = DatabaseHelper.users.values
    users.foreach(println)
    println("Total: " + users.size)
  }

}
