package t3h.android.do_an_qlbh.FragmentQL;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import t3h.android.do_an_qlbh.AdapteData.HoaDon_Adapter;
import t3h.android.do_an_qlbh.Entity.HoaDon;
import t3h.android.do_an_qlbh.Entity.HoaDonCT;
import t3h.android.do_an_qlbh.Entity.KhachHang;
import t3h.android.do_an_qlbh.Entity.SanPham;
import t3h.android.do_an_qlbh.Database.SQLife;
import t3h.android.do_an_qlbh.MessageError;
import t3h.android.do_an_qlbh.R;
import t3h.android.do_an_qlbh.SelectTable.AdapterSelect_KH;
import t3h.android.do_an_qlbh.SelectTable.AdapterSelect_SP;

import java.util.ArrayList;

public class Fragment_HoaDon extends Fragment {

    MessageError messageError;
    int MANV;
    Context contextm;
    ArrayList<SanPham> sanPhams;

    String error;

    public Fragment_HoaDon(int MANV) {
        this.MANV = MANV;
    }

    public int getMANV() {
        return MANV;
    }

    public void setMANV(int MANV) {
        this.MANV = MANV;
    }

    TextView tvMAKH, tvTENKH, tvNgayMua, tvMASP, tvTENSP, tvTONGTIENHANG, tvTrangThai;

