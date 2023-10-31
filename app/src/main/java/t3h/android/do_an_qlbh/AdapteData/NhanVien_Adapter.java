package t3h.android.do_an_qlbh.AdapteData;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import t3h.android.do_an_qlbh.Entity.NhanVien;
import t3h.android.do_an_qlbh.Database.SQLife;
import t3h.android.do_an_qlbh.MessageError;
import t3h.android.do_an_qlbh.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class NhanVien_Adapter extends BaseAdapter {

    MessageError messageError;
    ArrayList<NhanVien> nhanViens;

    TextView txtRowMaNV, txtRowTenNV, txtRowGioiTinh, txtRowVaiTro;

    CircleImageView circleImageView;

    TextView txtNgaySinh, txtGioiTinh;
    EditText edTenNV_show, edDiaChiNV_show, edNumberPhoneNV_show;
    Button btnSua, btnXoa, btnHuy, btnChonNgay, btnNam, btnNu;

    String TENNV, GioiTinh, VaiTro, DiaChi, NgaySinh, UserName, PassWord, error;

    int MANV, IMGNV, NumberPhone;

    Context contextNV;


    public NhanVien_Adapter(ArrayList<NhanVien> nhanViens) {
        this.nhanViens = nhanViens;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        messageError = new MessageError();
        contextNV = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_nv, parent, false);
        findViewById(view);
        getDataNhanVien(position);
        setDataRowNv();
        LinearLayout linearLayout = view.findViewById(R.id.linear_rownv);
        linearLayout.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(parent.getContext());
            View showNV = LayoutInflater.from(parent.getContext()).inflate(R.layout.show_nv, parent, false);
            builder.setView(showNV);
            Dialog dialog = builder.create();
            dialog.show();
            findIdShowNv(showNV);
            getDataNhanVien(position);
            setDataShowNV();
            btnChonNgay.setOnClickListener(v1 -> {
                messageError.DialogDatePicker(v1, txtNgaySinh);
            });
            btnNam.setOnClickListener(v16 -> txtGioiTinh.setText("Nam"));
            btnNu.setOnClickListener(v15 -> txtGioiTinh.setText("Nữ"));
            btnHuy.setOnClickListener(v14 -> dialog.dismiss());
            btnSua.setOnClickListener(v13 -> {
                updateNV(position, v13, dialog);
            });
            btnXoa.setOnClickListener(v12 -> {
                deleteNV(position, v12, dialog);
            });
        });
        return view;
    }

    private void findViewById(View view) {
        txtRowMaNV = view.findViewById(R.id.txtRowMaNV);
        txtRowTenNV = view.findViewById(R.id.txtRowTenNV);
        txtRowGioiTinh = view.findViewById(R.id.txtRowGioiTinhNV);
        txtRowVaiTro = view.findViewById(R.id.txtRowVaiTroNV);
        circleImageView = view.findViewById(R.id.RowImageNV);
    }

    private void setDataRowNv() {
        txtRowMaNV.setText("" + MANV + "");
        txtRowTenNV.setText("" + TENNV);
        txtRowGioiTinh.setText("" + GioiTinh);
        txtRowVaiTro.setText("" + VaiTro);
        circleImageView.setImageResource(IMGNV);
    }

    private void setDataShowNV() {
        edTenNV_show.setText(TENNV);
        edDiaChiNV_show.setText(DiaChi);
        edNumberPhoneNV_show.setText(NumberPhone + "");
        txtGioiTinh.setText(GioiTinh);
        txtNgaySinh.setText(NgaySinh);
    }

    private void getDataNhanVien(int position) {
        NhanVien nhanVien = nhanViens.get(position);
        MANV = nhanVien.getMaNV();
        TENNV = nhanVien.getTenNV();
        GioiTinh = nhanVien.getGioiTinh();
        VaiTro = nhanVien.getVaiTro();
        IMGNV = nhanVien.getImageNV();
        NumberPhone = nhanVien.getNumberPhone();
        DiaChi = nhanVien.getDiaChi();
        NgaySinh = nhanVien.getNgaySinh();
        UserName = nhanVien.getUserName();
        PassWord = nhanVien.getPassWord();
    }

    private void findIdShowNv(View showNV) {
        txtNgaySinh = showNV.findViewById(R.id.txtNS_NV_Show);
        txtGioiTinh = showNV.findViewById(R.id.txtGT_NV_Show);
        edTenNV_show = showNV.findViewById(R.id.edHT_NV_Show);
        edDiaChiNV_show = showNV.findViewById(R.id.edDC_NV_Show);
        edNumberPhoneNV_show = showNV.findViewById(R.id.edSDT_NV_Show);
        messageError.setInputTypeNumber(edNumberPhoneNV_show);
        btnSua = showNV.findViewById(R.id.btnSuaNV);
        btnXoa = showNV.findViewById(R.id.btnXoaNV);
        btnHuy = showNV.findViewById(R.id.btnHuyShowNV);
        btnChonNgay = showNV.findViewById(R.id.btnChonNS_NV_Show);
        btnNam = showNV.findViewById(R.id.btnGT_NV_Nam_Show);
        btnNu = showNV.findViewById(R.id.btnGT_NV_Nu_Show);
    }

    private void deleteNV(int position, View v12, Dialog dialog) {
        getDataNhanVien(position);
        int MANV1 = MANV;
        if (checkVaiTro(VaiTro)) {
            error = "Bạn không thể xóa chính mình";
            messageError.OpenDialog(contextNV, error);
            dialog.dismiss();
        } else {
            SQLife sqLife = new SQLife(v12.getContext());
            sqLife.DeleteNV(MANV1 + "");
            nhanViens.clear();
            nhanViens.addAll(sqLife.getALLNV());
            notifyDataSetChanged();
            error = "Xóa thành công nhân viên";
            messageError.OpenDialog(contextNV, error);
            dialog.dismiss();
        }
    }

    private void updateNV(int position, View v13, Dialog dialog) {
        getDataNhanVien(position);
        if (messageError.checkFilledNhanVien(contextNV, edTenNV_show, txtNgaySinh, edNumberPhoneNV_show, edDiaChiNV_show)) {
            int MANV1 = MANV;
            String TENNV1 = edTenNV_show.getText().toString();
            int NumberPhone1 = Integer.parseInt(edNumberPhoneNV_show.getText().toString());
            String DiaChi1 = edDiaChiNV_show.getText().toString();
            String NgaySinh1 = txtNgaySinh.getText().toString();
            String GioiTinh1 = txtGioiTinh.getText().toString();
            SQLife sqLife = new SQLife(v13.getContext());
            NhanVien nhanVien1 = new NhanVien(MANV1, UserName, PassWord, TENNV1, NumberPhone1, DiaChi1, NgaySinh1, GioiTinh1, VaiTro, IMGNV);
            sqLife.UpdateNV(nhanVien1);
            nhanViens.clear();
            nhanViens.addAll(sqLife.getALLNV());
            notifyDataSetChanged();
            dialog.dismiss();
            error = "Sửa thành viên thành công";
            messageError.OpenDialog(contextNV, error);
        }
    }


    @Override
    public int getCount() {
        return nhanViens.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    public boolean checkVaiTro(String vaitro) {
        if (vaitro.equals("Quản Trị")) {
            return true;
        }
        return false;
    }

}
