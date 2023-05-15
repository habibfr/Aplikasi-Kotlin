package com.kt.quiz_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout

class MainActivity : AppCompatActivity() {
    private lateinit var quizManager: QuizManager
    private lateinit var startButton: Button
    private lateinit var submitButton: Button
    private lateinit var nextBtn: Button
    private lateinit var answerChoices: RadioGroup
    private lateinit var restartButton: Button
    private lateinit var questionTextView: TextView
    private lateinit var resultTextView: TextView
    private lateinit var scoreTextView: TextView
    private lateinit var answerChoice1: RadioButton
    private lateinit var answerChoice2: RadioButton
    private lateinit var answerChoice3: RadioButton
    private lateinit var answerChoice4: RadioButton
    private lateinit var startLayout: LinearLayout
    private lateinit var questionLayout: LinearLayout
    private lateinit var resultLayout: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inisialisasi kelas QuizManager dengan daftar pertanyaan kuis
        val quizList = listOf(
            Quiz(
                "Siapakah presiden pertama Indonesia?",
                listOf("Soekarno", "Soeharto", "Habibie", "Megawati"),
                "Soekarno"
            ),
            Quiz(
                "Apa ibukota Indonesia?",
                listOf("Jakarta", "Surabaya", "Bandung", "Medan"),
                "Jakarta"
            ),
            Quiz("Berapa jumlah provinsi di Indonesia?", listOf("32", "33", "34", "35"), "34"),
            Quiz(
                "Siapakah penemu bola lampu?",
                listOf("Thomas Edison", "Alexander Graham Bell", "Albert Einstein", "Isaac Newton"),
                "Thomas Edison"
            ),
            Quiz(
                "Apa nama satelit alami bumi?",
                listOf("Bulan", "Mars", "Venus", "Jupiter"),
                "Bulan"
            )
        )
        quizManager = QuizManager(quizList)
        startButton = findViewById(R.id.start_button)
        submitButton = findViewById(R.id.submit_button)
        nextBtn = findViewById(R.id.next_button)
        answerChoices = findViewById(R.id.answer_choices)
        restartButton = findViewById<Button>(R.id.restart_button)
        questionTextView = findViewById<TextView>(R.id.question_text_view)
        resultTextView = findViewById<TextView>(R.id.result_text_view)
        scoreTextView = findViewById<TextView>(R.id.score_text_view)
        answerChoice1 = findViewById<RadioButton>(R.id.answer_choice_1)
        answerChoice2 = findViewById<RadioButton>(R.id.answer_choice_2)
        answerChoice3 = findViewById<RadioButton>(R.id.answer_choice_3)
        answerChoice4 = findViewById<RadioButton>(R.id.answer_choice_4)
        startLayout = findViewById<LinearLayout>(R.id.startLayout)
        questionLayout = findViewById<LinearLayout>(R.id.question_layout)
        resultLayout = findViewById<LinearLayout>(R.id.result_layout)

        // Menghubungkan tampilan dengan logika kuis
        startButton.setOnClickListener {
            showQuestionLayout()
            submitButton.visibility = View.GONE
        }

        submitButton.setOnClickListener {
            val selectedAnswer =
                answerChoices.findViewById<RadioButton>(answerChoices.checkedRadioButtonId)
            if (selectedAnswer != null) {
                val answer = selectedAnswer.text.toString()
                val isCorrect = quizManager.checkAnswer(answer)
                if (isCorrect) {
                    scoreTextView.setText("Skor: ${quizManager.score}")
                }
            }
            showResultLayout()
        }

        nextBtn.setOnClickListener {
            val selectedAnswer =
                answerChoices.findViewById<RadioButton>(answerChoices.checkedRadioButtonId)
            if (selectedAnswer != null) {
                val answer = selectedAnswer.text.toString()
                val isCorrect = quizManager.checkAnswer(answer)
                if (isCorrect) {
                    scoreTextView.setText("Skor: ${quizManager.score}")
                }
                answerChoices.clearCheck()
            }
            quizManager.nextQuiz()
            showQuestionLayout()

            if (quizManager.currentQuizIndex == quizList.size - 1) {
                nextBtn.visibility = View.GONE
                submitButton.visibility = View.VISIBLE
            }
        }

        restartButton.setOnClickListener {
            answerChoices.clearCheck()
            quizManager.reset()
            nextBtn.visibility = View.VISIBLE
            showStartLayout()

        }
        // Menampilkan tampilan awal
        showStartLayout()
    }

    private fun showStartLayout() {
        startLayout.visibility = View.VISIBLE
        questionLayout.visibility = View.GONE
        resultLayout.visibility = View.GONE
    }

    private fun showQuestionLayout() {
        answerChoices.clearCheck()
        startLayout.visibility = View.GONE
        questionLayout.visibility = View.VISIBLE
        resultLayout.visibility = View.GONE

        val currentQuiz = quizManager.getCurrentQuiz()
        questionTextView.setText(currentQuiz.question)

        answerChoice1.setText(currentQuiz.choices[0])
        answerChoice2.setText(currentQuiz.choices[1])
        answerChoice3.setText(currentQuiz.choices[2])
        answerChoice4.setText(currentQuiz.choices[3])

    }

    private fun showResultLayout() {
        startLayout.visibility = View.GONE
        questionLayout.visibility = View.GONE
        resultLayout.visibility = View.VISIBLE

        resultTextView.setText("Hasil Kuis")
        scoreTextView.setText("Skor: ${quizManager.score}")
    }
}