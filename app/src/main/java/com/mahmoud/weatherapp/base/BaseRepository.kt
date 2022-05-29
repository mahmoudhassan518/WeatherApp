package com.mahmoud.weatherapp.base

import com.google.gson.Gson
import com.mahmoud.weatherapp.core.data.model.ErrorResponse
import com.mahmoud.weatherapp.core.exception.UnexpectedResponseException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.ResponseBody
import retrofit2.HttpException
import timber.log.Timber

abstract class BaseRepository(
    private val ioDispatcher: CoroutineDispatcher
) {

    fun <T> requestHandler(
        errorMap: Map<Int, Throwable> = emptyMap(),
        fetch: suspend () -> T,
    ) = flow {
        try {
            emit(fetch.invoke())
        } catch (throwable: Throwable) {
            Timber.e("error happen in requestHandler $throwable")
            if (throwable is HttpException)
                throw throwable.getHttpException(errorMap)
            else
                throw throwable
        }
    }.flowOn(ioDispatcher)

    private fun HttpException.getHttpException(errorMap: Map<Int, Throwable>): Throwable =
        try {
            val body: ResponseBody? = this.response()!!.errorBody()

            val errorsResponse =
                Gson().fromJson(body!!.string(), ErrorResponse::class.java)

            errorMap[this.response()?.code()] ?: errorMap[errorsResponse.cod]
                ?: UnexpectedResponseException
        } catch (e: Exception) {
            UnexpectedResponseException
        }
}
