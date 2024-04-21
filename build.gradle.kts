plugins {
    kotlin("multiplatform") version "1.9.23"
}

group = "com.tanelso2"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

kotlin {
    js {
        browser {
        }
        binaries.executable()
    }
    sourceSets {
       val jsMain by getting {
           resources.srcDir("include")
       }
    }
}
