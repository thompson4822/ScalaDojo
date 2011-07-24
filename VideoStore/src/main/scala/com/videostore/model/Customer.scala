package com.videostore.model

import java.util.Date

/** Created on 7/24/11 at 8:37 AM by Steve */

case class Customer(id: Long, firstName: String, lastName: String, email: String,
                    gender: Gender.Value = Gender.UnknownGender, dob: Date, occupation: Occupation.Value = Occupation.Other)

