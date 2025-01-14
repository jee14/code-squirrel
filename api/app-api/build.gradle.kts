tasks.getByName("bootJar") {
    enabled = true
}

tasks.getByName("jar") {
    enabled = false
}

dependencies {
    implementation(libs.spring.boot.starter.web)
    implementation(libs.spring.boot.starter.aop)
    implementation(project(":domain"))
    implementation(project(":library:swagger"))

    testImplementation(libs.spring.boot.starter.test)
    testImplementation(project(":tests:api-docs"))
    testImplementation(project(":tests:test-helper"))
}

openapi3 {
    setServer("http://localhost:8080")
    title = "${project.property("openapi3Title")}"
    description = "${project.property("openapi3Description")}"
    version = "${project.property("openapi3DocsVersion")}"
    format = "yaml"
    outputFileNamePrefix = "${project.property("openapi3JsonName")}"
    outputDirectory = "${project.property("openapi3OutDirectory")}"
}
