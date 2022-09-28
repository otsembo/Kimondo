package com.otsembo.kimondo.common

sealed class AppResource <T> (data: T? = null, message:String? = null){
    class Loading <T>(data: T? = null): AppResource<T>(data)
    class Success <T>(data: T, message: String? = null): AppResource<T>(data, message)
    class Error <T>(message: String): AppResource<T>(message = message)
    class Idle<T> : AppResource<T>()
}