package com.example.firstkotlinapp

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType

class MainActivity : ComponentActivity() {
    private val TAG = "SP-Test"
    lateinit var sp: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "==== onCreate ====")
        // 获取SharedPreferences实例，文件名：user_info
        sp = getSharedPreferences("user_info", MODE_PRIVATE)

        setContent {
            SPPageUI(
                onSave = { name, pwd ->
                    saveData(name, pwd)
                },
                onLoad = {
                    loadData()
                },
                onClear = {
                    clearData()
                }
            )
        }
    }

    // 保存数据
    fun saveData(username: String, password: String) {
        val editor = sp.edit()
        editor.putString("username", username)
        editor.putString("password", password)
        editor.apply()
        Log.d(TAG,"保存成功：$username , $password")
    }

    // 读取数据
    fun loadData(): Pair<String,String> {
        val name = sp.getString("username", "") ?: ""
        val pwd = sp.getString("password", "") ?: ""
        Log.d(TAG,"读取成功：$name , $pwd")
        return Pair(name,pwd)
    }

    // 清空数据
    fun clearData(){
        val editor = sp.edit()
        editor.clear()
        editor.apply()
        Log.d(TAG,"数据已清空")
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
fun SPPageUI(
    onSave:(String,String)->Unit,
    onLoad:()->Pair<String,String>,
    onClear:()->Unit
){
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var showResult by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "实验四：SharedPreferences本地存储")
        OutlinedTextField(
            value = username,
            onValueChange = {username = it},
            label = { Text("用户名") }
        )
        OutlinedTextField(
            value = password,
            onValueChange = {password = it},
            label = { Text("密码") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )
        Button(onClick = {
            onSave(username,password)
            showResult = "已保存"
        }) {
            Text("保存")
        }
        Button(onClick = {
            val (name,pwd) = onLoad()
            username = name
            password = pwd
            showResult = "读取：用户名=$name，密码=$pwd"
        }) {
            Text("读取")
        }
        Button(onClick = {
            onClear()
            username = ""
            password = ""
            showResult = "已清空"
        }) {
            Text("清空")
        }
        Text(text = showResult)
    }
}
