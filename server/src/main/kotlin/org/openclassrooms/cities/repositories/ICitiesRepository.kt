package org.openclassrooms.cities.repositories

import org.openclassrooms.cities.model.City

/**
 * Created by jguidoux on 30/04/2017.
 * this repository can read cities database to get information on it
 */
 interface ICitiesRepository {

    /**
     * return the entire list of city
     */
     fun listCities(): List<City>

    /**
     * to get all cities where city name start with the user input
     */
    fun filterCities(cityNameStartWith: String): List<City>
}