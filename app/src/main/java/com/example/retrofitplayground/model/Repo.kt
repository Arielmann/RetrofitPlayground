package com.example.retrofitplayground.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Repo (){
    @SerializedName("name")
    @Expose
    var name: String = ""
}
