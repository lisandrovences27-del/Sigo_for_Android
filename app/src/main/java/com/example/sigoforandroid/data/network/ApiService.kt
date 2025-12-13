package com.example.sigoforandroid.data.network

//import androidx.tracing.perfetto.handshake.protocol.Response
import com.example.sigoforandroid.data.model.LoginRequest
import com.example.sigoforandroid.data.model.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("auth")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>
}