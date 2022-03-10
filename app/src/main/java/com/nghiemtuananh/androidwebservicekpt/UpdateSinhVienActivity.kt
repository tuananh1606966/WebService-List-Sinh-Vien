package com.nghiemtuananh.androidwebservicekpt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_update_sinh_vien.*

class UpdateSinhVienActivity : AppCompatActivity() {
    var hoTen: String = ""
    var namSinh: String = ""
    var diaChi: String = ""
    var id: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_sinh_vien)
        var sinhVien: SinhVien = intent.getSerializableExtra("dataSinhVien") as SinhVien
        edt_suahoten.setText(sinhVien.hoTen)
        edt_suanamsinh.setText(sinhVien.namSinh.toString())
        edt_suadiachi.setText(sinhVien.diaChi)

        btn_huy_sua.setOnClickListener {
            finish()
        }

        btn_xacnhan_sua.setOnClickListener {
            hoTen = edt_suahoten.text.toString().trim()
            namSinh = edt_suanamsinh.text.toString().trim()
            diaChi = edt_suadiachi.text.toString().trim()
            id = sinhVien.id.toString()
            if (hoTen.isEmpty() || namSinh.isEmpty() || diaChi.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đủ thông tin", Toast.LENGTH_LONG).show()
            } else {
                updateThongTin()
            }
        }
    }

    private fun updateThongTin() {
        var resOb: Observable<List<String>> =
            ThongTinFactor.createRetrofit().updateThongTin(id, hoTen, namSinh, diaChi)
        resOb.subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    if (it[0] == "success") {
                        Toast.makeText(this, "Cập nhật thành công", Toast.LENGTH_LONG).show()
                        startActivity(Intent(this, MainActivity::class.java))
                    } else {
                        Toast.makeText(this, "Cập nhật thất bại!", Toast.LENGTH_LONG).show()
                    }
                },
                {
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                },
                {}
            )
    }
}