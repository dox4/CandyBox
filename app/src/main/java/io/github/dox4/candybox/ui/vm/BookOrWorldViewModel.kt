package io.github.dox4.candybox.ui.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.dox4.candybox.data.dao.BookOrWorldDao
import io.github.dox4.candybox.domain.BookOrWorld
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookOrWorldViewModel @Inject constructor(private val dao: BookOrWorldDao) :
    ViewModel() {
    private val _state = MutableStateFlow<List<BookOrWorld>>(emptyList())
    val state = _state.asStateFlow()

    fun add(bw: BookOrWorld) = viewModelScope.launch { dao.insertBookOrWorld(bw) }
    fun update(bw: BookOrWorld) = viewModelScope.launch { dao.updateBookOrWorld(bw) }
    fun delete(bw: BookOrWorld) = viewModelScope.launch { dao.deleteBookOrWorld(bw) }
}