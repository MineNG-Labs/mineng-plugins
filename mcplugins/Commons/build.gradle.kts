plugins {
    kotlin("jvm") version "1.9.23"
}

dependencies {
    // Paper
    api("io.papermc.paper:paper-api:${property("versions.paper")}")

    // APIs
    api("dev.jorel:commandapi-bukkit-core:${property("versions.commandapi")}")

    // Kotlin-related
    api(kotlin("stdlib-jdk8"))
    api("io.insert-koin:koin-core:${property("versions.koin")}")

    // Kotlin Coroutines
    api("com.github.shynixn.mccoroutine:mccoroutine-bukkit-api:${property("versions.mccoroutine")}")
    api("com.github.shynixn.mccoroutine:mccoroutine-bukkit-core:${property("versions.mccoroutine")}")
    api("org.jetbrains.kotlinx:kotlinx-coroutines-core:${property("versions.coroutines")}")
}

val targetJavaVersion = 21

java {
    val javaVersion = JavaVersion.toVersion(targetJavaVersion)
    sourceCompatibility = javaVersion
    targetCompatibility = javaVersion
    if (JavaVersion.current() < javaVersion) {
        toolchain.languageVersion.set(JavaLanguageVersion.of(targetJavaVersion))
    }
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"

    if (targetJavaVersion >= 10 || JavaVersion.current().isJava10Compatible) {
        options.release.set(targetJavaVersion)
    }
}

tasks.processResources {
    val props = mutableMapOf(
        "version" to rootProject.version
    )

    properties.filter { it.key.startsWith("versions") }
        .map { it.key.removePrefix("versions.") to it.value }
        .forEach {
            props["versions_${it.first}"] = it.second as Any
        }

    inputs.properties(props)

    filteringCharset = "UTF-8"

    filesMatching("plugin.yml") {
        expand(props)
    }
}

tasks.jar {
    archiveFileName.set("MineNG.jar")
}