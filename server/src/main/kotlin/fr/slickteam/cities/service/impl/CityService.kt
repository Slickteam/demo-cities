package fr.slickteam.cities.service.impl

import fr.slickteam.cities.exceptions.CityNotFoundException
import fr.slickteam.cities.mappers.ICityMapper
import fr.slickteam.cities.model.City
import fr.slickteam.cities.service.ICityService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

/**
 * Created by jguidoux on 29/05/2017.
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
class CityService(val cityMapper: ICityMapper) : ICityService {

	override fun listCities() = cityMapper.listCities()

	override fun filterCities(cityNameStartWith: String)
			= cityMapper.filterCities(cityNameStartWith)

	@Throws(CityNotFoundException::class)
	override fun getCity(cityName: String): City {
		return cityMapper.getCity(cityName) ?: throw CityNotFoundException(cityName)
	}

	override fun insertCity(city: City) = cityMapper.insertCity(city)
}