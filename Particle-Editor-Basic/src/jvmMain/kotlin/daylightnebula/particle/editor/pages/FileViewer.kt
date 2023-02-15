package daylightnebula.particle.editor.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import daylightnebula.particle.editor.BasicColors

object FileViewer {

    private val views = mutableListOf(
        FileView(
            "bob.kt",
            BasicColors.foreground,
            "Hi my name\nis bob."
        ),
        FileView(
            "helloWorld.kt",
            BasicColors.foreground,
            """
                fun main() {
                    println("Hello world!")
                }
            """.trimIndent()
        )
    )

    @Composable
    fun drawFileViewer() {
        var currentView by remember { mutableStateOf(views.first()) }
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
                            onClick = { currentView = view },
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = if (view == currentView) BasicColors.highlightedElementBackground else BasicColors.elementBackground
                            ),
                            modifier = Modifier
                                .padding(end = 5.dp)
                        ) {
                            Text(
                                text = view.name,
                                color = view.nameColor
                            )
                        }
                    }
                }

                // create box for the plugins to draw into
                drawFileView(currentView)
            }
        }
    }

    @Composable
    fun drawFileView(view: FileView) {
        Box(
            modifier =
                Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(5.dp))
                    .background(BasicColors.elementBackground)
        ) {
            Text(view.text, color = BasicColors.foreground)
        }
    }
}
data class FileView(
    val name: String,
    val nameColor: Color,
    val text: String,
)