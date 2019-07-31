package com.example.dailyinform.bean
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


data class InformationClassifyBean_(
    @SerializedName("results")
    var results: List<Result>
){
    @Entity(tableName = "classify")
    data class Result(
        @PrimaryKey @ColumnInfo(name = "id")
        @SerializedName("id")
        var id: String,
        @SerializedName("icon")
        var icon: String,
        @SerializedName("title")
        var title: String
    ){
        var type:String = ""
    }
}