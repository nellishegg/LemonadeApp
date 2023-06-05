package andrewafony.research.diceroll

import andrewafony.research.diceroll.ui.theme.LemonJuiceTheme
import andrewafony.research.diceroll.ui.theme.PaleBlue
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.Top
import androidx.compose.ui.Alignment.Companion.TopCenter
import androidx.compose.ui.Alignment.Companion.TopStart
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.DefaultAlpha
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonJuiceTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    Scaffold(
        topBar = {
            TopAppBar(backgroundColor = Color.Yellow, elevation = 16.dp) {
                Text(
                    "Lemonade",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        },
    ) {
        JuiceWithButtonAndImage()
    }
}

@Composable
fun JuiceWithButtonAndImage(modifier: Modifier = Modifier) {
    var result by remember { mutableStateOf(1) }
    var buttonClick by remember { mutableStateOf((1..5).random()) }

    var isButtonEnabled = when(result) {
        2, 4 -> false
        else -> true
    }

    val images = when (result) {
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        else -> R.drawable.lemon_restart
    }

    val text = when (result) {
        1 -> ("Tap to select a lemon")
        2 -> ("Keep tapping the lemon image to squeeze it,\n click $buttonClick times  more")
        3 -> ("Tap to drink it")
        4 -> ("Choose what you want to do next")
        R.drawable.lemon_restart -> ("")
        else -> ("")
    }
    val chooseWhatToDoInTheFourImage =
        listOf<String>("I want additional glass of water", "Start again")

    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .background(color = PaleBlue)
                .clickable {
                    if (images == R.drawable.lemon_squeeze) {
                        if (result == 2) {
                            buttonClick--
                            if (buttonClick == 0) {
                                result++
                                buttonClick = (1..5).random()
                            }
                        }
                    }
                },
            painter = painterResource(images),
            contentDescription = "1"
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                if (result == 4 || result == 2)

                else
                    result++
                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Yellow,
                    contentColor = Color.Black
                ),
            enabled = isButtonEnabled
        ) {
            Text(text = stringResource(id = R.string.Tab))
        }
        Spacer(modifier = Modifier.height(16.dp))
        Column {
            Text(
                text = text,
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontSize = 18.sp,
            )
            Spacer(modifier = Modifier.height(25.dp))
            Column() {
                if (result == 4) {
                    Text(text = chooseWhatToDoInTheFourImage[0],
                        textAlign = TextAlign.Center,
                        fontSize = 18.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(width = 1.dp, color = Color.Yellow)
                            .clickable {
                                if (result == 4) {
                                    println(chooseWhatToDoInTheFourImage[0])
                                    result = 2
                                }
                            })
                    Spacer(modifier = Modifier.height(30.dp))
                    Text(text = chooseWhatToDoInTheFourImage[1],
                        textAlign = TextAlign.Center,
                        fontSize = 18.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(width = 1.dp, color = Color.Yellow)
                            .clickable {
                                if (result == 4) {
                                    println(chooseWhatToDoInTheFourImage[1])
                                    result = 1
                                }
                            })
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LemonJuice() {
    MainScreen()
}

