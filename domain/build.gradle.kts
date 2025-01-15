dependencies {
    compileOnly(libs.spring.context)
    implementation(libs.spring.tx)
    implementation(platform(libs.spring.ai.bom))
    implementation(libs.spring.ai.openai)
}
