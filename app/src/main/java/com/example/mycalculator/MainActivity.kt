package com.example.mycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatViewInflater
import org.w3c.dom.Text
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {
    private var TV: TextView?=null
    var numeric=false;
    var dot=false;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        TV=findViewById<TextView>(R.id.TV)
    }

    fun ondigit(view: View){
        TV?.append((view as Button).text)
        numeric=true
    }
    fun onclr(view: View){
        TV?.text=""
    }
    fun ondecimal(view: View){
      if(numeric && !dot){
          TV?.append(".")
          numeric=false
          dot = true
      }
    }

    fun onoperator(view: View){
        TV?.text?.let{
            if(numeric && !ifoperator(it.toString())){
                TV?.append((view as Button).text)
                numeric=false
                dot=false
            }
        }

    }

    fun removezero(result: String): String{
        var value=result
        if(result.contains(".0")){
            value=result.substring(0,result.length-2)
        }
        return value;
    }

    fun onequal(view: View){
        if(numeric){
            var tvalue=TV?.text.toString()
            var prefix=""
            try{
                if(tvalue.startsWith("-")){
                    prefix="-"
                    tvalue=tvalue.substring(1)
                }
                if(tvalue.contains("-")){
                val split=tvalue.split("-")
                var one=split[0]
                var two=split[1]
                if(prefix.contentEquals("-")){
                    TV?.text=removezero((-one.toDouble()-two.toDouble()).toString())
                }
                else {
                    TV?.text = removezero((one.toDouble() - two.toDouble()).toString())
                }}
                else if(tvalue.contains("+")){
                    val split=tvalue.split("+")
                    var one=split[0]
                    var two=split[1]
                    TV?.text = removezero((one.toDouble() + two.toDouble()).toString())
                }
                else if(tvalue.contains("*")){
                    val split=tvalue.split("*")
                    var one=split[0]
                    var two=split[1]
                    TV?.text = removezero((one.toDouble() * two.toDouble()).toString())
                }
                else if(tvalue.contains("/")){
                    val split=tvalue.split("/")
                    var one=split[0]
                    var two=split[1]
                    TV?.text = removezero((one.toDouble() / two.toDouble()).toString())
                }
                else if(tvalue.contains("%")){
                    val split=tvalue.split("%")
                    var one=split[0]
                    var two=split[1]
                    TV?.text = removezero((one.toDouble() % two.toDouble()).toString())
                }
            }catch (e:ArithmeticException){
                e.printStackTrace()
            }
        }
    }

    fun ifoperator(value:String): Boolean{
        return if(value.startsWith("-")){
            false
        }else {
            value.contains("/")|| value.contains("*")|| value.contains("-")|| value.contains("+")
        }
    }
}