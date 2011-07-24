package com.videostore.component.service

import sun.reflect.generics.reflectiveObjects.NotImplementedException
import com.videostore.component.dao.{IStorePolicyDaoComponent, ILateFeeDaoComponent}
import com.videostore.model.{LateFee, MovieInstance, Customer}
import java.util.Date

/** Created on 7/23/11 at 1:08 PM by Steve */
 
trait ICostServiceComponent {
  val costService: ICostService

  trait ICostService {
    def cost(movieInstance: MovieInstance, customer: Customer): BigDecimal
    def completeTransaction(customer: Customer, amountPaid: BigDecimal, movies: MovieInstance*): Unit
    def customerRiskScore(customer: Customer): Double
  }
}

// How much will rental cost?
trait CostServiceComponent extends ICostServiceComponent {
  this: IRentalServiceComponent with ILateFeeDaoComponent with IStorePolicyDaoComponent =>

  class CostService extends ICostService {
    def cost(movieInstance: MovieInstance, customer: Customer): BigDecimal = {
      import Math._
      val currentLateFees = lateFeeDao.outstandingForCustomer(customer)
      val lateFeeExpense = storePolicyDao.lateFee
      def originalMovieCost(lateFee: LateFee) =  lateFee.rental.movie.originalCost
      def amountOwed(lateFee: LateFee) = min(lateFee.daysLate * lateFeeExpense,originalMovieCost(lateFee))
      currentLateFees.map(amountOwed).sum + storePolicyDao.rentalFee
    }

    def completeTransaction(customer: Customer, amountPaid: BigDecimal, movies: MovieInstance*): Unit = {
      if(movies.map(m => cost(m, customer)).sum != amountPaid)
      {
        // This is an exceptional situation.  At the very least logging should be done
      }
      else {
        val outstanding = lateFeeDao.outstandingForCustomer(customer)
        outstanding.foreach(x => x.paidOn = Some(new Date))
        outstanding.foreach(lateFeeDao.save)
      }
    }

    def customerRiskScore(customer: Customer): Double = (lateFeeDao.allForCustomer(customer).length).toDouble / rentalService.rentalsFor(customer).length
  }
}