package daylightnebula.particle.editor.filetree

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import daylightnebula.particle.editor.BasicColors
import java.io.File
import javax.swing.Icon

object FileTreePage {

    @Composable
    fun drawFileTree(targetFile: File) {
        val openDirectories = remember { mutableListOf<String>() }

        // render the tree
        val tree = FileTree(FileTreeFile(targetFile))

        Box(
            modifier = Modifier
                .fillMaxHeight()
                .width(300.dp)
                .padding(start = 5.dp, bottom = 5.dp, end = 5.dp)
                .clip(RoundedCornerShape(5.dp))
                .background(BasicColors.elementBackground)
        ) {
            val scrollState = rememberLazyListState()
            LazyColumn(
                state = scrollState,
                modifier = Modifier.fillMaxSize()
            ) {
                items(tree.items.size) { idx ->
                    Row(
                        modifier = Modifier
                            .wrapContentHeight()
                            .clickable { tree.items[idx].open() }
                            .padding(start = 12.dp * tree.items[idx].level)
                            .height(20.dp)
                            .fillMaxWidth()
                    ) {
                        val item = tree.items[idx]
                        val interactionSource = remember { MutableInteractionSource() }
                        val active by interactionSource.collectIsHoveredAsState()
                        FileItemIcon(
                            Modifier,//.align(Alignment.CenterVertically),
                            item
                        )
                        Text(
                            text = item.name,
                            color = BasicColors.foreground,
                            modifier = Modifier
                                //.align(Alignment.CenterVertically)
                                .clipToBounds()
                                .hoverable(interactionSource),
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

    @Composable
    private fun FileItemIcon(modifier: Modifier, model: FileTree.Item) = Box(modifier.size(24.dp).padding(4.dp)) {
        when (val type = model.type) {
            is FileTree.ItemType.Folder -> when {
                !type.canExpand -> Unit
                type.isExpanded -> Icon(
                    Icons.Default.KeyboardArrowDown, contentDescription = null, tint = BasicColors.foreground
                )
                else -> Icon(
                    Icons.Default.KeyboardArrowRight, contentDescription = null, tint = BasicColors.foreground
                )
            }
            is FileTree.ItemType.File -> when (type.ext) {
//            "kt" -> Icon(Icons.Default.Code, contentDescription = null, tint = Color(0xFF3E86A0))
//            "xml" -> Icon(Icons.Default.Code, contentDescription = null, tint = Color(0xFFC19C5F))
//            "txt" -> Icon(Icons.Default.Description, contentDescription = null, tint = Color(0xFF87939A))
//            "md" -> Icon(Icons.Default.Description, contentDescription = null, tint = Color(0xFF87939A))
//            "gitignore" -> Icon(Icons.Default.BrokenImage, contentDescription = null, tint = Color(0xFF87939A))
//            "gradle" -> Icon(Icons.Default.Build, contentDescription = null, tint = Color(0xFF87939A))
//            "kts" -> Icon(Icons.Default.Build, contentDescription = null, tint = Color(0xFF3E86A0))
//            "properties" -> Icon(Icons.Default.Settings, contentDescription = null, tint = Color(0xFF62B543))
//            "bat" -> Icon(Icons.Default.Launch, contentDescription = null, tint = Color(0xFF87939A))
                else -> Icon(Icons.Default.Build/*TextSnippet*/, contentDescription = null, tint = BasicColors.foreground)
            }
        }
    }
}
class TreeElement(val name: String, val icon: Icon, val path: String, val isDirectory: Boolean, val offset: Dp)