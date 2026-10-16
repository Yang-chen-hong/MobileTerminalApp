package com.example.firstkotlinapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val TAG = "Net-Test"
    private val client = OkHttpClient()
    data class User(val id:Int, val name:String, val email:String)
    val userList = mutableStateListOf<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "==== onCreate ====")
        // 把当前实例的userList传给导航
        setContent {
            AppNav(userList)
        }
        fetchData()
    }

    fun fetchData(){
        GlobalScope.launch(Dispatchers.IO) {
            val request = Request.Builder()
                .url("https://jsonplaceholder.typicode.com/users")
                .build()
            val response = client.newCall(request).execute()
            val json = response.body?.string()
            Log.d(TAG,"网络返回原始数据：$json")
            val userArray = Gson().fromJson(json, Array<User>::class.java).toList()
            GlobalScope.launch(Dispatchers.Main) {
                userList.addAll(userArray)
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

// 接收userList参数
@Composable
fun AppNav(userList: List<MainActivity.User>){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "userList"){
        composable("userList"){
            UserListPage(
                userList = userList,
                onUserClick = { user ->
                    navController.navigate("userDetail/${user.id}/${user.name}/${user.email}")
                }
            )
        }
        composable(
            route = "userDetail/{id}/{name}/{email}",
            arguments = listOf(
                navArgument("id"){type = NavType.IntType},
                navArgument("name"){type = NavType.StringType},
                navArgument("email"){type = NavType.StringType}
            )
        ){ backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id") ?: 0
            val name = backStackEntry.arguments?.getString("name") ?: ""
            val email = backStackEntry.arguments?.getString("email") ?: ""
            UserDetailPage(id,name,email){
                navController.popBackStack()
            }
        }
    }
}

@Composable
fun UserListPage(
    userList: List<MainActivity.User>,
    onUserClick: (MainActivity.User)->Unit
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Text(text = "实验七：页面导航与参数传递")
        LazyColumn {
            items(userList) { user ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                    onClick = { onUserClick(user) }
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(text = "ID:${user.id}")
                        Text(text = "姓名:${user.name}")
                        Text(text = "邮箱:${user.email}")
                    }
                }
            }
        }
    }
}

@Composable
fun UserDetailPage(id:Int,name:String,email:String,onBack:()->Unit){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Text(text = "用户详情", modifier = Modifier.padding(bottom = 20.dp))
        Text(text = "用户ID：$id")
        Text(text = "姓名：$name")
        Text(text = "邮箱：$email")
        Button(onClick = onBack, modifier = Modifier.padding(top = 30.dp)) {
            Text("返回列表")
        }
    }
}
