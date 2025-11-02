pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    // Burada artık FAIL_ON_PROJECT_REPOS yerine PREFER_SETTINGS de kullanılabilir
    repositoriesMode.set(RepositoriesMode.PREFER_SETTINGS)
    repositories {
        google()       // Android ve Hilt kütüphaneleri
        mavenCentral() // Javapoet ve diğer üçüncü parti kütüphaneler
    }
}

rootProject.name = "Mini Weather Fetcher"
include(":app")
include(":bookapp")
