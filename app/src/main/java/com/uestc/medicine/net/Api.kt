package com.uestc.medicine.net

import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET

interface Api {

    @GET("user/jsmith")
    fun user(): Observable<Response<User>>

    @GET("user/jsmith/tweets")
    fun tweets(): Observable<Response<ArrayList<Tweet>>>

}
