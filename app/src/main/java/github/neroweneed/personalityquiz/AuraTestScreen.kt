package github.neroweneed.personalityquiz

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Circle
import androidx.compose.material.icons.rounded.Circle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

const val AURA_TEST_SCREEN = "auratest"

@Composable
fun AuraTestScreen(navController: NavController? = null) {
    Column(Modifier.fillMaxWidth(0.9f), verticalArrangement = Arrangement.Center) {
        Column(
            Modifier
                .fillMaxWidth()
                .background(Color.Gray, RoundedCornerShape(4.dp))
                .padding(8.dp)
        ) {
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Text(stringResource(R.string.aura_test_line_1), textAlign = TextAlign.Center)
            }
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Text(stringResource(R.string.aura_test_line_2), textAlign = TextAlign.Center)
            }
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Text(stringResource(R.string.aura_test_line_3), textAlign = TextAlign.Center)
            }
        }

        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Icon(
                Icons.Outlined.Circle,
                contentDescription = "Aura Test",
                modifier = Modifier
                    .padding(16.dp)
                    .width(96.dp)
                    .height(96.dp)
                    .pointerInput(Unit) {
                        detectTapGestures(onLongPress = {
                            navController?.navigate(RESULTS_SCREEN)
                        })
                    },
                tint = Color.Blue
            )
/*            Text(
                stringResource(R.string.aura_test_button),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(16.dp)
                    .pointerInput(Unit) {
                        detectTapGestures(onLongPress = {
                            navController?.navigate(RESULTS_SCREEN)
                        })
                    }, fontSize = 48.sp
            )*/


        }
    }
}