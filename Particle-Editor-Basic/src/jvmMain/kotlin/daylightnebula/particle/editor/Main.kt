package daylightnebula.particle.editor

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

@Composable
fun drawTopBar() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
            .padding(5.dp)
            .clip(RoundedCornerShape(5.dp))
            .background(BasicColors.elementBackground)
    ) {

    }
}

@Composable
fun drawFileTree() {
    Box(
        modifier = Modifier
            .fillMaxHeight()
            .width(300.dp)
            .padding(start = 5.dp, bottom = 5.dp)
            .clip(RoundedCornerShape(5.dp))
            .background(BasicColors.elementBackground)
    ) {

    }
}

@Composable
fun drawTabs() {

}

@Composable
fun App() {
    Box(modifier = Modifier.fillMaxSize().background(BasicColors.background)) {
        Column {
            drawTopBar()
            Row {
                drawFileTree()
                drawTabs()
            }
        }
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}
