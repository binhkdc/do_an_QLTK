package t3h.android.do_an_qlbh.AdapteData;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;

import t3h.android.do_an_qlbh.Entity.HoaDon;
import t3h.android.do_an_qlbh.Entity.LoaiSP;
import t3h.android.do_an_qlbh.Entity.SanPham;
import t3h.android.do_an_qlbh.Database.SQLife;
import t3h.android.do_an_qlbh.MessageError;
import t3h.android.do_an_qlbh.R;
import t3h.android.do_an_qlbh.SelectTable.AdapterSelect_LSP;

import java.util.ArrayList;

public class SanPham_Adapter extends BaseAdapter {
    ArrayList<SanPham> sanPhams;

    HoaDon hoaDon;

    MessageError messageError;
    String error;
    Context contextSP;
    TextView txtRowMaSP, txtRowTenSP, txtRowSLSP, txtRowTrangThai;

    TextView tvMaSP_show, tvNgayNhap, tvMaLoai, tvSLB;
    EditText edTenSP_show, edGiaNhap_show, edSLN_show;
    Button btnChonNgay, btnSuaMaLoai, btnSua, btnXoa, btnHuyShow;

    public SanPham_Adapter(ArrayList<SanPham> sanPhams) {
        this.sanPhams = sanPhams;
        notifyDataSetChanged();
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_sanpham, parent, false);
        messageError = new MessageError();
        contextSP = parent.getContext();
        findViewById(view);
        SanPham sanPham = sanPhams.get(position);
        int MASP = sanPham.getMASP();
        String TENSP = sanPham.getNAMESP();
        int SLSP_ = sanPham.getSOLUONGNHAP();
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
        LinearLayout linearLayout = view.findViewById(R.id.linear_SanPham);
        linearLayout.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(parent.getContext());
            View showSP = LayoutInflater.from(parent.getContext()).inflate(R.layout.show_sp, parent, false);
            builder.setView(showSP);
            Dialog dialog = builder.create();
            dialog.show();
            SanPham sanPham1 = sanPhams.get(position);

            findTextViewById(showSP);
            findButtonById(showSP);

            tvMaSP_show.setText(sanPham1.getMASP() + "");
            edTenSP_show.setText(sanPham1.getNAMESP());
            edGiaNhap_show.setText("" + sanPham1.getGIANHAP());
            tvNgayNhap.setText("" + sanPham1.getNGAYNHAP());
            edSLN_show.setText("" + sanPham1.getSOLUONGNHAP());
            tvSLB.setText("" + sanPham1.getSOLUONGDABAN());
            tvMaLoai.setText("" + sanPham1.getMALOAISP());

            btnChonNgay.setOnClickListener(v14 -> {
                messageError.DialogDatePicker(v14, tvNgayNhap);
            });

            btnSuaMaLoai.setOnClickListener(v13 -> {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(v13.getContext());
                View view1 = LayoutInflater.from(v13.getContext()).inflate(R.layout.select_table_maloai, parent, false);
                builder1.setView(view1);
                Dialog dialog1 = builder1.create();
                dialog1.show();
                SQLife sqLife = new SQLife(view1.getContext());
                ArrayList<LoaiSP> loaiSPS = sqLife.getALL_LSP();
                AdapterSelect_LSP adapterSelect_lsp = new AdapterSelect_LSP(loaiSPS);
                ListView lvShowLoaiSP = view1.findViewById(R.id.lvShowSelectLSP);
                lvShowLoaiSP.setAdapter(adapterSelect_lsp);
                lvShowLoaiSP.setOnItemClickListener((parent1, view2, position1, id) -> {
                    tvMaLoai.setText(loaiSPS.get(position1).getMaLoai() + "");
                    dialog1.dismiss();
                });
            });

