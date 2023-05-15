package com.kt.quiz_app

class QuizManager(val quizList: List<Quiz>) {
    var currentQuizIndex = 0
    var score = 0

    fun getCurrentQuiz(): Quiz {
        return quizList[currentQuizIndex]
    }

    fun nextQuiz() {
        if (currentQuizIndex < quizList.size - 1) {
            currentQuizIndex++
        } else {
            currentQuizIndex = 0
        }
    }

    fun checkAnswer(answer: String): Boolean {
        val currentQuiz = getCurrentQuiz()
        if (answer == currentQuiz.answer) {
            score += 1
            return true
        }
        return false
    }

    fun reset() {
        currentQuizIndex = 0
        score = 0
    }
}