package com.nghiemtuananh.androidwebservicekpt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_add_sinh_vien.*

class AddSinhVienActivity : AppCompatActivity() {
    lateinit var hoTen: String
    lateinit var namSinh: String
    lateinit var diaChi: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_sinh_vien)

        btn_huy.setOnClickListener {
            finish()
        }

        btn_them.setOnClickListener {
            hoTen = edt_themhoten.text.toString().trim()
            namSinh = edt_themnamsinh.text.toString().trim()
            diaChi = edt_themdiachi.text.toString().trim()
            if (hoTen.isEmpty() || namSinh.isEmpty() || diaChi.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đủ thông tin", Toast.LENGTH_LONG).show()
            } else {
                postThongTin()
            }
        }
    }

    private fun postThongTin() {
        var resOb: Observable<List<String>> = ThongTinFactor.createRetrofit().postThongTin(
            hoTen,
            namSinh,
            diaChi
        )
        resOb.subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    if (it[0] == "success") {
                        Toast.makeText(this, "Thêm thành công", Toast.LENGTH_LONG).show()
                        startActivity(Intent(this, MainActivity::class.java))
                    } else {
                        Toast.makeText(this, "Lỗi thêm!", Toast.LENGTH_LONG).show()
                    }
                },
                {
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                },
                {
                }
            )
    }
}