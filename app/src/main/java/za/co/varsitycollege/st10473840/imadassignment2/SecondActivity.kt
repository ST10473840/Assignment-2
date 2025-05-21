package za.co.varsitycollege.st10473840.imadassignment2

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
data class Question(val text: String, val answer: Boolean, val feedback: String = "")

class SecondActivity : AppCompatActivity() {

    private lateinit var Result: TextView
    private lateinit var Feedback: TextView
    private lateinit var TrueButton: Button
    private lateinit var FalseButton: Button
    private lateinit var Nextbutton: Button

    private val questions = listOf(
        Question("The Great Wall of China was built in the 20th century.", false, "It began over 2000 years ago."),
        Question("The Titanic sank in 1912.", true, "Indeed, the Titanic sank in 1912."),
        Question("The Roman Empire fell in 476 AD.", true, "That is correct! It marked the fall of the Western Roman Empire."),
        Question("World War I ended in 1939.", false, "WWI ended in 1918. 1939 was the start of World War II."),
        Question("The first moon landing took place in 1968.", false, "The first landing took place in 1969.")
    )

    private var currentIndex = 0
    private var score = 0
    private var isAnswered = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second)

        Result = findViewById(R.id.Result)
        Feedback = findViewById(R.id.Feedback)
        TrueButton = findViewById(R.id.TrueButton)
        FalseButton = findViewById(R.id.FalseButton)
        Nextbutton = findViewById(R.id.Nextbutton)

        showQuestion()

        TrueButton.setOnClickListener { checkAnswer(true) }
        FalseButton.setOnClickListener { checkAnswer(false) }

        Nextbutton.setOnClickListener {
            val intent = Intent(this, ThirdActivity::class.java)
            intent.putExtra("Score", score)
            intent.putExtra("Total", questions.size)
            startActivity(intent)
            finish()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun showQuestion() {
        val currentQuestion = questions[currentIndex]
        Result.text = currentQuestion.text
        Feedback.text = ""
        isAnswered = false
        TrueButton.isEnabled = true
        FalseButton.isEnabled = true
        TrueButton.setBackgroundColor(getColor(R.color.teal_700))
        FalseButton.setBackgroundColor(getColor(R.color.teal_700))
    }

    private fun checkAnswer(userAnswer: Boolean) {
        if (isAnswered) return
        isAnswered = true

        val currentQuestion = questions[currentIndex]
        val isCorrect = userAnswer == currentQuestion.answer

        if (isCorrect) {
            score++
            Feedback.text = "Correct! ${currentQuestion.feedback}"
            TrueButton.setBackgroundColor(getColor(if (currentQuestion.answer) R.color.correct_green else R.color.incorrect_red))
            FalseButton.setBackgroundColor(getColor(if (!currentQuestion.answer) R.color.correct_green else R.color.incorrect_red))
        } else {
            Feedback.text = "Incorrect. ${currentQuestion.feedback}"
            TrueButton.setBackgroundColor(getColor(if (!currentQuestion.answer) R.color.correct_green else R.color.incorrect_red))
            FalseButton.setBackgroundColor(getColor(if (currentQuestion.answer) R.color.correct_green else R.color.incorrect_red))
        }

        TrueButton.isEnabled = false
        FalseButton.isEnabled = false

        Handler(Looper.getMainLooper()).postDelayed({
            if (currentIndex < questions.size - 1) {
                currentIndex++
                showQuestion()
            } else {
                val intent = Intent(this, ThirdActivity::class.java)
                intent.putExtra("Score", score)
                intent.putExtra("Total", questions.size)
                startActivity(intent)
                finish()
            }
        }, 5000)
    }
}
