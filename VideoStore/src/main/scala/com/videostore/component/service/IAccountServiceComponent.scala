package com.videostore.component.service

import com.videostore.component.dao.IAccountDaoComponent

/** Created on 7/23/11 at 1:13 PM by Steve */
 
trait IAccountServiceComponent {
  val accountService: IAccountService

  trait IAccountService {

  }
}

// Manages all aspects of the account
trait AccountServiceComponent extends IAccountServiceComponent {
  this: IAccountDaoComponent =>

  class AccountService extends IAccountService {

  }
}