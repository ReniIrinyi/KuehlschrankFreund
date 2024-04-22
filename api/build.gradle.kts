plugins {
    kotlin("jvm") version "1.9.23";
   application
}

group = "org.example"
version = "1.0-SNAPSHOT"

application{
    mainClass.set("org.example.MainKt")
}


repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.7.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.7.0")
    implementation("org.mariadb.jdbc:mariadb-java-client:2.7.2") // MariaDB JDBC driver
}



tasks.named<JavaExec>("run") {
    doFirst {
        println("Classpath: ${classpath.files.joinToString("\n")}")
    }
    classpath = sourceSets.main.get().runtimeClasspath
    mainClass.set("org.example.MainKt");
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}