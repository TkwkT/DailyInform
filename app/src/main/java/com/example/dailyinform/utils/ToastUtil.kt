package com.example.dailyinform.utils

import android.content.Context
import android.widget.Toast

object ToastUtil {

    fun setToast(message:String, context: Context){
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show()
    }

}