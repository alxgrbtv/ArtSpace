package com.alxgrbdev.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alxgrbdev.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtSpaceLayout()
                }
            }
        }
    }
}

@Composable
fun ArtSpaceLayout() {
    var currentArtID by remember { mutableStateOf(1) }

    Column(
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp)
    ) {
        when (currentArtID) {
            1 -> {
                ArtImage(image = R.drawable.art_placeholder)
                CardDescription(title = R.string.art_1_title, author = R.string.art_1_author, year = R.string.art_1_year)
                Spacer(modifier = Modifier.height(36.dp))
                NavigationButtons(
                    onPrevClicked = { currentArtID = 3 },
                    onNextClicked = { currentArtID++ }
                )
            }
            2 -> {
                ArtImage(image = R.drawable.art_placeholder_2)
                CardDescription(title = R.string.art_2_title, author = R.string.art_2_author, year = R.string.art_2_year)
                Spacer(modifier = Modifier.height(36.dp))
                NavigationButtons(
                    onPrevClicked = { currentArtID-- },
                    onNextClicked = { currentArtID++ }
                )
            }
            3 -> {
                ArtImage(image = R.drawable.art_placeholder_3)
                CardDescription(title = R.string.art_3_title, author = R.string.art_3_author, year = R.string.art_3_year)
                Spacer(modifier = Modifier.height(36.dp))
                NavigationButtons(
                    onPrevClicked = { currentArtID-- },
                    onNextClicked = { currentArtID = 1 }
                )
            }
        }
    }
}

@Composable
fun ArtImage(@DrawableRes image: Int) {
    Image(
        painter = painterResource(id = image),
        contentDescription = null,
        modifier = Modifier
            .size(360.dp)
    )
}

@Composable
fun CardDescription(
    @StringRes title: Int,
    @StringRes author: Int,
    @StringRes year: Int
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(24.dp)
            .fillMaxWidth()
    ) {
        Text(text = stringResource(id = title), fontSize = 18.sp)
        Text(buildAnnotatedString { 
            withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                append(stringResource(id = author))
            }
            append(" ")
            withStyle(SpanStyle(fontWeight = FontWeight.Light)) {
                append(stringResource(id = year))
            }
        })
    }
}

@Composable
fun NavigationButtons(
    onPrevClicked: () -> Unit,
    onNextClicked: () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Button(onClick = onPrevClicked, modifier = Modifier.width(128.dp)) {
            Text(text = "Previous")
        }
        Button(onClick = onNextClicked, modifier = Modifier.width(128.dp)) {
            Text(text = "Next")
        }
    }
}

@Preview
@Composable
fun ArtSpaceAppPreview() {
    ArtSpaceTheme {
        ArtSpaceLayout()
    }
}
