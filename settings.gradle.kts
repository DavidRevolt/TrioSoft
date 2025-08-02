pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "App"
include(":app")
include(":feature:home")
include(":core:data")
include(":core:network")
include(":core:model")
include(":core:designsystem")
include(":feature:signup")
include(":feature:login")
include(":core:database")
