plugins {
    id("java")
    alias(libs.plugins.spotless)
    id("org.flywaydb.flyway") version "9.5.0"
}

group = "com.xebia"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform(libs.junit.jupiter.bom))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("org.flywaydb:flyway-core:11.3.2")
    implementation("org.postgresql:postgresql:42.5.0")



}

tasks.test {
    useJUnitPlatform()
}

spotless {
    java {
        googleJavaFormat()
    }
}

