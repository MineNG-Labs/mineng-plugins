plugins {
    kotlin("jvm") version "1.9.23"
}

dependencies {
    val paperVersion = "1.20.4-R0.1-SNAPSHOT"
    val koinVersion = "3.5.3"

    compileOnly("io.papermc.paper:paper-api:$paperVersion")
    implementation("io.insert-koin:koin-core:$koinVersion")
    implementation(kotlin("stdlib-jdk8"))
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
        "version" to version
    )

    inputs.properties(props)

    filteringCharset = "UTF-8"

    filesMatching("plugin.yml") {
        expand(props)
    }
}

tasks.jar {
    archiveFileName.set("${project.name}.jar")
}