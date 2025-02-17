plugins {
	kotlin("jvm") version "1.9.25"
	kotlin("plugin.spring") version "1.9.25"
	id("org.springframework.boot") version "3.4.2"
	id("io.spring.dependency-management") version "1.1.7"
	kotlin("plugin.jpa") version "1.9.25"
	id("org.jetbrains.kotlinx.dataframe") version "0.15.0"
}

group = "mikec"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

repositories {
	mavenCentral()
}

dependencies {
//	implementation("org.springframework.boot:spring-boot-starter-batch")
	implementation("org.springframework.boot:spring-boot-starter-cache")
	implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
//	implementation("org.liquibase:liquibase-core")
	implementation("org.jetbrains.kotlinx:dataframe:0.15.0")
	implementation("org.jetbrains.kotlinx:dataframe-csv:0.15.0")
	implementation("com.opencsv:opencsv:5.10")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
//	testImplementation("org.springframework.boot:spring-boot-testcontainers")
//	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
//	testImplementation("org.springframework.batch:spring-batch-test")
	testImplementation("org.testcontainers:junit-jupiter")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
	runtimeOnly("com.mysql:mysql-connector-j")
}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}

allOpen {
	annotation("jakarta.persistence.Entity")
	annotation("jakarta.persistence.MappedSuperclass")
	annotation("jakarta.persistence.Embeddable")
}

tasks.withType<Test> {
	useJUnitPlatform()
	maxHeapSize = "8g"
}
