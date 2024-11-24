plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}
rootProject.name = "BlockSwapper"
include("src:main:javaaaaa")
findProject(":src:main:javaaaaa")?.name = "javaaaaa"
