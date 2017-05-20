package org.openclassrooms.cities.model

/**
 * Created by jguidoux on 30/04/2017.
 */
data class City(var name: String,
                var population: Int = 100000,
                var revenuPerHabitants :Int = 20000)