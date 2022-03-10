package com.nghiemtuananh.androidwebservicekpt

import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface GetApi {
    @GET ("getdata.php")
    fun getThongTin() : Observable<List<SinhVien>>

    @POST ("insert.php")
    @FormUrlEncoded
    fun postThongTin(
        @Field("hotenSV") hoTen: String,
        @Field("namsinhSV") namSinh: String,
        @Field("diachiSV") diaChi: String
    ) : Observable<List<String>>

    @POST ("/update.php")
    @FormUrlEncoded
    fun updateThongTin(
        @Field("idSV") id: String,
        @Field("hotenSV") hoTen: String,
        @Field("namsinhSV") namSinh: String,
        @Field("diachiSV") diaChi: String
    ) : Observable<List<String>>

    @POST ("/delete.php")
    @FormUrlEncoded
    fun deleteThongTin(
        @Field("idSV") id: String
    ) : Observable<List<String>>
}