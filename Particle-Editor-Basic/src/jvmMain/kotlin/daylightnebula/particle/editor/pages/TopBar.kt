package daylightnebula.particle.editor.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.hoverable
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
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.AwtWindow
import androidx.compose.ui.window.Window
import daylightnebula.particle.editor.BasicColors
import daylightnebula.particle.editor.SettingsManager
import daylightnebula.particle.editor.plugins.PluginManager
import java.awt.FileDialog
import java.awt.Frame
import java.io.File
import javax.swing.JFileChooser

object TopBar {

    private val buttons = mutableStateListOf<TopBarButton>()

    fun reset() {
        buttons.clear()
    }

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
                        // draw open button
                        IconButton(onClick = {
                            // on click, open a file chooser
                            val chooser = JFileChooser()
                            chooser.fileSelectionMode = JFileChooser.DIRECTORIES_ONLY
                            chooser.showOpenDialog(ComposeWindow())
                            SettingsManager.setDirectory(chooser.selectedFile)
                            onFileSelectCallback(chooser.selectedFile)
                        }) {
                            Icon(
                                Icons.Filled.List,
                                "Open Project",
                                tint = BasicColors.foreground
                            )
                        }

                        // reload the plugins
                        IconButton(onClick = { PluginManager.reload() }) {
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
                        // drop down menu expanded tracker
                        var expanded by remember { mutableStateOf(false) }

                        // create column to stack everything
                        Column {
                            // create icon button
                            IconButton(
                                onClick = { expanded = !expanded /*button.onClick()*/ },
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .width(40.dp)
                            ) {
                                Icon(button.icon, contentDescription = null, tint = BasicColors.foreground)
                            }

                            // drop down menu
                            DropdownMenu(
                                expanded = expanded,
                                onDismissRequest = { expanded = false },
                                modifier = Modifier
                                    .background(BasicColors.highlightedElementBackground)
                            ) {
                                // for each given option, create a dropdown option for it
                                button.options.forEach { option ->
                                    DropdownMenuItem(
                                        onClick = { option.onClick() },
                                        modifier = Modifier
                                            .background(BasicColors.highlightedElementBackground)
                                            .wrapContentHeight()
                                            .height(20.dp)
                                            .fillMaxWidth()
                                    ) {
                                        Text(
                                            text = option.text,
                                            color = BasicColors.foreground,
                                            modifier = Modifier
                                                .clipToBounds(),
                                            softWrap = true,
                                            fontSize = 14.sp,
                                            overflow = TextOverflow.Ellipsis,
                                            maxLines = 1
                                        )
                                    }
                                }
                            }
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
data class TopBarButton(val icon: ImageVector, val options: Array<TopBarButtonOption>) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as TopBarButton

        if (icon != other.icon) return false
        if (!options.contentEquals(other.options)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = icon.hashCode()
        result = 31 * result + options.contentHashCode()
        return result
    }
}
data class TopBarButtonOption(val text: String, val onClick: () -> Unit)