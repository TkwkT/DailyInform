package com.example.dailyinform.bean

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "detail")
data class DetailBean(
    //图片
    @SerializedName("cover")
    var cover: String?,

    //http数据
    @SerializedName("raw")
    var raw: String,

    //标题
    @SerializedName("title")
    var title: String,

    //webView链接
    @SerializedName("url")
    var url: String
){
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var detailId: Long = 0

    @ColumnInfo(name = "classify_type")
    var classifyType:String = ""
}