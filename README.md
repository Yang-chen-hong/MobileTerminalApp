# MobileTerminalApp - 移动终端应用开发实验

## 实验目标

在 Kotlin Compose 项目中完成一次真实网络请求，观察 HTTP 配置前后的差异，学习 Android 网络安全配置与 Git 版本控制。

## 实验内容

- 实验一：Compose 基础入门（初始版本）
- 实验二：网络访问与 HTTP/HTTPS 明文对比
- 实验三：Activity 生命周期与 Intent 页面传值
- 实验四：SharedPreferences 本地持久化存储
- 实验五：Compose LazyColumn 列表展示
- 实验六：OkHttp 网络请求解析 JSON
- 实验七：Compose Navigation 页面导航与参数传递

## 运行方式

1. 使用 Android Studio 打开项目
2. 等待 Gradle 同步完成
3. 连接 Android 模拟器或真机
4. 点击 Run 运行应用

## 接口地址

- HTTPS 测试接口：`https://httpbin.org/get?room=A203`
- HTTP 测试接口：`http://httpbin.org/get?room=A203`

> **注意**：HTTP 仅用于教学对比，实际服务优先使用 HTTPS。

## 分支说明

每个实验对应一个独立分支，可切换查看对应实验的完整代码：

- `lab1` - 实验一：Compose 基础入门
- `lab2` - 实验二：网络访问与 HTTP 明文对比
- `lab3` - 实验三：Activity 生命周期与 Intent 传值
- `lab4` - 实验四：SharedPreferences 本地存储
- `lab5` - 实验五：LazyColumn 列表展示
- `lab6` - 实验六：OkHttp + Gson 网络请求
- `lab7` / `master` - 实验七：Navigation 页面导航
