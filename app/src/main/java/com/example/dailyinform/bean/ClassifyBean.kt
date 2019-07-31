package com.example.dailyinform.bean

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "classify")
data class ClassifyBean(
    //子分类英文
    @PrimaryKey @ColumnInfo(name = "id")
    @SerializedName("id")
    var id: String,
    //子分类图标，暂不用
    @SerializedName("icon")
    var icon: String,
    //子分类名称
    @SerializedName("title")
    var title: String
){
    //所属大类
    var type:String = ""
}
