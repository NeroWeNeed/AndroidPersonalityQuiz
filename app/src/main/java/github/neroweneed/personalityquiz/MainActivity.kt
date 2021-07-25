package github.neroweneed.personalityquiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import github.neroweneed.personalityquiz.data.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

@ExperimentalUnitApi
class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val questions = resources.openRawResource(R.raw.questions).use { inputStream ->
            inputStream.reader().use { inputStreamReader ->
                Json.decodeFromString<List<Question>>(inputStreamReader.readText())
            }
        }
        val auras = resources.openRawResource(R.raw.auras).use { inputStream ->
            inputStream.reader().use { inputStreamReader ->
                Json.decodeFromString<List<Aura>>(inputStreamReader.readText())
            }
        }
        Settings.Secure.getString(applicationContext.contentResolver, Settings.Secure.NAME)
        setContent {
            val model = viewModel<QuizModel>()
            Content(questions.shuffled().take(QuizModel.MAX_QUESTIONS),
                { model.submitResponse(it) },
                { model.responses.value ?: emptyList() })
        }
    }


    @Composable
    fun Content(
        questions: List<Question>,
        onSubmitAnswer: ((Response) -> Unit)? = null,
        onRequestResults: (() -> List<ResponsePoint>)? = null
    ) {


        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = START_SCREEN) {
            composable(FIRST_QUESTION_SCREEN) {
                QuestionScreen(questions[0], 0, questions.size, navController, onSubmitAnswer)
            }
            composable(
                QUESTION_SCREEN,
                arguments = listOf(navArgument(QUESTION_SCREEN_ARGUMENT) { type = NavType.IntType })
            ) {
                val number = it.arguments?.getInt(QUESTION_SCREEN_ARGUMENT) ?: 0
                QuestionScreen(
                    questions[number],
                    number,
                    questions.size,
                    navController,
                    onSubmitAnswer
                )
            }
            composable(RESULTS_SCREEN) {
                ResultsScreen(responses = onRequestResults?.invoke() ?: emptyList(),share = {
                    val intent = Intent.createChooser(Intent().apply {
                        action = Intent.ACTION_SEND
                        putExtra(Intent.EXTRA_TEXT,"My personality test said I was ${it.name}")
                        type = "text/plain"
                    },null)
                    startActivity(intent)
                })
            }
            composable(START_SCREEN) {
                StartScreen(navController)
            }
            composable(AURA_TEST_SCREEN) {
                AuraTestScreen(navController)
            }
        }

    }

    @ExperimentalUnitApi
    @Preview(showSystemUi = true)
    @Composable
    fun PreviewContent() {
        val question = Question(
            "What's your favourite fruit?", listOf(
                Response("Apple"),
                Response("Orange")
            )
        )
        //StartScreen()
        AuraTestScreen()
        //ResultsScreen(emptyList())
        /*QuestionScreen(
            question = question,
            questionNumber = 0,
            maxQuestions = 1,
            navController = null
        )*/

    }


}