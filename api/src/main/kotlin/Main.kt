package org.example

import java.sql.Connection
import java.sql.DriverManager
import java.util.*

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    val name = "Kotlin"
    //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
    // to see how IntelliJ IDEA suggests fixing it.
    println("Hello, " + name + "!")
    val connection = getConnection()
    println("Connected to MariaDB database successfully.")
    val query = connection.createStatement()
    val resultSet = query.executeQuery("SELECT VERSION();")

    if (resultSet.next()) {
        println("Database Version: ${resultSet.getString(1)}")
    }
}


fun loadDatabaseConfig(): Properties {
    val properties = Properties()
    val propertiesStream = Thread.currentThread().contextClassLoader.getResourceAsStream("config.properties")
    propertiesStream?.use {
        properties.load(it)
    }
    return properties
}

fun getConnection(): Connection {
    val props = loadDatabaseConfig()
    val url = props.getProperty("db.url")
    val username = props.getProperty("db.user")
    val password = props.getProperty("db.password")
    return DriverManager.getConnection(url, username, password)
}

