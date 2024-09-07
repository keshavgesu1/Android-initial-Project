package com.example.smarttask.networking
/*
 Created by "Jayant Sharma" on 20/05/20.
*/
sealed class DataResult<out T> {
    data class Loading(val nothing: Nothing? = null) : DataResult<Nothing>()
    data class Success<out T>(val data: T) : DataResult<T>()
    data class Failure(val message: String? = null, val exception: Exception? = null) :
        DataResult<Nothing>()
}