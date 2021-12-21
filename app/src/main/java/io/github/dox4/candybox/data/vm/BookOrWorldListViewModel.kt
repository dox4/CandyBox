package io.github.dox4.candybox.data.vm

import android.util.Log
import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.dox4.candybox.data.repo.BookOrWorldRepository
import io.github.dox4.candybox.data.state.ResultState
import io.github.dox4.candybox.data.state.ResultStatus
import io.github.dox4.candybox.domain.BookOrWorld
import io.github.dox4.candybox.domain.BookOrWorldType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookOrWorldListViewModel @Inject constructor(private val repo: BookOrWorldRepository) :
    ViewModel() {
    private val _state =
        MutableStateFlow<ResultState<List<BookOrWorld>>>(
            ResultState(
                ResultStatus.LOADING,
                emptyList()
            )
        )
    val state: StateFlow<ResultState<List<BookOrWorld>>> = _state

    private val _update = MutableLiveData(false)
    private val _bookOrWorlds = _update.switchMap {
        repo.findBookOrWorlds(BookOrWorldType.BOOK).asLiveData(viewModelScope.coroutineContext)
    }.switchMap {
        MutableLiveData(it.data)
    }
    val bookOrWorlds = _bookOrWorlds

    fun findBookOrWorld(type: BookOrWorldType) {
        _state.value = ResultState.loading()
        viewModelScope.launch {
            repo.findBookOrWorlds(type)
                .catch {
                    Log.d(javaClass.canonicalName, "catch exception: ${it.message}")
                    _state.value = ResultState.error(it.message.toString())
                }.collect {
                    Log.d(javaClass.canonicalName, "read data: ${it.data?.size}")
                    _state.value = ResultState.success(it.data!!)
                }
        }
    }

//    fun add(bw: BookOrWorld) = viewModelScope.launch { repo.insertBookOrWorld(bw) }
//    fun update(bw: BookOrWorld) = viewModelScope.launch { repo.updateBookOrWorld(bw) }
//    fun delete(bw: BookOrWorld) = viewModelScope.launch { repo.deleteBookOrWorld(bw) }
}