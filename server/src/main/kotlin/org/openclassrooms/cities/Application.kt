package org.openclassrooms.cities

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.PropertySource


/**
 * Created by jguidoux on 21/04/2017.
 */
@SpringBootApplication
@PropertySource("classpath:/env.properties")
@ComponentScan("org.openclassrooms.cities")
class Application

fun main(args: Array<String>) {


    SpringApplication.run(Application::class.java, *args)
}

