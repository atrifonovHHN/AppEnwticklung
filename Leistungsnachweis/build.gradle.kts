plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation ("com.squareup.okhttp3:mockwebserver:4.10.0")
    implementation ("com.google.code.gson:gson:2.8.9")

}

tasks.test {
    useJUnitPlatform()
}