package com.example.hello

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.hello.ui.theme.HelloTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HelloTheme {
                val navController = rememberNavController()
                SetupNavGraph(navController = navController)
            }
        }
    }
}

@Composable
fun TextAndButton(name: String, onUpdate: (String) -> Unit, onClick: () -> Unit) {
    Row(modifier = Modifier.padding(top = 8.dp)) {
        TextField(
            value = name,
            onValueChange = onUpdate,
            placeholder = {
                Text(text = stringResource(id = R.string.hint))
            },
            singleLine = true,
            modifier = Modifier.alignByBaseline(),
            keyboardOptions = KeyboardOptions(
                autoCorrect = false,
                capitalization = KeyboardCapitalization.Words
            )
        )
        Button(
            modifier = Modifier
                .alignByBaseline()
                .padding(8.dp),
            onClick = onClick
        ) {
            Text(text = stringResource(id = R.string.done))
        }
    }
}

@Composable
fun Greeting(navController: NavHostController, name: String?) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = stringResource(id = R.string.hello, name!!),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.subtitle1
        )

        Button(
            onClick = {
                navController.navigate(route = "home_screen") {
                    popUpTo("home_screen") {
                        inclusive = true
                    }
                }
            }
        ) {
            Text(text = "Back")
        }
    }
}

@Composable
fun Welcome() {
    Text(
        text = stringResource(id = R.string.welcome),
        style = MaterialTheme.typography.subtitle1
    )
}

@Composable
fun Hello(navController: NavHostController) {
    var name by remember { mutableStateOf("") }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Welcome()
        TextAndButton(name, { value: String ->
            name = value
        }, {
            navController.navigate(if (name.isEmpty()) "second_screen" else "second_screen?name=$name")
        }
        )
    }
}
