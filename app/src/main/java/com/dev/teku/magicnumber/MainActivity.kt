package com.dev.teku.magicnumber

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.dev.teku.magicnumber.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var magicNumber: Int = (0..100).random()
    private var steps: Int = 0
    private var gameFinished : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding  = ActivityMainBinding.inflate(layoutInflater)


        binding.validateButton.setOnClickListener {
            val guessNumber: String = binding.inputEditTextNumber.text.toString()

            showMessage(validates(guessNumber.toInt()))

            binding.resetButton.isEnabled = gameFinished
        }

        binding.resetButton.setOnClickListener {
            reset()
        }
        binding.resetButton.isEnabled = gameFinished

        setContentView(binding.root)
    }

    private fun validates(guessNumber: Int) : Int {

        ++steps

        gameFinished = guessNumber == magicNumber

        return when {
            guessNumber == magicNumber -> 0
            guessNumber < magicNumber -> 1
            else -> 2  // guessNumber > secretNumber
        }
    }

    private fun showMessage(validationResult: Int) {

        val textMssg = when (validationResult) {
            0 -> "Congratulations, you've guessed the magic number in $steps steps!!"
            1 -> "The magic number is bigger than your suggestion, try again"
            else -> "The magic number is smaller than your suggestion, try again" // validationResult == 2
        }

        val t: Toast = Toast.makeText(this, textMssg, Toast.LENGTH_SHORT)
        t.show()
    }

    private fun reset() {
        magicNumber = (0..100).random()
        steps = 0

        val t: Toast = Toast.makeText(this, "New magic number created, play again", Toast.LENGTH_SHORT)
        t.show()
    }
}