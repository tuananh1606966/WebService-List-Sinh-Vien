package com.nghiemtuananh.androidwebservicekpt

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class SinhVien(
    @SerializedName("ID")
    var id: Int,
    @SerializedName("HoTen")
    var hoTen: String,
    @SerializedName("NamSinh")
    var namSinh: Int,
    @SerializedName("DiaChi")
    var diaChi: String
) : Serializable
