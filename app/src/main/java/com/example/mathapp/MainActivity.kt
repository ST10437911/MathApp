package com.example.mathapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize views
        val numOne: EditText = findViewById(R.id.valueone)
        val numTwo: EditText = findViewById(R.id.valuetwo)
        val calculate: Button = findViewById(R.id.calculate)
        val answer: TextView = findViewById(R.id.txtAnswer)
        val operationGroup: RadioGroup = findViewById(R.id.operationGroup)

        calculate.setOnClickListener {
            val num1 = numOne.text.toString()
            val num2 = numTwo.text.toString()

            if (num1.isEmpty() || num2.isEmpty()) {
                Toast.makeText(this, "Please enter both numbers", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            try {
                val number1 = num1.toDouble()
                val number2 = num2.toDouble()

                val selectedOperationId = operationGroup.checkedRadioButtonId
                if (selectedOperationId == -1) {
                    Toast.makeText(this, "Please select an operation", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                val selectedOperation = findViewById<RadioButton>(selectedOperationId)

                val result = when (selectedOperation.text) {
                    "Addition" -> number1 + number2
                    "Subtraction" -> number1 - number2
                    "Multiplication" -> number1 * number2
                    "Division" -> if (number2 != 0.0) number1 / number2 else Double.NaN
                    else -> Double.NaN
                }

                answer.text = if (result.isNaN()) {
                    "Division by zero is not allowed"
                } else {
                    "Result: $result"
                }

            } catch (e: NumberFormatException) {
                Toast.makeText(this, "Please enter valid numbers", Toast.LENGTH_SHORT).show()
            }
        }
    }
}