import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.compose.reload.ComposeHotRun
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl


plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.hotReload)
    id("org.openjfx.javafxplugin") version "0.1.0"
}

kotlin {
    jvm("desktop"){
        withJava()
    }
    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        moduleName = "composeApp"
        browser {
            val rootDirPath = project.rootDir.path
            val projectDirPath = project.projectDir.path
        }
        binaries.executable()
    }

    tasks.register<ComposeHotRun>("runHot") {
        mainClass.set("org.ferdi.money.MainKt")
    }

    sourceSets {
        val desktopMain by getting
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(libs.androidx.lifecycle.viewmodel)
            implementation(libs.androidx.lifecycle.runtime.compose)
            implementation(compose.materialIconsExtended)
            implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.6.1")
            implementation("org.jetbrains.androidx.lifecycle:lifecycle-viewmodel-compose:2.8.2")
            implementation("org.kodein.emoji:emoji-kt:2.2.0")
            implementation("org.kodein.emoji:emoji-compose-m3:2.0.1")
            implementation(compose.materialIconsExtended)
        }
        desktopMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation(libs.kotlinx.coroutines.swing)
            implementation(compose.material3)
        }
    }
}

compose.desktop {
    application {
        mainClass = "org.ferdi.money.MainKt"
        jvmArgs("--add-opens", "java.desktop/sun.awt=ALL-UNNAMED")
        jvmArgs("--add-opens", "java.desktop/java.awt.peer=ALL-UNNAMED") // recommended but not necessary
        buildTypes.release.proguard {
            obfuscate.set(true)
            optimize.set(true)
            version.set("7.5.0")
            configurationFiles.from(project.file("proguard.pro"))
        }
        if (System.getProperty("os.name").contains("Mac")) {
            jvmArgs("--add-opens", "java.desktop/sun.lwawt=ALL-UNNAMED")
            jvmArgs("--add-opens", "java.desktop/sun.lwawt.macosx=ALL-UNNAMED")
        }
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "Ferdialif Counter Money"
            packageVersion = "1.0.0"
        }
    }
}
