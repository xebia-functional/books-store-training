plugins {
    id("java")
    alias(libs.plugins.spotless)
    application
}

group = "com.xebia"
version = "1.0-SNAPSHOT"

application {
    mainClass = "com.xebia.App.App"
}


repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform(libs.junit.jupiter.bom))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation(libs.flyway)
    implementation(libs.postgresql)
    testImplementation(libs.testcontatiners)
}

tasks.test {
    useJUnitPlatform()
}

spotless {
    java {
        googleJavaFormat()
    }
}

tasks.named<JavaExec>("run") {
    standardInput = System.`in`
}
