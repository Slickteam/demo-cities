package org.openclassrooms.cities.utils

import java.io.FileNotFoundException
import java.nio.file.Path
import java.nio.file.Paths

/**
 * Created by jguidoux on 02/05/2017.
 * get resource from gradle resource path
 */
fun Path.loadFromClassPath(): Path {
    val resource = Thread.currentThread().contextClassLoader.getResource(this.toString()) ?:
            throw FileNotFoundException("file '$this' does not exist!")
    return Paths.get(resource.toURI())
}