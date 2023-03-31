plugins {
    `java-library`
}

group = "ru.duzhinsky"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":lockbox-client-core"))
    implementation("com.yandex.cloud:java-sdk-auth:2.6.0")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.14.1")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}