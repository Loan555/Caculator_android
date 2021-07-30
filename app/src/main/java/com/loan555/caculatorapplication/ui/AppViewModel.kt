package com.loan555.caculatorapplication.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.loan555.caculatorapplication.model.ItemButton

class AppViewModel : ViewModel() {
    var expression: MutableLiveData<String> = MutableLiveData("")
    var result: MutableLiveData<String> = MutableLiveData("")
    var expressionTouch = MutableLiveData<List<ItemButton>>(listOf())
    var mess = MutableLiveData<String>("")

    fun addExpression(char: ItemButton) {
        expressionTouch.value = expressionTouch.value!! + char
    }

    fun subExpression(item: ItemButton) {
        expressionTouch.value!!.drop(expressionTouch.value!!.size - 1)
    }

    fun doCount() {
        var a = expression.value
        var agregate = 0f
        if (a != null && a != "") {
            a = a.replace(",", ".")
            a = a.replace("%", "÷100")
            var operator: Array<String> =
                a.split("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", ".").toTypedArray()
            var operands: Array<String> = a.split("+", "-", "x", "÷").toTypedArray()
            Log.d(
                "aaa",
                "toan tu = ${arrayToString(operator)} , toan hang = ${arrayToString(operands)}"
            )

            var arrayOperator = arrayListOf<String>()
            for (i in operator.indices) {
                if (operator[i] != "") {
                    arrayOperator.add(operator[i])
                }
            }

            if (operands[0] == "") {
                agregate += 0
            } else agregate += operands[0].toFloat()

            var count = 0
            for (i in 1 until operands.size) {
                var temp: Float = 0f
                if (operands[i] == "") {
                    continue
                } else {
                    temp = operands[i].toFloat()
                }
                count++
                Log.d("aaa", "temp = $temp ag= $agregate")
                when (arrayOperator[count - 1]) {
                    "+" -> {
                        agregate += temp
                    }
                    "-", "+-" -> {
                        agregate -= temp
                    }
                    "x" -> {
                        agregate *= temp
                    }
                    "÷" -> {
                        agregate /= temp
                    }
                    "x-" -> {
                        agregate *= (-1) * temp
                    }
                    "÷-" -> {
                        agregate /= (-1) * temp
                    }
                }
            }
        }
        result.value = agregate.toString()
    }

    private fun arrayToString(operator: Array<String>): String {
        var result = ""
        operator.forEach {
            result += "$it;"
        }
        return result
    }

    fun newExpression() {
        expression.value = result.value
    }
}