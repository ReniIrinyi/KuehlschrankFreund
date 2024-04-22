plugins {
    kotlin("jvm") version "1.9.23";
}


group = "org.example"
version = "1.0-SNAPSHOT"

allprojects{
    repositories {
        mavenCentral()
    }
}
subprojects{
    apply(plugin = "kotlin")

    dependencies {
        implementation(kotlin("stdlib-jdk8"))
    }


}

tasks.register("runBoth") {
    dependsOn(":frontend:ng_serve", "api:run")
    doLast {
        println("Starting both frontend and backend...")
    }
}

tasks.register("runApi"){
    dependsOn("api:run")
    doLast {
        println("Starting backend...")
    }
}

tasks.register("runFrontend"){
    dependsOn(":frontend:ng_serve")
    doLast{
        println("Starting frontend...")
    }
}

tasks.register("stopApi") {
    doLast {
        val file = File("api.pid")
        if (file.exists()) {
            val pid = file.readText().trim()
            exec {
                commandLine("bash", "-c", "kill $pid")
            }
            println("Backend server stopped.")
            file.delete()
        } else {
            println("No PID found for backend server.")
        }
    }
}

tasks.register("stopFrontend") {
    doLast {
        try {
            exec {
                commandLine("cmd", "/c", "taskkill", "/F", "/IM", "node.exe", "/T")
            }
            println("Frontend server stopped.")
        } catch (e: Exception) {
            println("Failed to stop frontend server: ${e.message}")
        }
    }
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}