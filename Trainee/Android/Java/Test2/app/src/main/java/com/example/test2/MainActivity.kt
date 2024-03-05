package com.example.test2

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.test2.bottomnav.HomeFragment
import com.example.test2.bottomnav.LogOutFragment
import com.example.test2.bottomnav.SettingFragment

class MainActivity : AppCompatActivity() {

    private lateinit var cvHome: CardView
    private lateinit var cvSetting: CardView
    private lateinit var cvLogout: CardView
    private lateinit var cvIndHome: CardView
    private lateinit var cvIndSetting: CardView
    private lateinit var cvIndLogout: CardView
    private lateinit var imgHome: ImageView
    private lateinit var imgSetting: ImageView
    private lateinit var imgLogout: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cvHome = findViewById(R.id.cvHome)
        cvIndHome = findViewById(R.id.cvIndHome)
        imgHome = findViewById(R.id.imgHome)

        cvSetting = findViewById(R.id.cvSetting)
        cvIndSetting = findViewById(R.id.cvIndSetting)
        imgSetting = findViewById(R.id.imgSetting)

        cvLogout = findViewById(R.id.cvLogout)
        cvIndLogout = findViewById(R.id.cvIndLogout)
        imgLogout = findViewById(R.id.imgLogout)

        selectFragment(HomeFragment(), imgHome, cvIndHome)

        cvHome.setOnClickListener { selectFragment(HomeFragment(), imgHome, cvIndHome) }

        cvLogout.setOnClickListener { selectFragment(LogOutFragment(), imgLogout, cvIndLogout) }

        cvSetting.setOnClickListener { selectFragment(SettingFragment(), imgSetting, cvIndSetting) }
    }

    private fun selectFragment(fragment: Fragment, imgView: ImageView, cardIndView: CardView) {
        resetNavigationBar()

        supportFragmentManager.beginTransaction()
            .replace(R.id.frmMain, fragment)
            .commit()

        imgView.setColorFilter(ContextCompat.getColor(applicationContext, R.color.orange_light), android.graphics.PorterDuff.Mode.SRC_IN)

        cardIndView.setCardBackgroundColor(ContextCompat.getColor(applicationContext, R.color.orange_light))
    }

    private fun resetNavigationBar() {
        imgHome.setColorFilter(ContextCompat.getColor(applicationContext, R.color.black), android.graphics.PorterDuff.Mode.SRC_IN)
        imgLogout.setColorFilter(ContextCompat.getColor(applicationContext, R.color.black), android.graphics.PorterDuff.Mode.SRC_IN)
        imgSetting.setColorFilter(ContextCompat.getColor(applicationContext, R.color.black), android.graphics.PorterDuff.Mode.SRC_IN)

        cvHome.setCardBackgroundColor(Color.TRANSPARENT)
        cvLogout.setCardBackgroundColor(Color.TRANSPARENT)
        cvSetting.setCardBackgroundColor(Color.TRANSPARENT)

        cvIndHome.setCardBackgroundColor(Color.TRANSPARENT)
        cvIndLogout.setCardBackgroundColor(Color.TRANSPARENT)
        cvIndSetting.setCardBackgroundColor(Color.TRANSPARENT)
    }
}