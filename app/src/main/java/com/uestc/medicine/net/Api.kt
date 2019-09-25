package com.uestc.medicine.net

import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.*

interface Api {

    @GET("cycle/v-tipr-physician/getcolumnbycompanyid.php")
    fun getMenus(@Query("company_id") company_id: String = "1", @Query("parent_id") parent_id: String = "0"): Observable<Response<Menu>>

    @GET("cycle/v-tipr-physician/getknowledgebycolumnid.php")
    fun getknowledgebycolumnid(@Query("column_id") column_id: String, @Query("user_id") user_id: String): Observable<Response<MenuDetails>>


    @GET("cycle/v-tipr-physician/getknowledgebyknowledgeid.php")
    fun getknowledgebyknowledgeid(@Query("knowledge_id") knowledge_id: String): Observable<Response<MenuDetails>>


    @POST("cycle/v-tipr-physician/loginfordoctor.php")
    @FormUrlEncoded
    fun login(@Field("loginname") loginname: String, @Field("pwd") pwd: String): Observable<Response<User>>

    @POST("cycle/v-tipr-physician/study.php")
    @FormUrlEncoded
    fun study(@Field("knowledge_id") knowledge_id: String, @Field("user_id") user_id: String): Observable<Response<Study>>

}
