package com.example.firstkotlinapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

class SecondActivity : ComponentActivity() {
    private val TAG = "LifeTest-Second"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "==== onCreate ====")
        // 获取MainActivity传递过来的数据
        val receiveMsg = intent.getStringExtra("msg") ?: "无数据"
        setContent {
            SecondPageUI(receiveMsg)
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "==== onStart ====")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "==== onResume ====")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "==== onPause ====")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "==== onStop ====")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "==== onDestroy ====")
    }
}

@Composable
fun SecondPageUI(message:String) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "第二个Activity页面")
        Text(text = "收到传递数据：$message")
    }
}
