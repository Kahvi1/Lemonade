package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonadeTheme {
                LemonadeApp()
            }
        }
    }
}

@Composable
fun LemonadeApp(modifier: Modifier = Modifier) {
    Column {
        TopBar()
        MainContent()
    }
}

@Composable
fun TopBar(modifier: Modifier = Modifier) {
    Column {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth(
)
                .background(Color.Yellow)
                .padding(WindowInsets.safeDrawing.asPaddingValues())
        ) {
            Text(
                text = stringResource(R.string.app_name),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 16.dp, bottom = 16.dp)
            )
        }
    }
}

@Composable
fun MainContent(modifier: Modifier = Modifier) {
    var currStep by remember { mutableIntStateOf(0) }
    var squeezeCntTarget by remember { mutableIntStateOf(1) }
    var squeezeCurrCnt by remember { mutableIntStateOf(0) }
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        val imageContent = when (currStep) {
            0 -> R.drawable.lemon_tree
            1 -> R.drawable.lemon_squeeze
            2 -> R.drawable.lemon_drink
            else -> R.drawable.lemon_restart
        }

       val imageDesc = when (currStep) {
           0 -> stringResource(R.string.lemon_tree)
           1 -> stringResource(R.string.lemon)
           2 -> stringResource(R.string.glass_of_lemonade)
           else -> stringResource(R.string.empty_glass)
       }

        val textContent = when (currStep) {
            0 -> stringResource(R.string.step1)
            1 -> stringResource(R.string.step2)
            2 -> stringResource(R.string.step3)
            else -> stringResource(R.string.step4)
        }

        Image(
            painter = painterResource(imageContent),
            contentDescription = imageDesc,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .background(
                    color = MaterialTheme.colorScheme.tertiaryContainer,
                    shape = RoundedCornerShape(16.dp)
                )
                .clickable {
                    if (currStep == 1) {
                        squeezeCurrCnt++
                        if (squeezeCurrCnt >= squeezeCntTarget) {
                            currStep++
                            squeezeCurrCnt = 0
                        }
                    } else if (currStep == 0) {
                        currStep++
                        squeezeCntTarget = (2..4).random()
                    } else {
                        currStep++
                    }
                    currStep = currStep.rem(4)
                }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = textContent,
            fontSize = 18.sp,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LemonadeTheme {
        LemonadeApp()
    }
}