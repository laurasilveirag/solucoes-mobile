package com.example.calculadora_mobile.ui.theme.ui

package com.example.calculadora

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Calculator() {
    var result by remember { mutableStateOf("") }
    var operation by remember { mutableStateOf("") }
    var firstNumber by remember { mutableStateOf(0.0)}
    var secondNumber by remember { mutableStateOf(0.0)}

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = result,
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))
        // ... (Botões numéricos e operadores)
        Row {
            CalculatorButton("=") {
                calculate()
            }
            CalculatorButton("C") {
                clear()
            }
        }
    }

    fun appendNumber(number: String) {
        result += number
    }

    fun setOperation(op: String) {
        firstNumber = result.toDoubleOrNull() ?: 0.0
        operation = op
        result = ""
    }

    fun calculate() {
        secondNumber = result.toDoubleOrNull() ?: 0.0
        when (operation) {
            "+" -> result = (firstNumber + secondNumber).toString()
            "-" -> result = (firstNumber - secondNumber).toString()
            "*" -> result = (firstNumber * secondNumber).toString()
            "/" -> {
                if (secondNumber != 0.0) {
                    result = (firstNumber / secondNumber).toString()
                } else {
                    result = "Error"
                }
            }
        }
        operation = ""
    }

    fun clear() {
        result = ""
        operation = ""
        firstNumber = 0.0
        secondNumber = 0.0
    }

    Column {
        // ... (display)
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            CalculatorButton("7") { appendNumber("7") }
            CalculatorButton("8") { appendNumber("8") }
            CalculatorButton("9") { appendNumber("9") }
            CalculatorButton("/") { setOperation("/") }
        }
        Row {
            CalculatorButton("4") { appendNumber("4") }
            CalculatorButton("5") { appendNumber("5") }
            CalculatorButton("6") { appendNumber("6") }
            CalculatorButton("*") { setOperation("*") }
        }
        Row {
            CalculatorButton("1") { appendNumber("1") }
            CalculatorButton("2") { appendNumber("2") }
            CalculatorButton("3") { appendNumber("3") }
            CalculatorButton("-") { setOperation("-") }
        }
        Row {
            CalculatorButton("0") { appendNumber("0") }
            CalculatorButton(".") { appendNumber(".") }
            CalculatorButton("=") { calculate() }
            CalculatorButton("+") { setOperation("+") }
        }
    }
}

@Composable
fun CalculatorButton(symbol: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier.size(64.dp)
    ) {
        Text(text = symbol)
    }
}