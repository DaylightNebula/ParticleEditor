package daylightnebula.particle.editor.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import daylightnebula.particle.editor.BasicColors

object TopBar {
    @Composable
    fun drawTopBar() {
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
                        IconButton(onClick = { openProject() }) {
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
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp)
                        .clip(RoundedCornerShape(5.dp))
                        .background(BasicColors.elementBackground)
                ) {

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