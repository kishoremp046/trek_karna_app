package com.trekkarnataka.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.trekkarnataka.app.ui.AppNav
import com.trekkarnataka.app.ui.TrekViewModel
import com.trekkarnataka.app.ui.theme.TrekTheme

class MainActivity : ComponentActivity() {
    private val vm: TrekViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { TrekTheme { AppNav(vm) } }
    }
}
