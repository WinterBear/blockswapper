import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    kotlin("jvm") version "2.4.21"
    id("com.gradleup.shadow") version "9.6.1"
}

group = "dev.snowcave"
version = "1.4.0"
val javaVersion = 25


repositories {
    mavenCentral()
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    testImplementation(kotlin("test"))
    testImplementation("io.mockk:mockk:1.13.13")
    compileOnly("dev.folia:folia-api:26.2.build.6-beta")
    //compileOnly("org.spigotmc:spigot-api:26.3-R0.1-SNAPSHOT")
    implementation("org.bstats:bstats-bukkit:3.0.2")
}

tasks.withType<JavaCompile>().configureEach {
    options.compilerArgs.add("-source $javaVersion")
    options.compilerArgs.add("-target $javaVersion")
}

tasks.withType<ShadowJar> {
    archiveFileName = project.name + "-" + project.version + ".jar"
    dependencies {
        exclude(dependency("org.jetbrains.kotlin:.*"))
        exclude(dependency("org.jetbrains:.*"))
        exclude(dependency("org.intellij:.*"))
    }
    relocate("org.bstats", "dev.snowcave.blockswapper.metrics")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(javaVersion)
}