            btnSua.setOnClickListener(v12 -> {
                if (messageError.checkFilledSanPham(contextSP, edTenSP_show, edGiaNhap_show, tvNgayNhap, edSLN_show, tvMaLoai)) {
                    try {
                        int MASP1 = Integer.parseInt(tvMaSP_show.getText().toString());
                        String TENSP1 = edTenSP_show.getText().toString();
                        int GIANHAP1 = Integer.parseInt(edGiaNhap_show.getText().toString());
                        String NGAYNHAP1 = tvNgayNhap.getText().toString();
                        int SLN1 = Integer.parseInt(edSLN_show.getText().toString());
                        int MALOAI1 = Integer.parseInt(tvMaLoai.getText().toString());
                        int SLB1 = Integer.parseInt(tvSLB.getText().toString());

                        SanPham sanPham11 = new SanPham(MASP1, TENSP1, GIANHAP1, NGAYNHAP1, SLN1, SLB1, MALOAI1);
                        SQLife sqLife = new SQLife(v12.getContext());
                        sqLife.UpdateSP(sanPham11);
                        sanPhams.clear();
                        sanPhams.addAll(sqLife.getALLSP());
                        notifyDataSetChanged();
                        error = "Sửa sản phẩm thành công";
                        messageError.OpenDialog(contextSP, error);
                        dialog.dismiss();
                    }catch (Exception e){
                        error = "số bạn nhập vượt quá giới hạn cho phép! tối đa 9 số";
                        messageError.OpenDialog(contextSP, error);
                    }

                }
            });
            btnXoa.setOnClickListener(v1 -> {
                String MASP1 = tvMaSP_show.getText().toString().trim();
                SQLife sqLife = new SQLife(v1.getContext());
                hoaDon = sqLife.find_maSP_HoaDon(MASP1);
                String oldMASP = hoaDon.getMaSP();
                String newMASP = "- " + MASP1;
                if (oldMASP == null) {
                    oldMASP = "binhkdc";
                }
                if (oldMASP.trim().equals(newMASP.trim())) {
                    error = "Bạn không thể xóa Sản Phẩm này vì có một hóa đơn đang sử dụng sản phẩm này";
                    messageError.OpenDialog(contextSP, error);
                    dialog.dismiss();
                } else {
                    sqLife.DeleteSP(MASP1 + "");
                    sanPhams.clear();
                    sanPhams.addAll(sqLife.getALLSP());
                    notifyDataSetChanged();
                    error = "Xóa sản phẩm thành công";
                    messageError.OpenDialog(contextSP, error);
                    dialog.dismiss();
                }

            });
            btnHuyShow.setOnClickListener(v15 -> dialog.dismiss());

        });
        return view;
    }

    private void findViewById(View view) {
        txtRowMaSP = view.findViewById(R.id.txtRowMaSP);
        txtRowTenSP = view.findViewById(R.id.txtRowNameSP);
        txtRowSLSP = view.findViewById(R.id.txtRowSoluongSP);
        txtRowTrangThai = view.findViewById(R.id.txtRowTrangThaiSP);
    }

    private void findTextViewById(View showSP) {
        tvMaSP_show = showSP.findViewById(R.id.tvMaSP_Show);
        edTenSP_show = showSP.findViewById(R.id.ed_TenSP_Show);
        edGiaNhap_show = showSP.findViewById(R.id.ed_GiaNhap_Show);
        messageError.setInputTypeNumber(edGiaNhap_show);
        tvNgayNhap = showSP.findViewById(R.id.tvNgayNhap);
        edSLN_show = showSP.findViewById(R.id.ed_SLN_Show);
        messageError.setInputTypeNumber(edSLN_show);
        tvSLB = showSP.findViewById(R.id.tvSLB_show);
        tvMaLoai = showSP.findViewById(R.id.tvMaLoai);
    }

    private void findButtonById(View showSP) {
        btnChonNgay = showSP.findViewById(R.id.btnChonNN_SP_show);
        btnSuaMaLoai = showSP.findViewById(R.id.btnSuaLSP_SP_show);
        btnSua = showSP.findViewById(R.id.btnSuaSP);
        btnXoa = showSP.findViewById(R.id.btnXoaSP);
        btnHuyShow = showSP.findViewById(R.id.btnHuyShowSP);
    }

    @Override
    public int getCount() {
        return sanPhams.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

}
