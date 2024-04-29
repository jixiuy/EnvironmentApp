package com.example.enviroment

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@Composable
fun ExploreScreen() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Title()
        Spacer(modifier = Modifier.height(2.dp).background(color = Color.Gray).fillMaxWidth())
        Data()
    }
}


@Composable
fun Title() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        // 添加 "智能监控系统" 文字，设置字号大小为 20sp
        Text(
            text = "智能监控系统",
            style = MaterialTheme.typography.subtitle1,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(end = 8.dp)
                .weight(1f)
        )

        // 添加日期
        val currentDate = remember { Calendar.getInstance().time }
        val formattedDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(currentDate)
        Text(
            text = "$formattedDate",
            style = MaterialTheme.typography.subtitle1,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .weight(1f)
                .border(
                    width = 1.dp,
                    color = Color.Black,
                    shape = RoundedCornerShape(8.dp)
                )
        )

        // 添加房间号
        Text(
            text = "11A401",
            style = MaterialTheme.typography.subtitle1,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(start = 8.dp)
                .weight(1f)
                .border(
                    width = 1.dp,
                    color = Color.Black,
                    shape = RoundedCornerShape(8.dp)
                )
        )
    }
}
@Composable
fun Data() {
    // 示例数据，替换为您自己的实际数据
    val violationsList = remember {
        mutableListOf(
            ViolationItem("2024-04-30", "08:30", Color.Yellow, "未关水"),
            ViolationItem("2024-04-30", "10:15", Color.Red, "未关空调"),
            ViolationItem("2024-04-30", "13:45", Color.Yellow, "未关灯"),
            ViolationItem("2024-04-30", "14:30", Color.Yellow, "未关水"),
            ViolationItem("2024-04-30", "16:20", Color.Red, "未关空调"),
            ViolationItem("2024-04-30", "17:45", Color.Yellow, "未关水"),
            ViolationItem("2024-04-30", "19:15", Color.Yellow, "未关灯"),
            ViolationItem("2024-04-30", "20:30", Color.Red, "未关空调"),
            ViolationItem("2024-04-30", "22:10", Color.Yellow, "未关水"),
            ViolationItem("2024-04-30", "23:45", Color.Red, "未关空调"),
            ViolationItem("2024-05-01", "08:30", Color.Yellow, "未关水"),
            ViolationItem("2024-05-01", "10:15", Color.Red, "未关空调"),
            ViolationItem("2024-05-01", "13:45", Color.Yellow, "未关灯"),
            ViolationItem("2024-05-01", "14:30", Color.Yellow, "未关水"),
            ViolationItem("2024-05-01", "16:20", Color.Red, "未关空调"),
            ViolationItem("2024-05-01", "17:45", Color.Yellow, "未关水"),
            ViolationItem("2024-05-01", "19:15", Color.Yellow, "未关灯"),
            ViolationItem("2024-05-01", "20:30", Color.Red, "未关空调"),
            ViolationItem("2024-05-01", "22:10", Color.Yellow, "未关水"),
            ViolationItem("2024-05-01", "23:45", Color.Red, "未关空调"),
            ViolationItem("2024-05-02", "08:30", Color.Yellow, "未关水"),
            ViolationItem("2024-05-02", "10:15", Color.Red, "未关空调"),
            ViolationItem("2024-05-02", "13:45", Color.Yellow, "未关灯"),
            ViolationItem("2024-05-02", "14:30", Color.Yellow, "未关水"),
            ViolationItem("2024-05-02", "16:20", Color.Red, "未关空调"),
            ViolationItem("2024-05-02", "17:45", Color.Yellow, "未关水"),
            ViolationItem("2024-05-02", "19:15", Color.Yellow, "未关灯"),
            ViolationItem("2024-05-02", "20:30", Color.Red, "未关空调"),
            ViolationItem("2024-05-02", "22:10", Color.Yellow, "未关水"),
            ViolationItem("2024-05-02", "23:45", Color.Red, "未关空调")
        )
    }

    // 状态用于控制删除按钮对话框的显示与隐藏
    var showDialog by remember { mutableStateOf(false) }
    // 状态用于记录当前选中的违规项目
    var selectedItem by remember { mutableStateOf<ViolationItem?>(null) }

    val onDeleteConfirmed: () -> Unit = {
        showDialog = false
        selectedItem?.let { item ->
            violationsList.remove(item)
            selectedItem = null // 清除选中状态
        }
    }


    // 函数处理取消删除操作
    val onDeleteCanceled = {
        showDialog = false
        selectedItem = null // 清除选中状态
    }


    LazyColumn {
        items(violationsList) { item ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
                    .border(width = 1.dp, color = Color.Gray, shape = RoundedCornerShape(4.dp)) // 添加边框
                    .padding(2.dp) // 设置外边距为2dp
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(5.dp), // 将Row的modifier设置为fillMaxWidth
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // 添加日期和时间
                    Text(
                        text = "${item.date} ${item.time}",
                        style = MaterialTheme.typography.body1,
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center,
                    )

                    // 添加带有颜色的小圆圈
                    Box(
                        modifier = Modifier
                            .size(16.dp)
                            .background(color = item.color, shape = CircleShape)
                            .align(Alignment.CenterVertically)
                    )

                    // 添加违规事情
                    Text(
                        text = item.violation,
                        style = MaterialTheme.typography.body1,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.weight(1f)
                    )

                    // 添加删除按钮
                    DeleteButton {
                        selectedItem = item // 设置选中的违规项目
                        showDialog = true // 显示删除对话框
                    }
                }
            }
        }
    }
    // 删除对话框
    if (showDialog) {
        AlertDialog(
            onDismissRequest = onDeleteCanceled,
            title = {
                Text(text = "确认删除？")
            },
            text = {
                Text(text = "是否确认删除此违规记录？")
            },
            confirmButton = {
                Button(onClick = onDeleteConfirmed,colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)) {
                    Text("确认")
                }
            },
            dismissButton = {
                Button(onClick = onDeleteCanceled,colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)) {
                    Text("取消")
                }
            },
            properties = DialogProperties(dismissOnClickOutside = false)
        )
    }
}

@Composable
fun DeleteButton(onClick: () -> Unit) {
    IconButton(
        onClick = onClick,
        modifier = Modifier.size(24.dp)
    ) {
        Icon(
            imageVector = Icons.Default.MoreVert,
            contentDescription = "Delete"
        )
    }
}

// 数据类，表示违规事项
data class ViolationItem(
    val date: String,
    val time: String,
    val color: Color,
    val violation: String
)
