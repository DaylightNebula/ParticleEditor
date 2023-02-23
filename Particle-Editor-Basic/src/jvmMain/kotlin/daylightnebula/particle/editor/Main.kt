package daylightnebula.particle.editor

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import daylightnebula.particle.editor.filetree.FileTreePage
import daylightnebula.particle.editor.pages.FileViewer
import daylightnebula.particle.editor.pages.TopBar
import daylightnebula.particle.editor.pages.TopBarButton
import daylightnebula.particle.editor.pages.TopBarButtonOption
import daylightnebula.particle.editor.plugins.PluginManager
import java.io.File

@Composable
fun App() {
    var targetFile by remember { mutableStateOf<File>(SettingsManager.getDirectory()) }

    Box(modifier = Modifier.fillMaxSize().background(BasicColors.background)) {
        Column {
            TopBar.drawTopBar{
                if (it != null) {
                    targetFile = it
                    FileViewer.selectedView = null
                    FileViewer.views.clear()
                }
            }
            Row {
                FileTreePage.drawFileTree(targetFile)
                FileViewer.drawFileViewer()
            }
        }
    }
}

fun main() = application {
    PluginManager.init(File(System.getProperty("user.dir"), "plugins").apply { println(this.path) })
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}
