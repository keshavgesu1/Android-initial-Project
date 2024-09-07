package com.app.pravasiuttrakhandi.repository

import com.example.smarttask.model.BaseResponse
import com.example.smarttask.model.UserDetails
import com.example.smarttask.networking.ApiService
import com.example.smarttask.networking.DataResult
import com.example.smarttask.networking.NetworkOnlineDataRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response

@ExperimentalCoroutinesApi
class RegisterRepository(private val apiService: ApiService) {

    suspend fun login(value: UserDetails): Flow<DataResult<BaseResponse>> {
        return object : NetworkOnlineDataRepo<BaseResponse, BaseResponse>() {
            override suspend fun fetchDataFromRemoteSource(): Response<BaseResponse> {
                return apiService.login(value)
            }
        }.asFlow().flowOn(Dispatchers.IO)
    }

    suspend fun resgisterUser(value: UserDetails): Flow<DataResult<BaseResponse>> {
        return object : NetworkOnlineDataRepo<BaseResponse, BaseResponse>() {
            override suspend fun fetchDataFromRemoteSource(): Response<BaseResponse> {
                return apiService.registerUser(value)
            }
        }.asFlow().flowOn(Dispatchers.IO)
    }
}