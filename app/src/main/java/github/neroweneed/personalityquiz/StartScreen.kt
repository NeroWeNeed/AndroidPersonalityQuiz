package github.neroweneed.personalityquiz

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

const val START_SCREEN = "start"

@Composable
fun StartScreen(navController: NavController? = null) {
    Column {
        Row(Modifier.fillMaxWidth()) {
            Text(stringResource(R.string.app_name), textAlign = TextAlign.Center, fontSize = 48.sp)
        }
        Row(Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.Center) {
            Button(onClick = {
                navController?.navigate(FIRST_QUESTION_SCREEN)
            },Modifier.fillMaxWidth(0.9f)) {
                Text(stringResource(R.string.start_button))
            }
        }


    }
}