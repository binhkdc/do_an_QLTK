package t3h.android.do_an_qlbh.FragmentQL;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import t3h.android.do_an_qlbh.AdapteData.NhanVien_Adapter;
import t3h.android.do_an_qlbh.Database.SQLife;
import t3h.android.do_an_qlbh.Entity.NhanVien;
import t3h.android.do_an_qlbh.MessageError;
import t3h.android.do_an_qlbh.R;

public class Fragment_QLTK extends Fragment {
    MessageError messageError;

    public int getMANV() {
        return MANV;
    }

    int MANV;

    ArrayList<NhanVien> nhanViens;

    String TENNV, GioiTinh, VaiTro, DiaChi, NgaySinh, UserName, PassWord, error;

    EditText edTenNV_show, edDiaChiNV_show, edNumberPhoneNV_show;

    TextView txtNgaySinh, txtGioiTinh;

    int  IMGNV, NumberPhone;

    Context context;

    Button btnSua, btnXoa, btnHuy, btnChonNgay, btnNam, btnNu;

    public Fragment_QLTK(int maNV) {
        this.MANV=maNV;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.layout_fragment_qltk, container, false);
        context = container.getContext();
        messageError = new MessageError();
        findIdShowNv(view);
        getDataNhanVien(getMANV());
        setDataShowNV();
        btnChonNgay.setOnClickListener(v1 -> {
            messageError.DialogDatePicker(v1, txtNgaySinh);
        });
        btnNam.setOnClickListener(v16 -> txtGioiTinh.setText("Nam"));
        btnNu.setOnClickListener(v15 -> txtGioiTinh.setText("Nữ"));
        btnSua.setOnClickListener(v13 -> {
            updateNV(getMANV(), v13);
        });
        return view;
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

    private void getDataNhanVien(int manv) {
        SQLife sqLife = new SQLife(context);
        NhanVien nhanVien = sqLife.findNhanVien(manv);;
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

    private void setDataShowNV() {
        edTenNV_show.setText(TENNV);
        edDiaChiNV_show.setText(DiaChi);
        edNumberPhoneNV_show.setText(NumberPhone + "");
        txtGioiTinh.setText(GioiTinh);
        txtNgaySinh.setText(NgaySinh);
    }

    private void updateNV(int manv, View v13) {
        getDataNhanVien(manv);
        if (messageError.checkFilledNhanVien(context, edTenNV_show, txtNgaySinh, edNumberPhoneNV_show, edDiaChiNV_show)) {
            int MANV1 = manv;
            String TENNV1 = edTenNV_show.getText().toString();
            int NumberPhone1 = Integer.parseInt(edNumberPhoneNV_show.getText().toString());
            String DiaChi1 = edDiaChiNV_show.getText().toString();
            String NgaySinh1 = txtNgaySinh.getText().toString();
            String GioiTinh1 = txtGioiTinh.getText().toString();
            SQLife sqLife = new SQLife(v13.getContext());
            NhanVien nhanVien1 = new NhanVien(MANV1, UserName, PassWord, TENNV1, NumberPhone1, DiaChi1, NgaySinh1, GioiTinh1, VaiTro, IMGNV);
            sqLife.UpdateNV(nhanVien1);

            error = "Sửa thành viên thành công";
            messageError.OpenDialog(context, error);
        }
    }
}
