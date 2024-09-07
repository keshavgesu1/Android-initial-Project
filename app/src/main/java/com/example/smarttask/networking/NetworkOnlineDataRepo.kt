package com.example.smarttask.networking

import androidx.annotation.MainThread

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Response

/*
 Created by "Jayant Sharma" on 20/05/20.
*/
@ExperimentalCoroutinesApi
abstract class NetworkOnlineDataRepo<RESULT, REQUEST> {
    fun asFlow() = flow {
        emit(DataResult.Loading())
        try {
            val apiResponse = fetchDataFromRemoteSource()
            val data = apiResponse.body()

            if (apiResponse.isSuccessful && data != null) {
                emit(DataResult.Success(data))
            } else {
                // emit(DataResult.Failure("Something went wrong!"))
                if (apiResponse.code() == 401) {
                    // logout the user
//                    clearAllData(Covid19App.appContext!!)
//                    InitialActivity.launchActivity(Covid19App.appContext!!)
                } else {
                    emit(DataResult.Failure(getErrorMsg(apiResponse.errorBody()!!)))
                }
            }
        } catch (e: Exception) {
            emit(
                DataResult.Failure(
                    e.message
                )
            )
        }

    }

    //    private fun getErrorMessage(e: HttpException): String {
//        return getErrorMsg(e.response()?.errorBody()!!)
//    }
    fun getErrorMsg(responseBody: ResponseBody): String {

        try {
            val jsonObject = JSONObject(responseBody.string())

            return jsonObject.getString("message")

        } catch (e: Exception) {
            return e.message!!
        }

    }

    @MainThread
    protected abstract suspend fun fetchDataFromRemoteSource(): Response<REQUEST>
}