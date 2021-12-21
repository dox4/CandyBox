package io.github.dox4.candybox.data.state


data class ResultState<out T>(val status: ResultStatus, val data: T?, val message: String = "") {
    companion object {
        fun <T> success(data: T): ResultState<T> {
            return ResultState(ResultStatus.SUCCESS, data)
        }

        fun <T> error(message: String): ResultState<T> {
            return ResultState(ResultStatus.ERROR, null, message)
        }

        fun <T> loading(): ResultState<T> {
            return ResultState(ResultStatus.LOADING, null)
        }
    }
}

enum class ResultStatus {
    SUCCESS,
    ERROR,
    LOADING,
}