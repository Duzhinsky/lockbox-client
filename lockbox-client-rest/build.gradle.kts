plugins {
    `java-library`
}

group = "ru.duzhinsky.lockbox-client"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":lockbox-client-core"))
    implementation("com.konghq:unirest-java:3.14.2")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.14.2")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}