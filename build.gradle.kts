plugins {
	val kotlinVersion: String by System.getProperties()

	kotlin("jvm") version kotlinVersion
	kotlin("plugin.serialization") version kotlinVersion
	kotlin("plugin.spring") version kotlinVersion
	id("org.springframework.boot") version "3.1.5"
	id("io.spring.dependency-management") version "1.1.3"
}

group = "me.emyar"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

val exposedVersion: String by project
dependencies {
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-web")

	implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")

	implementation("org.jetbrains.exposed:exposed-spring-boot-starter:$exposedVersion")

	implementation("com.h2database:h2:2.2.224")

	annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.security:spring-security-test")
}

kotlin {
	jvmToolchain(17)
	compilerOptions.freeCompilerArgs.add("-Xjsr305=strict")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
