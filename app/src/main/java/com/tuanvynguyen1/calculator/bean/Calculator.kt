package com.tuanvynguyen1.calculator.bean

import android.widget.Toast
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.DecimalFormat
import java.time.Duration
import kotlin.math.ceil

class Calculator {
    private val ZERO: BigDecimal = 0.toBigDecimal();
    private var result: BigDecimal = ZERO;
    private var currNumber:String = "0";
    private val LENGTH_LIMIT = 14;
    private var isDecimal = false;
    private var isPercentage = false;
    private val df = DecimalFormat("0000.0000000E0")
    private val df2 = DecimalFormat("###,###,###,###.##############");

    fun getResult(): String{
//        if(result) return result.toInt().toString()

        if(result.precision() - result.scale() > LENGTH_LIMIT-3)
            return df.format(result).toString();
        return df2.format(result).toString();
    }
    fun getCurrentNumberFormat():String{
        var percent = "%";
        if(!isPercentage) percent = "";
        return df2.format(currNumber.toBigDecimal()).toString()+percent;
    }
    private fun add(currInp:BigDecimal){
        result+=currInp;
    }
    private fun subtract(currInp:BigDecimal){
        result-=currInp;
    }
    private fun multiply(currInp:BigDecimal){
        result*=currInp;
    }
    private fun divide(currInp:BigDecimal): Boolean{
        if(currInp == ZERO) return false;
        result = result.divide(currInp,30, RoundingMode.HALF_UP).stripTrailingZeros();
        return true;
    }
    fun putPercentage():String{
        if(isPercentage) isPercentage = false;
            else isPercentage = true;
        return getCurrentNumberFormat();
    }
    fun resetNumber(){
        currNumber = "0";
        isPercentage = false;
        isDecimal = false;
    }
    fun allClear(){
        result = ZERO;
        currNumber = "0"
        isDecimal = false;
        isPercentage = false;
    }
    fun putNumber(inpNum:String):String {
        if(currNumber.length == LENGTH_LIMIT) return getCurrentNumberFormat();
        if(currNumber == "0") currNumber = inpNum;
            else currNumber+=inpNum;
        return getCurrentNumberFormat();
    }
    fun putDecimal(): String{
        if(isDecimal) return currNumber;
        isDecimal=true;
        currNumber = "$currNumber.";
        return getCurrentNumberFormat();
    }
    fun getResult(method: Int): String{
        var number:BigDecimal = currNumber.toBigDecimal();
        print(currNumber);
        if(isPercentage) number = number.divide(100.toBigDecimal(),30, RoundingMode.HALF_UP).stripTrailingZeros();
        if(result == ZERO) {
            result = number;
        }
        else{
            when(method){
                1 -> add(number)
                2 -> subtract(number)
                3 -> multiply(number)
                4 -> {
                    if(!divide(number))
                        return "Cannot Divide By 0"
                }
                else -> {
                    if(number != ZERO) result = number;
                }
            }
        }
        resetNumber();
        return getResult();
    }

    fun getMethod(text: String): Int {
        when(text) {
            "+" -> return 1
            "-" -> return 2
            "x" -> return 3
            "/" -> return 4
            "=" -> return 0
        }
        return -1
    }

    fun negativeNumber(
    ): String {
        if(currNumber == "0") return currNumber;
        if(currNumber[0] == '-') currNumber= currNumber.substring(1, currNumber.length)
        else currNumber= ("-$currNumber").toString();
        return getCurrentNumberFormat();
    }
}