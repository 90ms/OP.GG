package com.a90ms.opgg.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import com.a90ms.domain.repository.ApiRepository
import com.a90ms.opgg.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var repository: ApiRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        findViewById<TextView>(R.id.tv1).setOnClickListener {
            lifecycleScope.launch {
                repository.getSummonerInfo()
            }
        }
        findViewById<TextView>(R.id.tv2).setOnClickListener {
            lifecycleScope.launch {
                repository.getGameInfoList(System.currentTimeMillis().toString())
            }
        }

    }
}