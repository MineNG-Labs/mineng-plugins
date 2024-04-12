plugins {
    kotlin("jvm") version "1.9.23"
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:${property("versions.paper")}")
    implementation("com.github.shynixn.mccoroutine:mccoroutine-bukkit-api:${property("versions.mccoroutine")}")
    implementation("com.github.shynixn.mccoroutine:mccoroutine-bukkit-core:${property("versions.mccoroutine")}")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.1-Beta")

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
    val props = mapOf(
        "version" to rootProject.version
    )

    inputs.properties(props)

    filteringCharset = "UTF-8"

    filesMatching("plugin.yml") {
        expand(props)
    }
}

tasks.jar {
    archiveFileName.set("MineNG.jar")
}