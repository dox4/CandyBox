package io.github.dox4.candybox.ui.component

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.em

@Composable
fun TopAppBarText(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.h5,
        lineHeight = 1.2.em
    )
}