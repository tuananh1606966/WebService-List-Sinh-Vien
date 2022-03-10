package com.nghiemtuananh.androidwebservicekpt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var listSinhVien: ArrayList<SinhVien> = arrayListOf()
    lateinit var sinhVienAdapter: SinhVienAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var linearLayoutManager = LinearLayoutManager(this)
        rcv_sinhvien.layoutManager = linearLayoutManager
        var itemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        rcv_sinhvien.addItemDecoration(itemDecoration)
        sinhVienAdapter = SinhVienAdapter(this, R.layout.dong_sinh_vien, listSinhVien)
        rcv_sinhvien.adapter = sinhVienAdapter

        getData()
    }

    fun deleteThongTin(id: String) {
        var resOb: Observable<List<String>> = ThongTinFactor.createRetrofit().deleteThongTin(id)
        resOb.subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    if (it[0] == "success") {
                        Toast.makeText(this, "Xoá thành công", Toast.LENGTH_LONG).show()
                        getData()
                    } else {
                        Toast.makeText(this, "Xoá thất bại!", Toast.LENGTH_LONG).show()
                    }
                },
                {
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                },
                {}
            )
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.add_student, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_addstudent) {
            startActivity(Intent(this, AddSinhVienActivity::class.java))
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getData() {
        listSinhVien.clear()
        var resOb: Observable<List<SinhVien>> = ThongTinFactor.createRetrofit().getThongTin()
        resOb.subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    listSinhVien.addAll(it)
                    sinhVienAdapter.notifyDataSetChanged()
                },
                {
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                },
                {
                }
            )
    }
}