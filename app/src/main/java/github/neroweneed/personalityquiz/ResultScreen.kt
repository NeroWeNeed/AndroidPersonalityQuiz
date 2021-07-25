package github.neroweneed.personalityquiz

import android.content.Intent
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.sp
import github.neroweneed.personalityquiz.data.Nature
import github.neroweneed.personalityquiz.data.Response
import github.neroweneed.personalityquiz.data.ResponsePoint


const val RESULTS_SCREEN = "results"

@ExperimentalUnitApi
@Composable
fun ResultsScreen(responses: List<ResponsePoint>, share: ((Nature) -> Unit)? = null) {
    println(responses)
    val nature = if (responses.isEmpty()) Nature.UNKNOWN else responses.calculatePersonality()
    Column(
        Modifier
            .fillMaxWidth()
            .animateContentSize { initialValue, targetValue -> }) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Text(stringResource(R.string.results_line_1))
        }
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Text(nature.name, fontSize = 48.sp)

        }
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Button({ share?.invoke(nature) }, modifier = Modifier.fillMaxWidth()) {
                Text("Share")
            }
        }

    }
}