package io.github.dox4.candybox

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.room.Room
import io.github.dox4.candybox.component.HomeScaffold
import io.github.dox4.candybox.entity.BoxDatabase
import io.github.dox4.candybox.ui.theme.CandyBoxTheme

class MainActivity : AppCompatActivity() {


    companion object {
        lateinit var db: BoxDatabase
        val fabActions: Map<String, @Composable () -> Unit> = HashMap()
        var currentFabHolder: String? = null

    }

    @OptIn(ExperimentalComposeUiApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        db = Room.databaseBuilder(
            applicationContext,
            BoxDatabase::class.java,
            "candybox"
        ).allowMainThreadQueries()
            .addMigrations()
            .build()
//        db.initProperty()

        setContent {
            CandyBoxTheme {
                HomeScaffold()
            }
        }
    }
}