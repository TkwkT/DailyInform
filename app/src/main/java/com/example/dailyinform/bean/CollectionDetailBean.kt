package com.example.dailyinform.bean

import androidx.room.*
import com.google.gson.annotations.SerializedName

@Entity(indices  = [Index(value = ["title","url"],unique = true)], tableName = "collection_detail")
data class CollectionDetailBean(
    //图片
    @SerializedName("cover")
    var cover: String?,

    //标题
    @SerializedName("title")
    var title: String,

    //webView链接
    @SerializedName("url")
    var url: String

) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var gardenPlantingId: Long = 0
}