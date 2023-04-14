plugins {
    `java-library`
}

group = "duzhinsky"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-configuration-processor:2.7.10")
    implementation("org.springframework.boot:spring-boot-starter:2.7.10")

    implementation("com.yandex.cloud:java-sdk-services:2.6.0")

    implementation("io.grpc:grpc-netty-shaded:1.54.0")

    compileOnly("org.projectlombok:lombok:1.18.22")
    annotationProcessor("org.projectlombok:lombok:1.18.22")
    testCompileOnly("org.projectlombok:lombok:1.18.22")
    testAnnotationProcessor("org.projectlombok:lombok:1.18.22")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}