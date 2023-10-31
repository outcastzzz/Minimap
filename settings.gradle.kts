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
        maven {
            setUrl("https://jitpack.io")
            setUrl("https://dl.bintray.com/s1m0nw1/KtsRunner")
        }
    }
}

rootProject.name = "My Application"
include(":app")
 