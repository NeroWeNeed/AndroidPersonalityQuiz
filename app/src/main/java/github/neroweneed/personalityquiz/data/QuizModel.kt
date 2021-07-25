package github.neroweneed.personalityquiz.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class QuizModel : ViewModel() {
    companion object {
        const val MAX_QUESTIONS = 9
    }

    private val _responses = MutableLiveData<List<ResponsePoint>>(emptyList())
    val responses: LiveData<List<ResponsePoint>> = _responses

    private val _currentQuestionIndex = MutableLiveData<Int>(0)
    val currentQuestionIndex: LiveData<Int> = _currentQuestionIndex
    private val _questions = MutableLiveData<List<Question>>()
    val questions: LiveData<List<Question>> = _questions
    val currentQuestion: Question?
        get() = questions.value?.get(currentQuestionIndex?.value ?: 0)


    fun submitResponse(response: Response) {
        _responses.value = _responses.value?.plus(response.points) ?: response.points.toList()
    }


}