plugins {
    kotlin("jvm") version "1.5.21"
}

group = "org.doni"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.bouncycastle:bcpkix-jdk15on:1.69")
    implementation(kotlin("stdlib"))
}