package github.neroweneed.personalityquiz

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import github.neroweneed.personalityquiz.data.Question
import github.neroweneed.personalityquiz.data.Response

const val FIRST_QUESTION_SCREEN = "question"
const val QUESTION_SCREEN_ARGUMENT = "questionNumber"
const val QUESTION_SCREEN = "question/{${QUESTION_SCREEN_ARGUMENT}}"
fun questionScreen(number: Int) = "question/$number"
@Composable
fun QuestionScreen(
    question: Question,
    questionNumber: Int,
    maxQuestions: Int,
    navController: NavController? = null,
    onSubmitAnswer: ((Response) -> Unit)? = null
) {
    var answer by remember {
        mutableStateOf(-1)

    }

    Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Row(
            Modifier
                .fillMaxWidth(0.9f)
                .padding(0.dp, 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                question.prompt,
                Modifier
                    .background(Color.Blue, RoundedCornerShape(4.dp))
                    .padding(12.dp)

                    .fillMaxWidth(),
                textAlign = TextAlign.Center

            )
        }
        question.answers.forEachIndexed { index, response ->
            Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                RadioButton(selected = answer == index, onClick = {
                    answer = index
                })
                Text(response.text)
            }
        }
        Row(
            Modifier
                .fillMaxWidth(0.9f)
                .padding(0.dp, 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(enabled = answer != -1, onClick = {
                onSubmitAnswer?.invoke(question.answers[answer])
                if (questionNumber + 1 < maxQuestions)
                    navController?.navigate(questionScreen(questionNumber+1))
                else
                    navController?.navigate(AURA_TEST_SCREEN)
            }) {
                Text(
                    "Next",
                    Modifier
                        .background(Color.Gray, RoundedCornerShape(4.dp))
                        .padding(12.dp)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center

                )


            }
        }
    }


}