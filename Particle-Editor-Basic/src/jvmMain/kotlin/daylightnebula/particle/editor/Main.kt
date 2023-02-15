package daylightnebula.particle.editor

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import daylightnebula.particle.editor.pages.FileTree
import daylightnebula.particle.editor.pages.FileViewer
import daylightnebula.particle.editor.pages.TopBar

@Composable
fun App() {
    Box(modifier = Modifier.fillMaxSize().background(BasicColors.background)) {
        Column {
            TopBar.drawTopBar()
            Row {
                FileTree.drawFileTree()
                FileViewer.drawFileViewer()
            }
        }
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}
