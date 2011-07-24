package com.videostore.model

/** Created on 7/24/11 at 8:14 AM by Steve */

object Occupation extends Enumeration {
  val Other = Value(0, "other")
  val AcademicEducator = Value("academic/educator")
  val Artist = Value("artist")
  val ClericalAdmin = Value("clerical/admin")
  val CollegeGradStudent = Value("college/grad student")
  val CustomerService = Value("customer service")
  val DoctorHealthCare = Value("doctor/health care")
  val ExecutiveManagerial = Value("executive/managerial")
  val Farmer = Value("farmer")
  val Homemaker = Value("homemaker")
  val K12Student = Value("K-12 student")
  val Lawyer = Value("lawyer")
  val Programmer = Value("programmer")
  val Retired = Value("retired")
  val SalesMarketing = Value("sales/marketing")
  val Scientist = Value("scientist")
  val SelfEmployed = Value("self-employed")
  val TechnicianEngineer = Value("technician/engineer")
  val TradesmanCraftsman = Value("tradesman/craftsman")
  val Unemployed = Value("unemployed")
  val Writer = Value("writer")
}

