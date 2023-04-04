plugins {
    `java-library`
}

group = "ru.duzhinsky.lockbox-client"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    compileOnly("com.yandex.cloud:java-sdk-auth:2.6.0")
    compileOnly(project(":lockbox-client-core"))
    compileOnly("org.springframework:spring-beans:5.2.23.RELEASE")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}