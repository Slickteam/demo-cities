package org.openclassrooms.cities.service

import org.openclassrooms.cities.dto.Account

/**
 * Created by jguidoux on 24/05/2017.
 */
interface IUserService {

	fun registerNewUserAccount(user: Account)
}