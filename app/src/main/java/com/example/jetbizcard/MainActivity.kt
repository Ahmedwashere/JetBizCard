package com.example.jetbizcard

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetbizcard.ui.theme.JetBizCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetBizCardTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    BizCard()
                }
            }
        }
    }
}

@Composable
fun BizCard() {
    val buttonClickedState = remember {
        mutableStateOf(false)
    }
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Card(
            modifier = Modifier
                .width(200.dp)
                .height(390.dp)
                .padding(12.dp),
            shape = RoundedCornerShape(corner = CornerSize(15.dp)),
            elevation = CardDefaults.cardElevation(4.dp),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CreateImageProfile()
                // Start indent can be achieved by using padding(start=dp we want to indent)
                HorizontalDivider()
                ProfileInformation()
                Button(onClick = {
                    Log.d("Clicked", "BizCard: Clicked!")
                    // We need to control the toggling of the field
                    buttonClickedState.value = !buttonClickedState.value
                }, shape = RoundedCornerShape(5.dp)) {
                    Text(
                        "Portfolio",
                        style = MaterialTheme.typography.labelLarge
                    )
                }
                if (buttonClickedState.value) {
                    Content()
                }
            }
        }
    }

}

@Composable
private fun ProfileInformation() {
    Column(modifier = Modifier.padding(5.dp)) {

        Text(
            text = "Ahmed E.",
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.headlineLarge
        )

        Text(text = "Android Compose Novice")
        Text(text = "@AhmedwasHere", color = Color.Black)
    }
}

@Composable
fun CreateImageProfile(modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier
            .size(150.dp)
            .padding(5.dp),
        shape = CircleShape,
        border = BorderStroke(0.5.dp, Color.LightGray),
        shadowElevation = 4.dp,
        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
    ) {
        Image(
            painter = painterResource(id = R.drawable.me),
            contentDescription = "Profile Picture",
            modifier = modifier.size(135.dp),
            contentScale = ContentScale.Crop
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CreateBizCardPreview() {
    JetBizCardTheme {
        BizCard()
    }
}

@Composable
fun Content() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp)
    ) {
        Surface(
            modifier = Modifier
                .padding(3.dp)
                .fillMaxSize(),
            shape = RoundedCornerShape(corner = CornerSize(6.dp)),
            border = BorderStroke(width = 2.dp, color = Color.Blue)
        ) {
            Portfolio(
                data = listOf(
                    "Toxic Comment Classification",
                    "SOUL automation",
                    "MILP automation",
                    "Project 4",
                    "Project 5",
                )
            )
        }
    }
}

@Composable
fun Portfolio(data: List<String>) {
    // LazyColumn lets you scroll and also get recyclable views!
    // At a high level, we can iterate through a list and display it using
    // Lazy column and then inside it LazyListScope.items(List)
    // Then in the lambda of items, we can do whatever we want to each individual
    // element
    LazyColumn {
        items(data) { item: String ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(13.dp),
                shape = RectangleShape,
            ) {
                Row(
                    modifier = Modifier
                        .padding(8.dp)
                        .background(MaterialTheme.colorScheme.surface)
                        .padding(16.dp)
                        .fillMaxWidth()
                ) {
                    CreateImageProfile(Modifier.size(100.dp))
                    Column(modifier = Modifier.padding(7.dp).align(alignment = Alignment.CenterVertically)) {
                        Text(text = item, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleLarge)
                        Text("This project is amazing!")
                    }
                }

            }

        }
    }
}

@Preview
@Composable
fun ContentPreview() {
    JetBizCardTheme {
        Content()
    }
}