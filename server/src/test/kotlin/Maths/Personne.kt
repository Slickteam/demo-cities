package Maths

import fr.slickteam.cities.model.City
import java.io.File

/**
 * Created by jguidoux on 17/05/2017.
 */
data class Personne(val prenom: String, val nom: String, val age: Int = 0)


fun main(args: Array<String>) {

    val list = listOf(1, 2, 3)
    list.forEach { println(it) }

    val listdePersonnes = listOf(
            Personne("Audrey", "Guidoux", 17),
            Personne("Chrisline", "Victor", 9),
            Personne("Jeremie", "Guidoux", 32)
    )

    listdePersonnes.filter { p -> p.nom.startsWith("Gui") }
            .map { p -> Personne(p.prenom, p.nom.toUpperCase(), p.age) }
            .forEach { p -> println("${p.prenom} ${p.nom}") }

    val audrey = Personne("Audrey", "Guidoux", 17)
    val (prenom, nom, _) = audrey
    println("${prenom} ${nom}")

    val monFicher = File("monFichier.doc")
    println(monFicher.extension)

    when (audrey.age) {
        in 1..10 -> println("Je suis un enfant")
        in 11..20 -> print("Je suis un adolescent")
        else -> print("Je suis un adulte")
    }

    val city = City(name = "aa")


}



