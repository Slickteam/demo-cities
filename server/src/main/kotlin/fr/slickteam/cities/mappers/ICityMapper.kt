package fr.slickteam.cities.mappers

import fr.slickteam.cities.model.City
import org.apache.ibatis.annotations.Mapper
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

/**
 * Created by jguidoux on 29/05/2017.
 */
@Mapper
@Transactional(propagation = Propagation.MANDATORY)
interface ICityMapper {

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
	fun getCity(cityName: String): City?

	fun insertCity(city: City)
}