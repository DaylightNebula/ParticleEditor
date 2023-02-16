package daylightnebula.particle.editor

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import daylightnebula.particle.editor.filetree.FileTreePage
import daylightnebula.particle.editor.pages.FileViewer
import daylightnebula.particle.editor.pages.TopBar
import java.io.File

@Composable
fun App() {
    val targetFile by remember { mutableStateOf(File("D:\\projectstream\\WebStreamEngine - Copy for particle testing")) } // todo default to nothing

    Box(modifier = Modifier.fillMaxSize().background(BasicColors.background)) {
        Column {
            TopBar.drawTopBar()
            Row {
                FileTreePage.drawFileTree(targetFile)
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
