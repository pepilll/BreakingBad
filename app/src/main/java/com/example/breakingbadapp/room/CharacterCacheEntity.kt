package com.example.breakingbadapp.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "characters")
data class CharacterCacheEntity(

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "charId")
    var charId: Int?,

    @ColumnInfo(name = "appearance")
    var appearance: List<Int>?,

    @ColumnInfo(name = "better_call_saul_appearance")
    var betterCallSaulAppearance: List<Int>?,

    @ColumnInfo(name = "birthday")
    var birthday: String?,

    @ColumnInfo(name = "category")
    var category: String?,

    @ColumnInfo(name = "img")
    var img: String?,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "nickname")
    var nickname: String?,

    @ColumnInfo(name = "occupation")
    var occupation: List<String>?,

    @ColumnInfo(name = "portrayed")
    var portrayed: String?,

    @ColumnInfo(name = "status")
    var status: String?
)
