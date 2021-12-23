package io.github.dox4.candybox.data.vm

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.dox4.candybox.data.dao.BookOrWorldDao
import io.github.dox4.candybox.domain.BookOrWorld
import io.github.dox4.candybox.domain.BookOrWorldType
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookOrWorldViewModel @Inject constructor(private val dao: BookOrWorldDao) : ViewModel() {
    private var _name = mutableStateOf("")
    val name: State<String> = _name
    private var _desc = mutableStateOf("")
    val desc: State<String> = _desc

    fun updateName(name: String) {
        _name.value = name
    }

    fun updateDesc(desc: String) {
        _desc.value = desc
    }

    var type = BookOrWorldType.BOOK

    private var _state = mutableStateOf(BookOrWorld())
    val state = _state

    fun save() {
        viewModelScope.launch {
            dao.insertBookOrWorld(
                BookOrWorld(
                    name = name.value,
                    desc = desc.value,
                    type = type,
                )
            )
        }
    }

    fun findBookOrWorld(id: String) {
        viewModelScope.launch {
            _state.value = dao.findBookOrWorld(id)
        }
    }
}