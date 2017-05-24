package fr.slickteam.cities.service

import fr.slickteam.cities.dto.Account

/**
 * Created by jguidoux on 24/05/2017.
 */
interface IUserService {

	fun registerNewUserAccount(user: Account)
}