package com.tuanvynguyen1.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.tuanvynguyen1.calculator.bean.Calculator
import kotlin.math.ceil
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {
    private var currMethod:Int = -1;
    private var lastMath:Boolean = false
    private var calc = Calculator();
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun numberClickAction(view: View){
        if(view is Button){
            var currInp = findViewById<TextView>(R.id.currInp)

            currInp.text = calc.putNumber(view.text.toString());
            lastMath = false;
        }
    }
    fun clearClickAction(view: View){
        var currInp = findViewById<TextView>(R.id.currInp)
        currMethod = -1
        lastMath = false
        currInp.text = "0"
        calc.allClear();
    }
    fun negativeClickAction(view: View){
        if(!lastMath) {
            var currInp = findViewById<TextView>(R.id.currInp)

            currInp.text = calc.negativeNumber();
        }
    }
    fun mathClickButton(view: View){
        if(view is Button){
            if(!lastMath) {
                var currInp = findViewById<TextView>(R.id.currInp)
                currInp.text = calc.getResult( currMethod);
                lastMath = true;
            }
            currMethod = calc.getMethod(view.text.toString())
        }


    }
    fun addDecimalAction(view: View){
        if(view is Button){
            var currInp = findViewById<TextView>(R.id.currInp)
            currInp.text = calc.putDecimal();
            lastMath = false;
        }
    }
    fun addPercentageAction(view: View){
        if(view is Button){
            var currInp = findViewById<TextView>(R.id.currInp)
            currInp.text = calc.putPercentage();
            lastMath = false;
        }
    }
}