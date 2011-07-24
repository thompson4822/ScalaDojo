package com.videostore.model

import java.util.Date

/** Created on 7/24/11 at 8:40 AM by Steve */

case class Comment(id: Long, review: Review, account: Account, title: String = "", var text: String, commentDate: Date = new Date)

