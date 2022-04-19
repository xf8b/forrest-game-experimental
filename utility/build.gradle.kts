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

import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    java // language
    kotlin("jvm") version "1.6.20" // language
    `java-library` // library
}

// package details
group = "io.github.xf8b"
base.archivesName.set("forrest-game-experimental-utility")
version = "0.1.0"

repositories {
    mavenCentral()
}

dependencies {
    // main dependencies
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.6.21") // Kotlin reflection
    api("ch.qos.logback:logback-classic:1.2.11") // logging

    // test dependencies
    testImplementation(platform("org.junit:junit-bom:5.8.2")) // JUnit bill of materials
    testImplementation("org.junit.jupiter:junit-jupiter") // JUnit Jupiter
}

kotlin {
    jvmToolchain {
        this as JavaToolchainSpec

        languageVersion.set(JavaLanguageVersion.of(17)) // require Java 17
        vendor.set(JvmVendorSpec.ADOPTIUM) // get a JDK from Adoptium if no JDK was found
    }
}

tasks {
    withType<KotlinCompile>().configureEach {
        kotlinOptions {
            jvmTarget = "17"
            languageVersion = "1.6"
        }
    }

    withType<JavaCompile>().configureEach {
        options.compilerArgs.addAll(
            listOf(
                "--patch-module", "io.github.xf8b.fge.utility=${sourceSets["main"].output.asPath}"
            )
        )
    }

    compileJava {
        options.javaModuleVersion.set(provider { project.version as String })
    }

    test {
        useJUnitPlatform() // use junit for testing
    }
}