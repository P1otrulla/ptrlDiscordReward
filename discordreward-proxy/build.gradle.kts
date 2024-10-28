plugins {
    id("java")
    alias(libs.plugins.shadow)
}

group = "dev.piotrulla"
version = "1.0.0"

repositories {
    mavenCentral()

    maven("https://storehouse.okaeri.eu/repository/maven-public/")
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://repo.eternalcode.pl/releases/")
}

dependencies {
    compileOnly(libs.velocity.api)
    annotationProcessor(libs.velocity.api)

    implementation(libs.bundles.okaeri.configs)
    implementation(libs.litecommands)
}