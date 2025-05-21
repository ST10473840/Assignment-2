package za.co.varsitycollege.st10473840.imadassignment2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ThirdActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_third)

                val score = intent.getIntExtra("Score", 0)
                val total = intent.getIntExtra("Total", 0)

                val FinalResult = findViewById<TextView>(R.id.FinalResult)
                val RestartButton= findViewById<Button>(R.id.RestartButton)

                FinalResult.text = "You scored $score out of $total"

                RestartButton.setOnClickListener {
                    // Restart the quiz by going back to SecondActivity
                    val intent = Intent(this, SecondActivity::class.java)
                    startActivity(intent)
                    finish()
                }

                // Fix insets
                ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
                    val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                    v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                    insets
                }
            }
        }



