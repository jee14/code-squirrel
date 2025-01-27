rootProject.name = "code-squirrel"

pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    @Suppress("UnstableApiUsage")
    repositories {
        mavenCentral()
        maven {
            url = uri("https://repo.spring.io/milestone")
        }
        gradlePluginPortal()
    }
}

include(
    "api:app-api",
)
include(
    "domain",
)
include(
    "library:swagger",
)
include(
    "tests:api-docs",
    "tests:test-helper"
)
