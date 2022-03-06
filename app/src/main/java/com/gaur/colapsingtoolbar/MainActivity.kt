package com.gaur.colapsingtoolbar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gaur.colapsingtoolbar.ui.theme.ColapsingToolbarTheme
import me.onebone.toolbar.CollapsingToolbarScaffold
import me.onebone.toolbar.ScrollStrategy
import me.onebone.toolbar.rememberCollapsingToolbarScaffoldState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ColapsingToolbarTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainContent()
                }
            }
        }
    }
}

@Composable
fun MainContent() {

    val state = rememberCollapsingToolbarScaffoldState()

    CollapsingToolbarScaffold(
        modifier = Modifier,
        state = state,
        scrollStrategy = ScrollStrategy.EnterAlways,
        toolbar = {

            val textSize = (18 + (30 - 12) * state.toolbarState.progress).sp

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .pin()
                    .background(color = MaterialTheme.colors.primary)
            )


            Image(
                modifier= Modifier.fillMaxSize(),
                painter = painterResource(id = R.drawable.ic_collapsing), contentDescription = null,
                contentScale = ContentScale.Crop, alpha = if (textSize.value == 18f) 0f else 1f
            )

            Text(
                "Collapsing Toolbar",
                style = TextStyle(color = Color.White, fontSize = textSize),
                modifier = Modifier.padding(16.dp).road(whenCollapsed = Alignment.TopStart, whenExpanded = Alignment.BottomStart)
            )


        }) {


        LazyColumn(modifier= Modifier.fillMaxSize()){
            items(100){
                Card(modifier= Modifier
                    .fillMaxWidth()
                    .padding(8.dp)) {

                    Text(text = "This is card number $it",modifier= Modifier
                        .fillMaxWidth()
                        .padding(16.dp))

                }
            }
        }


    }


}

