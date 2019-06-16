group = "com.merricklabs.drogon"

repositories {
    mavenCentral()
    jcenter()
}

plugins {
    id("de.fayard.buildSrcVersions") version "0.3.2"
    kotlin("jvm") version "1.3.31"
    id("com.github.johnrengelman.shadow") version "4.0.4"
}

dependencies {
    implementation(Libs.okhttp)
    implementation("org.jetbrains.kotlin:kotlin-reflect:${Versions.org_jetbrains_kotlin}")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.slf4j:slf4j-api:1.7.25")
    implementation("org.slf4j:slf4j-jdk14:1.7.26")
    implementation("io.github.microutils:kotlin-logging:1.6.10")
    implementation(Libs.koin_core)
    implementation("com.amazonaws:aws-lambda-java-core:1.1.0")
    implementation("com.amazonaws:aws-lambda-java-log4j2:1.0.0")
    implementation("com.amazonaws:aws-lambda-java-events:2.0.1")
    implementation("com.amazonaws:aws-java-sdk-dynamodb:1.11.527")
    implementation("com.fasterxml.jackson.core:jackson-core:2.8.5")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.8.5")
    implementation("com.fasterxml.jackson.core:jackson-annotations:2.8.5")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.8.5")

    testImplementation(Libs.testng)
    testImplementation(Libs.koin_test)
    testImplementation(Libs.kotlintest_runner_junit5)
}

val deployDev = tasks.create<Exec>("deployDev") {
    commandLine = listOf("serverless", "deploy", "--stage=dev")
}

val deployPrd = tasks.create<Exec>("deployPrd") {
    commandLine = listOf("serverless", "deploy", "--stage=prd")
}

// Alias for deploy dev
val deploy = tasks.create("deploy")
deploy.dependsOn(deployDev)

deployDev.dependsOn(tasks.getByName("shadowJar"))
deployPrd.dependsOn(tasks.getByName("shadowJar"))

tasks.test {
    useTestNG()
}