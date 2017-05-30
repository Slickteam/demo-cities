package fr.slickteam.cities.service

import fr.slickteam.cities.model.City

/**
 * Created by jguidoux on 29/05/2017.
 */
interface ICityService {

	/**
	 * return the entire list of city
	 */
	fun listCities(): List<City>

	/**
	 * to get all cities where city name start with the user input
	 */
	fun filterCities(cityNameStartWith: String): List<City>

	/**
	 * get a city with asking name
	 *
	 * @param cityName : the name of the city to search
	 * @return the wanted city
	 *
	 * @throws CityNotFoundException if the city is not found
	 */
	fun getCity(cityName: String): City

	fun insertCity(city: City)
}