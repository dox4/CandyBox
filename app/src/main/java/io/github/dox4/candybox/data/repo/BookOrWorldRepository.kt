package io.github.dox4.candybox.data.repo

import io.github.dox4.candybox.data.dao.BookOrWorldDao
import io.github.dox4.candybox.data.state.ResultState
import io.github.dox4.candybox.domain.BookOrWorldType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class BookOrWorldRepository @Inject constructor(val dao: BookOrWorldDao) {
    fun findBookOrWorlds(type: BookOrWorldType) = flow {
        val bws = dao.findBookOrWorlds(type = type)
        emit(ResultState.success(bws))
    }.flowOn(Dispatchers.IO)

}