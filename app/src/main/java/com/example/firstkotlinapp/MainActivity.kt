package com.example.firstkotlinapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

class MainActivity : ComponentActivity() {
    private val TAG = "LifeTest-Main"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "==== onCreate ====")
        setContent {
            MainPageUI {
                // 点击跳转到SecondActivity，附带传递数据
                val intent = Intent(this@MainActivity, SecondActivity::class.java)
                intent.putExtra("msg", "来自MainActivity的数据：Yang-chen-hong")
                startActivity(intent)
            }
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
fun MainPageUI(onJumpClick: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "实验三：Activity生命周期与Intent跳转")
        Button(onClick = onJumpClick) {
            Text("打开第二个页面")
        }
    }
}
