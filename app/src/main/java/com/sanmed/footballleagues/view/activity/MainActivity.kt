
package com.sanmed.footballleagues.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sanmed.footballleagues.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}