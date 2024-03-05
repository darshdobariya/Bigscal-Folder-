package com.example.testfour.demo

import com.google.gson.annotations.SerializedName


data class WordData (

  @SerializedName("word"       ) var word       : String?              = null,
  @SerializedName("phonetic"   ) var phonetic   : String?              = null,
  @SerializedName("phonetics"  ) var phonetics  : ArrayList<Phonetics> = arrayListOf(),
  @SerializedName("meanings"   ) var meanings   : ArrayList<Meanings>  = arrayListOf(),
  @SerializedName("license"    ) var license    : License?             = License(),
  @SerializedName("sourceUrls" ) var sourceUrls : ArrayList<String>    = arrayListOf()

)