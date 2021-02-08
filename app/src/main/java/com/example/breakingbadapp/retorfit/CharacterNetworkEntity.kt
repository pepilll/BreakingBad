package com.example.breakingbadapp.retorfit

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CharacterNetworkEntity(
    @SerializedName("char_id")
    @Expose
    var charId: Int?,

    @SerializedName("appearance")
    @Expose
    var appearance: List<Int>?,

    @SerializedName("better_call_saul_appearance")
    @Expose
    var betterCallSaulAppearance: List<Int>?,

    @SerializedName("birthday")
    @Expose
    var birthday: String?,

    @SerializedName("category")
    @Expose
    var category: String?,

    @SerializedName("img")
    @Expose
    var img: String?,

    @SerializedName("name")
    @Expose
    var name: String,

    @SerializedName("nickname")
    @Expose
    var nickname: String?,

    @SerializedName("occupation")
    @Expose
    var occupation: List<String>?,

    @SerializedName("portrayed")
    @Expose
    var portrayed: String?,

    @SerializedName("status")
    @Expose
    var status: String?
)