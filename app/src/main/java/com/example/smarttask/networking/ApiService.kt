package com.example.smarttask.networking

import com.example.smarttask.model.BaseResponse
import com.example.smarttask.model.UserDetails
import com.example.smarttask.utility.Constants
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @POST(Constants.LOGIN)
    suspend fun login(@Body value: UserDetails): Response<BaseResponse>

    @POST(Constants.REGISTER)
    suspend fun registerUser(@Body value: UserDetails): Response<BaseResponse>
}