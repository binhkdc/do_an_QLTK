package t3h.android.do_an_qlbh.LoginAccount;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import t3h.android.do_an_qlbh.Entity.NhanVien;
import t3h.android.do_an_qlbh.Database.SQLife;
import t3h.android.do_an_qlbh.MessageError;
import t3h.android.do_an_qlbh.R;

import java.util.ArrayList;
import java.util.Calendar;

public class StepDangKi extends AppCompatActivity {

    EditText edHoTen, edSDT, edDiaChi;
    TextView txtNgaySinh, txtGioiTinh;
    Button btnDangKi, btnChonNgay, btnNam, btnNu;
    Intent intent;
    String error, VaiTro;
    MessageError messageError;
    ArrayList<NhanVien> nhanViens;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_dang_ki);
        intent = getIntent();
        messageError = new MessageError();
        findId();
        //        Button Chọn Ngày Sinh <Start>
        btnChonNgay.setOnClickListener(v -> {
            messageError.DialogDatePicker(v,txtNgaySinh);
        });
//        Button Chọn Ngày Sinh <End>

//        Button Chọn Giới Tính Nam <Start>
        btnNam.setOnClickListener(view -> {
            txtGioiTinh.setText("Nam");
        });
//        Button Chọn Giới Tính Nam <End>

//        Button Chọn Giới Tính Nữ <Start>
        btnNu.setOnClickListener(view -> {
            txtGioiTinh.setText("Nữ");
        });
//        Button Chọn Giới Tính Nữ <End>

//        Button Đăng Kí <Start>
        btnDangKi.setOnClickListener(view -> {
            if (messageError.checkFillStepDangKi(StepDangKi.this,edHoTen,txtNgaySinh,txtGioiTinh,edDiaChi,edSDT)) {
                register();
            }
        });
//        Button Đăng Kí <End>


    }

    private void findId() {
        edHoTen = findViewById(R.id.edHT_NV_Add);
        edDiaChi = findViewById(R.id.edDC_NV_Add);

        edSDT = findViewById(R.id.edSDT_NV_Add);
        messageError.setInputTypeNumber(edSDT);

        txtNgaySinh = findViewById(R.id.txtNS_NV_Add);
        txtGioiTinh = findViewById(R.id.txtGT_NV_Add);
        btnDangKi = findViewById(R.id.btnDangKiAdd);
        btnChonNgay = findViewById(R.id.btnChonNS_NV_Add);
        btnNam = findViewById(R.id.btnGT_NV_Nam_Add);
        btnNu = findViewById(R.id.btnGT_NV_Nu_Add);
    }



    private void register() {
        try {
            String HOTEN = edHoTen.getText().toString();
            String DIACHI = edDiaChi.getText().toString();
            int PHONE = Integer.parseInt(edSDT.getText().toString());
            String TaiKhoan = intent.getStringExtra("TaiKhoan");
            String MatKhau = intent.getStringExtra("MatKhau");
            String NgaySinh = txtNgaySinh.getText().toString();
            String GioiTinh = txtGioiTinh.getText().toString();
            int IMAGENV = R.drawable.img_inf4;
            SQLife sqLife = new SQLife(getApplicationContext());
            nhanViens = sqLife.getALLNV();

            // chỉ có 1 quản trị
            if (nhanViens.size() == 0) {
                VaiTro = "Quản Trị";
                NhanVien nhanVien = new NhanVien(0, TaiKhoan, MatKhau, HOTEN, PHONE, DIACHI, NgaySinh, GioiTinh, VaiTro, IMAGENV);
                sqLife.AddNhanVien(nhanVien);
                intent = new Intent(StepDangKi.this, DangNhap.class);
                intent.putExtra("success", "Đăng Kí thành Công");
                intent.putExtra("username", TaiKhoan);
                intent.putExtra("pass", MatKhau);
                startActivity(intent);
            }

            // có quản trị rồi thì đăng kí nhân viên
            if (nhanViens.size() > 0) {
                VaiTro = "Nhân Viên";
                NhanVien nhanVien = new NhanVien(0, TaiKhoan, MatKhau, HOTEN, PHONE, DIACHI, NgaySinh, GioiTinh, VaiTro, IMAGENV);
                sqLife.AddNhanVien(nhanVien);
                intent = new Intent(StepDangKi.this, DangNhap.class);
                intent.putExtra("username", TaiKhoan);
                intent.putExtra("pass", MatKhau);
                intent.putExtra("success", "Đăng Kí thành Công");
                startActivity(intent);
            }
        } catch (Exception e) {
            error = "Bạn nhập quá kí tự cho phép, số điện thoại tối đa 9 số";
            messageError.OpenDialog(StepDangKi.this, error);
        }

    }
}