    Button btnSelectKH, btnSelectSP, btnSelectNgayMua, btnTrue, btnFalse, btnSave, btnAdd;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.layout_fragment_hd, container, false);
        contextm = container.getContext();
        ListView listView = view.findViewById(R.id.lvHD);
        btnAdd = view.findViewById(R.id.btnAddHD);
        SQLife sqLife = new SQLife(container.getContext());
        ArrayList<HoaDon> hoaDons = sqLife.getALLHoaDon();
        HoaDon_Adapter hoaDon_adapter = new HoaDon_Adapter(hoaDons);
        listView.setAdapter(hoaDon_adapter);

        messageError = new MessageError();
        btnAdd.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
            View viewShow = inflater.inflate(R.layout.show_add_hd, container, false);
            builder.setView(viewShow);
            Dialog dialog = builder.create();
            dialog.show();
            SQLife sqLife12 = new SQLife(viewShow.getContext());
            sanPhams = sqLife12.getALLSP();
            findIdTextview(viewShow);
            findIdButton(viewShow);
            btnTrue.setOnClickListener(v1 -> tvTrangThai.setText("Đã Thanh Toán"));
            btnFalse.setOnClickListener(v12 -> tvTrangThai.setText("Chưa Thanh Toán"));
            btnSelectNgayMua.setOnClickListener(view1 -> {
                messageError.DialogDatePicker(view1,tvNgayMua);
            });
            btnSelectSP.setOnClickListener(v14 -> {
                onclickSelectSP(v14, container);
            });
            btnSelectKH.setOnClickListener(v15 -> {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(v15.getContext());
                View view1 = LayoutInflater.from(v15.getContext()).inflate(R.layout.select_table_kh, container, false);
                builder1.setView(view1);
                Dialog dialog1 = builder1.create();
                dialog1.show();
                SQLife sqLife121 = new SQLife(view1.getContext());
                ArrayList<KhachHang> khachHangs = sqLife121.getALLKH();
                if (khachHangs.size() == 0) {
                    messageError = new MessageError();
                    error="Hiện chưa có Khách hàng, vui lòng thêm khách hàng để tiếp tục";
                    messageError.OpenDialog(contextm,error);
                    dialog1.dismiss();
                }
                AdapterSelect_KH adapterSelect_kh = new AdapterSelect_KH(khachHangs);
                ListView lvShowKH = view1.findViewById(R.id.lvShowSelectKH);
                lvShowKH.setAdapter(adapterSelect_kh);
                lvShowKH.setOnItemClickListener((parent, view2, position, id) -> {
                    tvMAKH.setText(khachHangs.get(position).getMaKH() + "");
                    tvTENKH.setText(khachHangs.get(position).getTenKH());
                    dialog1.dismiss();
                });

            });
            btnSave.setOnClickListener(v16 -> {
                if (messageError.checkFilledHoaDon(contextm, tvMAKH, tvMASP, tvNgayMua)) {
                    //                        TextView tvMAKH,tvTENKH,tvNgayMua,tvMASP,tvTENSP,tvTONGTIENHANG,tvTrangThai;
                    int MAKH1 = Integer.parseInt(tvMAKH.getText().toString());
                    String TENKH1 = tvMAKH.getText().toString();
                    String NGAYMUA1 = tvNgayMua.getText().toString();
                    String MASP1 = tvMASP.getText().toString();
                    String TENSP1 = tvTENSP.getText().toString();
                    int TONGTIEN1 = Integer.parseInt(tvTONGTIENHANG.getText().toString());
                    String TrangThai = tvTrangThai.getText().toString();
                    HoaDon hoaDon = new HoaDon(0, MAKH1, NGAYMUA1, MASP1, TENSP1, TONGTIEN1, TrangThai);
                    SQLife sqLife1 = new SQLife(v16.getContext());
                    sqLife1.AddHoaDon(hoaDon);
                    ArrayList<HoaDon> hoaDons1 = sqLife12.getALLHoaDon();
                    KhachHang khachHang = sqLife12.getOneKH(MAKH1 + "");
                    hoaDon = hoaDons1.get(hoaDons1.size() - 1);
                    if (TrangThai.equals("Đã Thanh Toán") == true) {
                        HoaDonCT hoaDonCT = new HoaDonCT(hoaDon.getMaHD(), hoaDon.getMaSP(), hoaDon.getTenSP(), hoaDon.getMaKH(), khachHang.getTenKH(), khachHang.getNumberPhone(), TONGTIEN1, TONGTIEN1, NGAYMUA1, getMANV());
                        sqLife1.AddHoaDonCT(hoaDonCT);
                        EditSanPham();
                    }
                    if (TrangThai.equals("Chưa Thanh Toán") == true) {
                        HoaDonCT hoaDonCT = new HoaDonCT(hoaDon.getMaHD(), hoaDon.getMaSP(), hoaDon.getTenSP(), hoaDon.getMaKH(), khachHang.getTenKH(), khachHang.getNumberPhone(), TONGTIEN1, 0, NGAYMUA1, getMANV());
                        sqLife1.AddHoaDonCT(hoaDonCT);
                        EditSanPham();
                    }
                    SQLife sqLife2 = new SQLife(v16.getContext());
                    HoaDon_Adapter hoaDon_adapter1 = new HoaDon_Adapter(sqLife2.getALLHoaDon());
                    listView.setAdapter(hoaDon_adapter1);
                    hoaDon_adapter1.notifyDataSetChanged();
                    dialog.dismiss();
                    error="Thêm Hóa Đơn thành công";
                    messageError.OpenDialog(contextm,error);
                }
            });
        });
        return view;
    }

    public void EditSanPham() {
        SQLife sqLife = new SQLife(contextm);
        for (int i = 0; i < sanPhams.size(); i++) {
            sqLife.UpdateSP(sanPhams.get(i));
        }
    }

    private void findIdTextview(View viewShow) {
        tvMAKH = viewShow.findViewById(R.id.tvHD_MAKH_Add);
        tvTENKH = viewShow.findViewById(R.id.tvHD_TENKH_Add);
        tvNgayMua = viewShow.findViewById(R.id.tvHD_NGAYMUA_Add);
        tvMASP = viewShow.findViewById(R.id.tvHD_MASP_Add);
        tvTENSP = viewShow.findViewById(R.id.tvHD_TENSP_Add);
        tvTONGTIENHANG = viewShow.findViewById(R.id.tvHD_TONGTIEN_Add);
        tvTrangThai = viewShow.findViewById(R.id.tvHD_TRANGTHAI_Add);
    }

    private void findIdButton(View viewShow) {
        btnSelectKH = viewShow.findViewById(R.id.btnHD_SelectKH_Add);
        btnSelectSP = viewShow.findViewById(R.id.btnHD_SelectSP_Add);
        btnSelectNgayMua = viewShow.findViewById(R.id.btnHD_SelectNGAYMUA_Add);
        btnTrue = viewShow.findViewById(R.id.btnHD_True_Add);
        btnFalse = viewShow.findViewById(R.id.btnHD_False_Add);
        btnSave = viewShow.findViewById(R.id.btnHD_Save);
    }

    private void onclickSelectSP(View v14,ViewGroup container){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(v14.getContext());
        View view1 = LayoutInflater.from(v14.getContext()).inflate(R.layout.select_table_sp, container, false);
        builder1.setView(view1);
        Dialog dialog1 = builder1.create();
        dialog1.show();
        if (sanPhams.size() == 0) {
            messageError = new MessageError();
            error="Hiện chưa có Sản Phẩm nào, vui lòng thêm sản phẩm để tiếp tục";
            messageError.OpenDialog(contextm,error);
            dialog1.dismiss();
        }
        AdapterSelect_SP adapterSelect_sp = new AdapterSelect_SP(sanPhams);
        ListView lvShowSP = view1.findViewById(R.id.lvShowSelectSP);
        EditText editText = view1.findViewById(R.id.edSL_Select);

        //chỉ cho nhập số
        messageError.setInputTypeNumber(editText);

        lvShowSP.setAdapter(adapterSelect_sp);
        lvShowSP.setOnItemClickListener((parent, view2, position, id) -> {
            try {
                int SoLuongNhap = Integer.parseInt(editText.getText().toString());
                if (SoLuongNhap < 0) {
                    error = "Số lượng bạn nhập phải >= 0";
                    messageError.OpenDialog(contextm, error);
                    return;
                }
                if (SoLuongNhap > (sanPhams.get(position).getSOLUONGNHAP())) {
                    error = "Số lượng bạn nhập lớn hơn số lượng có sẵn";
                    messageError.OpenDialog(contextm, error);
                    return;
                }


                int MASP_ = sanPhams.get(position).getMASP();
                String MaSPs = tvMASP.getText().toString();
                MaSPs += " - " + MASP_;
                tvMASP.setText(MaSPs);

                String TENSP_ = sanPhams.get(position).getNAMESP() + "(" + SoLuongNhap + ")";
                String TENSPs = tvTENSP.getText().toString();
                TENSPs += " - " + TENSP_;
                tvTENSP.setText(TENSPs);

                int TongTien = SoLuongNhap * (sanPhams.get(position).getGIANHAP());

                int TongTienTienHang_ = Integer.parseInt(tvTONGTIENHANG.getText().toString());
                TongTienTienHang_ += TongTien;
                tvTONGTIENHANG.setText(TongTienTienHang_ + "");
                sanPhams.get(position).setSOLUONGNHAP((sanPhams.get(position).getSOLUONGNHAP() - SoLuongNhap));
                sanPhams.get(position).setSOLUONGDABAN(SoLuongNhap);

                dialog1.dismiss();
            } catch (Exception e) {
                error = "Bạn hãy nhập số lượng trước khi chọn sản phẩm";
                messageError.OpenDialog(view2.getContext(), error);
            }
        });
    }

}
