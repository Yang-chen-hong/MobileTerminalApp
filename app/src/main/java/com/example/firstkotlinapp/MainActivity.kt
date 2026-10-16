package com.example.firstkotlinapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    private val TAG = "List-Test"
    // 模拟列表数据
    private val dataList = listOf(
        "potato",
        "apple",
        "banana",
        "orange",
        "grape",
        "pear",
        "mango",
        "cherry"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "==== onCreate ====")
        setContent {
            ListPageUI(dataList)
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
fun ListPageUI(list: List<String>) {
    var selectedItem by remember { mutableStateOf("尚未点击任何条目") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Text(text = "实验五：LazyColumn列表", modifier = Modifier.padding(bottom = 10.dp))
        Text(text = "选中：$selectedItem", modifier = Modifier.padding(bottom = 10.dp))

        LazyColumn {
            items(list) { item ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                        .clickable {
                            selectedItem = item
                            Log.d("List-Test", "点击条目：$item")
                        },
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Text(
                        text = item,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    }
}
