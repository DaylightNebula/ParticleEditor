package daylightnebula.particle.editor.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import daylightnebula.particle.editor.BasicColors

object FileViewer {

    val views = mutableStateListOf<FileView>()
    var selectedView by mutableStateOf<FileView?>(null)

    @Composable
    fun drawFileViewer() {
        // box containing everything
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 5.dp, end = 5.dp)
        ) {
            Column {
                // bar of open files
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp)
                        .padding(bottom = 5.dp)
                ) {
                    // create an item for each file view
                    items(views) { view ->

                        Button(
                            onClick = { selectedView = view },
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = if (view == selectedView) BasicColors.highlightedElementBackground else BasicColors.elementBackground
                            ),
                            modifier = Modifier
                                .padding(end = 5.dp)
                        ) {
                            Row {
                                Text(
                                    text = view.name,
                                    color = view.nameColor
                                )
                                IconButton(
                                    onClick = {
                                        views.remove(view)
                                        if (selectedView == view)
                                            selectedView = views.firstOrNull()
                                    },
                                    modifier = Modifier
                                        .width(18.dp)
                                        .height(18.dp)
                                        .align(Alignment.CenterVertically)
                                        .padding(start = 5.dp)
                                ) {
                                    Icon(Icons.Filled.Close, null, tint = BasicColors.foreground)
                                }
                            }
                        }
                    }
                }

                // create box for the plugins to draw into
                if (selectedView != null)
                    drawFileView(selectedView!!)
            }
        }
    }

    @Composable
    fun drawFileView(view: FileView) {
        val scrollState = rememberLazyListState()
        LazyColumn(
            state = scrollState,
            modifier =
                Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(5.dp))
                    .background(BasicColors.elementBackground)
        ) {
            item {
                Text(view.text, color = BasicColors.foreground)
            }
        }
    }
}
data class FileView(
    val name: String,
    val path: String,
    val nameColor: Color,
    val text: String,
)