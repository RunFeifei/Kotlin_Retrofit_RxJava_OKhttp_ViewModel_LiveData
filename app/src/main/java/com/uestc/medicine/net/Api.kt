package com.uestc.medicine.net

import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface Api {

    @GET("user/jsmith")
    fun user(): Observable<Response<User>>


    @POST("cycle/v-tipr-physician/loginfordoctor.php")
    @FormUrlEncoded
    fun login(@Field("loginname") loginname:String,@Field("pwd") pwd:String): Observable<Response<User>>

}
