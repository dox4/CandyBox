package io.github.dox4.candybox.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.ExperimentalComposeUiApi
import com.google.accompanist.pager.ExperimentalPagerApi
import dagger.hilt.android.AndroidEntryPoint
import io.github.dox4.candybox.ui.nav.CandyNavigation
import io.github.dox4.candybox.ui.theme.CandyBoxTheme

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @OptIn(
        ExperimentalComposeUiApi::class,
        ExperimentalPagerApi::class,
        ExperimentalMaterialApi::class
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CandyBoxTheme {
                Surface(color = MaterialTheme.colors.background) {
                    CandyNavigation()
                }
            }
        }
    }
}