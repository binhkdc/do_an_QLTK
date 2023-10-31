package t3h.android.do_an_qlbh.Controller;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import t3h.android.do_an_qlbh.Entity.KhachHang;
import t3h.android.do_an_qlbh.Entity.LoaiSP;
import t3h.android.do_an_qlbh.Entity.SanPham;
import t3h.android.do_an_qlbh.MessageError;
import t3h.android.do_an_qlbh.R;

public class SelectTableController {

    MessageError messageError;
    String error;
    ////////////////////////////Select_KH////////////////////////////////////
    CircleImageView circleImageView;
    int MAKH, NumberPhone, IMGKH;
    String TENKH, DiaChi, GioiTinh, NgaySinh;
    TextView txtRowMaKH, txtRowTenKH, txtRowGioiTinh, txtRowPhoneKH;
    //////////////////////////////////Select_SP///////////////////////////////////////
    TextView txtRowMaSP, txtRowTenSP, txtRowSLSP, txtRowTrangThai;

    int MASP, SLSP_;

    String TENSP;
    //////////////////////////////////Select_LSP////////////////////////////
    TextView txtRowMaLoaiSP, txtRowTenLoaiSP;

    int MALOAISP;
    String TENLOAISP;

    ///////////////////////////////Select_KH//////////////////////////////////
    public void findIdSelectKH(View view) {
        txtRowMaKH = view.findViewById(R.id.txtRowMaKH_SELECT);
        txtRowTenKH = view.findViewById(R.id.txtRowTenKH_SELECT);
        txtRowGioiTinh = view.findViewById(R.id.txtRowGioiTinhKH_SELECT);
        txtRowPhoneKH = view.findViewById(R.id.txtRowPhoneKH_SELECT);
        circleImageView = view.findViewById(R.id.RowImageKH_SELECT);
    }

    public void getDataKhachHang(int position, ArrayList<KhachHang> khachHangs) {
        KhachHang khachHang = khachHangs.get(position);
        MAKH = khachHang.getMaKH();
        TENKH = khachHang.getTenKH();
        NumberPhone = khachHang.getNumberPhone();
        DiaChi = khachHang.getDiaChi();
        GioiTinh = khachHang.getGioiTinh();
        NgaySinh = khachHang.getNgaySinh();
        IMGKH = khachHang.getImageKH();
    }

    public void setDataRowKH() {
        txtRowMaKH.setText("Mã Khách Hàng: " + MAKH + "");
        txtRowTenKH.setText("Tên Khách Hàng: " + TENKH);
        txtRowGioiTinh.setText("Giới Tính: " + GioiTinh);
        txtRowPhoneKH.setText("NumberPhone: " + NumberPhone);
        circleImageView.setImageResource(IMGKH);
    }

    /////////////////////////////Select_SP///////////////////////////////////
    public void findIdRowSP(View view) {
        txtRowMaSP = view.findViewById(R.id.txtRowMaSP);
        txtRowTenSP = view.findViewById(R.id.txtRowNameSP);
        txtRowSLSP = view.findViewById(R.id.txtRowSoluongSP);
        txtRowTrangThai = view.findViewById(R.id.txtRowTrangThaiSP);
    }

    public void getDataSanPham(int position, ArrayList<SanPham> sanPhams) {
        SanPham sanPham = sanPhams.get(position);
        MASP = sanPham.getMASP();
        TENSP = sanPham.getNAMESP();
        SLSP_ = sanPham.getSOLUONGNHAP();
    }

    public void setDataRowSP() {
        txtRowMaSP.setText(MASP + "");
        txtRowTenSP.setText(TENSP);
        txtRowSLSP.setText(SLSP_ + "");
        if (SLSP_ == 0) {
            txtRowTrangThai.setText("Hết Hàng");
            txtRowTrangThai.setTextColor(Color.parseColor("#ED2213"));
        }
        if (SLSP_ > 0) {
            txtRowTrangThai.setText("Còn Hàng");
            txtRowTrangThai.setTextColor(Color.parseColor("#4CAF50"));
        }
    }
    ///////////////////////////////Select_LSP//////////////////////////////////

    public void findIdRowLSP(View view) {
        txtRowMaLoaiSP = view.findViewById(R.id.txtRowMaLoaiSP);
        txtRowTenLoaiSP = view.findViewById(R.id.txtRowNameLoaiSP);
    }

    public void getDataLSP(int position, ArrayList<LoaiSP> loaiSPS) {
        LoaiSP loaiSP = loaiSPS.get(position);

        MALOAISP = loaiSP.getMaLoai();
        TENLOAISP = loaiSP.getTenLoai();

    }

    public void setDataLSP() {
        txtRowMaLoaiSP.setText(MALOAISP + "");
        txtRowTenLoaiSP.setText(TENLOAISP);
    }
}
