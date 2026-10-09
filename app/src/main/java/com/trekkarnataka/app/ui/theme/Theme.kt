package com.trekkarnataka.app.ui.theme

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Forest = Color(0xFF1B5E20)
val Leaf = Color(0xFF43A047)
val Mint = Color(0xFFE8F5E9)
val Dark = Color(0xFF0B2E13)

@Composable
fun TrekTheme(content: @Composable () -> Unit) = MaterialTheme(
    colorScheme = lightColorScheme(primary = Forest, secondary = Leaf, background = Color(0xFFF6FBF6), surface = Color.White),
    content = content
)
