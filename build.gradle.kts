plugins {
    id("java")
    alias(libs.plugins.spotless)
}

group = "com.xebia"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform(libs.junit.jupiter.bom))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation(libs.flyway)
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

