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
    fun filterCities( cityNameStartWith: String): List<City>

    /**
     * get a city with asking name
     *
     * @param cityName : the name of the city to search
     * @return the wanted city
     *
     * @throws CityNotFoundException if the city is not found
     */
    fun getCity( cityName : String) : City
}