plugins {
    id("java")
    id("application")
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.10.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.10.0")
    testImplementation("org.mockito:mockito-core:4.11.0")
    implementation("com.google.code.gson:gson:2.10.1")
}

application {
    mainClass.set("service.UseTimeCalculator")
}
tasks.withType<Jar> {
    manifest {
        attributes(
            "Main-Class" to "service.UseTimeCalculator"
        )
    }
}


tasks.test {
    useJUnitPlatform()
}

tasks {
    shadowJar {
        archiveBaseName.set("app")              // Set JAR's base name
        archiveClassifier.set("")               // Set no classifier to replace the default JAR
        archiveVersion.set(version.toString())  // Include version in JAR name
        manifest {
            attributes["Main-Class"] = "de.hhn.aib3.Main" // Main class
        }
    }
}