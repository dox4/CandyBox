package io.github.dox4.candybox.viewmodel

import androidx.lifecycle.ViewModel
import java.util.*

class SavedViewModel : ViewModel() {
    private val map: MutableMap<String, String> = mutableMapOf()
    fun save() {}
    fun update(key: UUID, value: String) {
        map[key.toString()] = value
    }
}