package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {

    private var tvInput: TextView? = null
    var lastNumeric = false
    var lastDot  = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        tvInput = findViewById(R.id.tvInput)
    }

    fun popUp(view:View){
        tvInput?.append((view as Button).text)
        lastNumeric = true
        lastDot = false
    }

    fun clear(view: View){

        tvInput?.text = ""
    }

    fun onDecimalPoint(view: View){
        if(lastNumeric && !lastDot){
            tvInput?.append(".")
            lastDot = true
            lastNumeric = false
        }
    }

    fun removeZeroAfter(someVariable: String): String {
        return someVariable.substring(0, someVariable.length - 2)
    }

    fun onEquals(view: View){
        if(lastNumeric){
            var stringValue  = tvInput?.text.toString()
            var prefix  = ""

            try {
                if(stringValue.startsWith("-")){
                    prefix = "-"
                    stringValue = stringValue.substring(1)
                }
                if(stringValue.contains("-")){
                    var splitValue = stringValue.split("-")
                    var one = splitValue[0] //99
                    var two = splitValue[1] //1
                    if(prefix.isNotEmpty()){
                        one  = prefix + one
                    }
                    var result = removeZeroAfter((one.toDouble() - two.toDouble()).toString())
                    tvInput?.text = result
                }
                else if(stringValue.contains("+")){
                    var splitValue = stringValue.split("+")
                    var one = splitValue[0] //99
                    var two = splitValue[1] //1
                    if(prefix.isNotEmpty()){
                        one  = prefix + one
                    }
                    var result = removeZeroAfter((one.toDouble() + two.toDouble()).toString())
                    tvInput?.text = result
                }
                else if(stringValue.contains("/")){
                    var splitValue = stringValue.split("/")
                    var one = splitValue[0] //99
                    var two = splitValue[1] //1
                    if(prefix.isNotEmpty()){
                        one  = prefix + one
                    }
                    var result =removeZeroAfter((one.toDouble() / two.toDouble()).toString())
                    tvInput?.text = result
                }
                else if(stringValue.contains("x")){
                    var splitValue = stringValue.split("x")
                    var one = splitValue[0] //99
                    var two = splitValue[1] //1
                    if(prefix.isNotEmpty()){
                        one  = prefix + one
                    }
                    var result = removeZeroAfter((one.toDouble() * two.toDouble()).toString())
                    tvInput?.text = result
                }


            }catch (e: ArithmeticException){
                e.printStackTrace()
            }

        }
    }

    fun onOperator(view: View){

        tvInput?.text?.let {
            if(lastNumeric && !isOperatorAdded(it.toString())){
                tvInput?.append((view as Button).text)
                lastNumeric =  false
                lastDot = false
            }
        }


    }

    fun isOperatorAdded(value:String): Boolean{
        return if(value.startsWith("—")) {
            false
        }
        else{
            value.contains("+") ||
                    value.contains("—") ||
                    value.contains("/") ||
                    value.contains("X")
        }
    }
}