/*
 * Copyright (c) 2022 xf8b.
 *
 * This file is part of forrest-game-experimental.
 *
 * forrest-game-experimental is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * forrest-game-experimental is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with forrest-game-experimental. If not, see <https://www.gnu.org/licenses/>.
 */

import org.jetbrains.kotlin.gradle.tasks.KotlinCompile as CompileKotlin

plugins {
    java // language
    kotlin("jvm") version "1.6.10" // language
    application // running
}

// package details
group = "io.github.xf8b"
base.archivesName.set("forrest-game-experimental")
version = "0.1.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.6.10") // kotlin reflection
    implementation("ch.qos.logback:logback-classic:1.2.10") // logging

    testImplementation(platform("org.junit:junit-bom:5.8.2")) // testing
    testImplementation("org.junit.jupiter:junit-jupiter") // testing
}

java {
    toolchain {
        // require java 17
        languageVersion.set(JavaLanguageVersion.of(17))
        vendor.set(JvmVendorSpec.ADOPTIUM)
    }

    // enable module-path inferring
    modularity.inferModulePath.set(true)
}

application {
    mainModule.set("io.github.xf8b.fge")
    mainClass.set("io.github.xf8b.fge.Main")
}

tasks {
    withType<CompileKotlin>().configureEach {
        kotlinOptions {
            jvmTarget = "17"
            languageVersion = "1.6"
        }
    }

    jar {
        manifest {
            attributes(
                "Manifest-Version" to "1.0",
                "Main-Class" to "io.github.xf8b.fge.Main"
            )
        }
    }

    compileJava {
        // set module version to project version
        options.javaModuleVersion.set(provider { project.version.toString() })
    }

    test {
        // use junit for testing
        useJUnitPlatform()
    }
}