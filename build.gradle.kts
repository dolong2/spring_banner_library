import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id ("maven-publish")
    id("org.springframework.boot") version "2.7.5"
    id("io.spring.dependency-management") version "1.1.0"
    kotlin("jvm") version "1.7.22"
    kotlin("plugin.spring") version "1.7.22"
    application
}

group = "library"
version = "1.0.0-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

application{
    mainClass.set("library.banner.BannerGenerator")
}

publishing {
    repositories {
        mavenLocal()
    }

    publications {
        register("mavenJava", MavenPublication::class.java){
            from(components["java"])
        }
    }
}
