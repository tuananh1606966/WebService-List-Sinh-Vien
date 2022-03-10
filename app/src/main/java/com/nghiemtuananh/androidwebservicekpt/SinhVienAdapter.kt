package com.nghiemtuananh.androidwebservicekpt

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class SinhVienAdapter(var context: MainActivity, var layout: Int, var listSinhVien: List<SinhVien>): RecyclerView.Adapter<SinhVienAdapter.ViewHolderSinhVien>() {
    inner class ViewHolderSinhVien(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var txtTen: TextView
        var txtNamSinh: TextView
        var txtDiaChi: TextView
        var ibtnEdit: ImageButton
        var ibtnDelete: ImageButton

        init {
            txtTen = itemView.findViewById(R.id.tv_hoten)
            txtNamSinh = itemView.findViewById(R.id.tv_namsinh)
            txtDiaChi = itemView.findViewById(R.id.tv_diachi)
            ibtnEdit = itemView.findViewById(R.id.ibrn_edit)
            ibtnDelete = itemView.findViewById(R.id.ibtn_delete)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderSinhVien {
        var view = LayoutInflater.from(context).inflate(layout, parent, false)
        return ViewHolderSinhVien(view)
    }

    override fun onBindViewHolder(holder: ViewHolderSinhVien, position: Int) {
        var sinhVien = listSinhVien.get(position)
        if (sinhVien == null) {
            return
        }
        holder.txtTen.setText(sinhVien.hoTen)
        holder.txtNamSinh.setText("Năm sinh: ${sinhVien.namSinh}")
        holder.txtDiaChi.setText(sinhVien.diaChi)

        holder.ibtnEdit.setOnClickListener {
            var intent = Intent(context, UpdateSinhVienActivity::class.java)
            intent.putExtra("dataSinhVien", sinhVien)
            context.startActivity(intent)
        }

        holder.ibtnDelete.setOnClickListener {
            xacNhanXoa(sinhVien.hoTen, sinhVien.id.toString())
        }
    }

    override fun getItemCount(): Int {
        return listSinhVien.size
    }

    fun xacNhanXoa(ten: String, id: String) {
        var alertDialog = AlertDialog.Builder(context)
        alertDialog.setMessage("Bạn có muốn xoá sinh viên $ten không?")
        alertDialog.setPositiveButton("Có", DialogInterface.OnClickListener { dialog, which ->
            context.deleteThongTin(id)
        })

        alertDialog.setNegativeButton("Không", DialogInterface.OnClickListener { dialog, which -> })
        alertDialog.show()
    }
}