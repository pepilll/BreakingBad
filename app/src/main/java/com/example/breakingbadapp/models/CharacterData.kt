package com.example.breakingbadapp.models


import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CharacterData(
    var charId: Int?,
    var appearance: List<Int>?,
    var betterCallSaulAppearance: List<Int>?,
    var birthday: String?,
    var category: String?,
    var img: String?,
    var name: String,
    var nickname: String?,
    var occupation: List<String>?,
    var portrayed: String?,
    var status: String?
) : Parcelable