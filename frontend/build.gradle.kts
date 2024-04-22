import com.github.gradle.node.NodeExtension
import com.github.gradle.node.npm.task.NpmTask

plugins {
  id("com.github.node-gradle.node") version "3.1.1"
}

node {
  version = "18.13.0"
  npmVersion = "8.19.3"
  download = true
}

configure<NodeExtension> {
  npmVersion.set("8.19.3")
  download = true
}

tasks.register<NpmTask>("npm_install") {
  args.set(listOf("install"))
}

tasks.register<NpmTask>("ng_build") {
  dependsOn("npm_install")
  args.set(listOf("run", "build"))
}

tasks.register<NpmTask>("ng_serve") {
  dependsOn("npm_install")
  args.set(listOf("run", "start"))
}
