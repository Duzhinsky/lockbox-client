plugins {
    `java-library`
}

group = "ru.duzhinsky"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-configuration-processor:2.7.10")
    implementation("org.springframework.boot:spring-boot-starter:2.7.10")
    api("ru.duzhinsky:lockbox-client-spring-core:1.0")
    api("ru.duzhinsky:lockbox-client-rest:1.0")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}