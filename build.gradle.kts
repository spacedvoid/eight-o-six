plugins {
	kotlin("jvm") version "2.0.20"
}

group = "io.github.spacedvoid"
version = "1.0.0"

repositories {
	mavenCentral()
}

dependencies {
	compileOnly("org.jetbrains.dokka:dokka-core:1.9.20")
	implementation("org.jetbrains.dokka:dokka-base:1.9.20")
	testImplementation(kotlin("test"))
}

tasks.test {
	useJUnitPlatform()
}

kotlin {
	jvmToolchain(21)
}
