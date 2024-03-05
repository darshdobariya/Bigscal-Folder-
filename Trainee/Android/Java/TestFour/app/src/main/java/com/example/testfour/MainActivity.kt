@file:OptIn(DelicateCoroutinesApi::class)

package com.example.testfour

import android.media.AudioManager
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.SearchView
import android.widget.TextView
import android.widget.Toast
import com.airbnb.lottie.LottieAnimationView
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var tvWord: TextView
    private lateinit var tvMeaning: TextView
    private lateinit var cgSynonyms: ChipGroup
    private lateinit var cgAntonyms: ChipGroup
    private lateinit var searchItem: SearchView
    private lateinit var imgSpeak: ImageView
    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var progress: ProgressBar
    private lateinit var btnClick: Button
    private lateinit var wName: String
    private lateinit var wUrl: String
    private var searchJob: Job? = null
    private lateinit var animation: LottieAnimationView
    private var wMeaning: MutableList<String> = mutableListOf()
    private var wSynonyms: MutableList<String> = mutableListOf()
    private var wAntonyms: MutableList<String> = mutableListOf()
    private val service = RetrofitFactory.makeRetrofitService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvWord = findViewById(R.id.tvWord)
        tvMeaning = findViewById(R.id.tvMeaning)
        cgSynonyms = findViewById(R.id.cgSynonyms)
        cgAntonyms = findViewById(R.id.cgAntonyms)
        imgSpeak = findViewById(R.id.imgSpeak)
        progress = findViewById(R.id.progress)
        btnClick = findViewById(R.id.btnClick)
        animation = findViewById(R.id.animation)

        animation.visibility = View.VISIBLE

        searchItem = findViewById(R.id.searchItem)
        searchItem.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextChange(newText: String): Boolean {

                if (searchJob?.isActive == true) {
                    // If a job is active, cancel it
                    searchJob?.cancel()
                }

                searchJob  = CoroutineScope(Dispatchers.Main).launch{
                    delay(1000)

                    if (newText.trim() != "" || newText.trim() != " "){
                        progress.visibility = View.VISIBLE
                        newRequest(newText)
                    }else{
                        animation.visibility = View.VISIBLE
                    }
                }

                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

        })

        btnClick.setOnClickListener(View.OnClickListener {
            cgAntonyms.removeAllViews()
            cgSynonyms.removeAllViews()
        })

        imgSpeak.setOnClickListener(View.OnClickListener {
            mediaPlayer = MediaPlayer()
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC)

            try {
                if (wUrl.trim().length > 10){
                    mediaPlayer.setDataSource(wUrl)
                    mediaPlayer.prepare()
                    mediaPlayer.start()
                    Log.e("wUrl print : ", wUrl)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        })
    }

    private fun newRequest(word: String){
        CoroutineScope(Dispatchers.Main).launch {
            try{
                val data = service.getPosts(word)

                val size = 0

                wName = data[size].word.toString()
                wUrl = data[size].phonetics[size].audio.toString()

                wMeaning.clear()
                for (definition in data[size].meanings[size].definitions) { // Use a loop to iterate over definitions
                    wMeaning.add(definition.definition.toString())
                }

                wSynonyms.clear()
                for (synonyms in data[size].meanings){
                    wSynonyms.add(synonyms.synonyms.toString())
                }

                wAntonyms.clear()
                for (antonyms in data[size].meanings){
                    wAntonyms.add(antonyms.antonyms.toString())
                }

                setDataInUI();

            }catch (e: Exception){
                Log.e("Failed to retrieve data : ", e.toString())
//                Toast.makeText(applicationContext, "Failed to retrive data", Toast.LENGTH_SHORT).show()
                progress.visibility = View.GONE
                animation.visibility = View.VISIBLE
            }
        }
    }

    private fun setDataInUI(){
        // Set Word Name
        tvWord.text  = wName
        animation.visibility = View.GONE

        // Set meanings
        var text = "";
        for (x in 0 until wMeaning.size){
            text = if (x == 0){
                wMeaning.get(x)
            }else text + " " + wMeaning.get(x)
        }
        tvMeaning.text = text

        cgAntonyms.removeAllViews()
        cgSynonyms.removeAllViews()

        // Synonyms
        for (x in wSynonyms) {
            addChip(x, cgSynonyms)
        }

        // Antonyms
        for (x in wAntonyms) {
            addChip(x, cgAntonyms)
        }

        progress.visibility = View.GONE
    }

    private fun addChip(inputString: String, chipGroup: ChipGroup) {
        val separatedStrings = inputString
            .substring(1, inputString.length - 1)
            .split(", ")

        for (string in separatedStrings) {
            if (string.trim() == "" || string.trim() == "") {
                continue
            } else {
                val chip = Chip(this)
                chip.text = string
                chip.isClickable = false
                chip.isCheckable = false
                chip.setChipBackgroundColorResource(R.color.green_dark)
                chip.setTextColor(resources.getColor(R.color.white))

                chip.setOnClickListener(View.OnClickListener {
                    progress.visibility = View.VISIBLE
                    newRequest(string)
                })

                chipGroup.addView(chip)
            }
        }
    }
}