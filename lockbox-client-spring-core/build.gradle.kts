plugins {
    `java-library`
}

group = "ru.duzhinsky"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    api("ru.duzhinsky:lockbox-client-core:${version}")
    api("com.yandex.cloud:java-sdk-auth:2.6.0")
    implementation("org.springframework:spring-context:5.3.18")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}