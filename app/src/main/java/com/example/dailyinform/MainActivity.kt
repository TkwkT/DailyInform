package com.example.dailyinform

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.example.dailyinform.constdata.itemNameList
import com.example.dailyinform.network.ClassifyService
import com.example.dailyinform.network.IdeaApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        canSlideFinish(false)

        replaceFragment(InformationFragment())
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.main_frame, fragment)
        fragmentTransaction.commit()
    }
}
