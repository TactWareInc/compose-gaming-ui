package net.tactware.gamingui

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import net.tactware.gamingui.demo.SciFiDemoApp

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "KotlinProject",
    ) {
         SciFiDemoApp()
    }
}