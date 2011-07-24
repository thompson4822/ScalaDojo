package com.videostore.component.dao

import com.videostore.model.Account

/** Created on 7/23/11 at 1:16 PM by Steve */
 
trait IAccountDaoComponent {
  val accountDao: IAccountDao

  trait IAccountDao {

  }
}

trait AccountDaoComponent extends IAccountDaoComponent {
  class AccountDao extends IAccountDao with CommonDao[Account] {
    override val t = manifest[Account]

  }
}