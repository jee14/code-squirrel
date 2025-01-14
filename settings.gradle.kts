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
