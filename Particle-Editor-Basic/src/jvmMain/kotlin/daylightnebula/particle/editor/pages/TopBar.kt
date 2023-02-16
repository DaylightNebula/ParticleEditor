package daylightnebula.particle.editor.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.awt.ComposeWindow
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.AwtWindow
import androidx.compose.ui.window.Window
import daylightnebula.particle.editor.BasicColors
import java.awt.FileDialog
import java.awt.Frame
import java.io.File
import javax.swing.JFileChooser

object TopBar {

    private val buttons = mutableStateListOf<TopBarButton>()

    fun addButton(button: TopBarButton) {
        buttons.add(button)
    }

    @Composable
    fun drawTopBar(onFileSelectCallback: (file: File?) -> Unit) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
                .padding(5.dp)
        ) {
            Row {
                // create a reload of default buttons
                LazyRow(
                    modifier = Modifier
                        .clip(RoundedCornerShape(5.dp))
                        .background(BasicColors.elementBackground)
                ) {
                    item {
                        IconButton(onClick = {
                            val chooser = JFileChooser()
                            chooser.fileSelectionMode = JFileChooser.DIRECTORIES_ONLY
                            chooser.showOpenDialog(ComposeWindow())
                            onFileSelectCallback(chooser.selectedFile)
                        }) {
                            Icon(
                                Icons.Filled.List,
                                "Open Project",
                                tint = BasicColors.foreground
                            )
                        }
                        IconButton(onClick = { reloadPlugins() }) {
                            Icon(
                                Icons.Filled.Refresh,
                                "Reload Plugins",
                                tint = BasicColors.foreground
                            )
                        }
                    }
                }

                // put a spacer between the two elements
                Spacer(Modifier.padding(horizontal = 2.5.dp))

                // create row for the plugin buttons
                LazyRow (
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp)
                        .clip(RoundedCornerShape(5.dp))
                        .background(BasicColors.elementBackground)
                ) {
                    items(buttons) { button ->
                        IconButton(
                            onClick = { button.onClick() },
                            modifier = Modifier
                                .fillMaxHeight()
                                .width(40.dp)
                        ) {
                            Icon(button.icon, contentDescription = null, tint = BasicColors.foreground)
                        }
                    }
                }
            }
        }
    }

    private fun openProject() {
        println("TODO Open project")
    }

    private fun reloadPlugins() {
        println("TODO reload plugins")
    }
}
data class TopBarButton(val icon: ImageVector, val onClick: () -> Unit)