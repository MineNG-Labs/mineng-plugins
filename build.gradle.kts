plugins {
    kotlin("jvm") version "1.9.23"
}

group = "br.com.mineng"
version = "1.0.0"

allprojects {
    repositories {
        mavenCentral()
        maven {
            name = "papermc-repo"
            url = uri("https://repo.papermc.io/repository/maven-public/")
        }
        maven {
            name = "sonatype"
            url = uri("https://oss.sonatype.org/content/groups/public/")
        }
        maven {
            name = "codemc"
            url = uri("https://repo.codemc.org/repository/maven-public/")
        }
    }

    tasks.withType<Jar> {
        destinationDirectory = File("$rootDir/build/mcplugins")
    }
}

tasks.jar {
    enabled = false
}