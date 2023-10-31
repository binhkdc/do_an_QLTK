package t3h.android.do_an_qlbh.LoginAccount;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import t3h.android.do_an_qlbh.Entity.NhanVien;
import t3h.android.do_an_qlbh.Database.SQLife;
import t3h.android.do_an_qlbh.MainActivity;
import t3h.android.do_an_qlbh.MessageError;
import t3h.android.do_an_qlbh.R;

import java.io.Serializable;
import java.util.ArrayList;

public class DangNhap extends AppCompatActivity {
    Button btnDangKi, btnDangNhap;
    EditText edTaiKhoan, edMatKhau;
    CheckBox chk_remember;
    String error = "";
    private MessageError messageError;
    Intent intent;

    String TaiKhoan, MatKhau;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        findId();
        messageError = new MessageError();

        SharedPreferences preferences = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        edTaiKhoan.setText(preferences.getString("USERNAME", ""));
        edMatKhau.setText(preferences.getString("PASSWORD", ""));
        chk_remember.setChecked(preferences.getBoolean("REMEMBER", false));

        btnDangNhap.setOnClickListener(v -> {
            if (messageError.checkFillDangNhap(DangNhap.this,edTaiKhoan,edMatKhau)) {
                login();
            }
        });

        btnDangKi.setOnClickListener(v -> {
            Intent intent = new Intent(DangNhap.this, DangKi.class);
            startActivity(intent);
        });
    }

    public void rememberUser(String user, String pass, boolean status) {
        SharedPreferences pref = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        if (!status) {
            editor.clear();
        } else {
            editor.putString("USERNAME", user);
            editor.putString("PASSWORD", pass);
            editor.putBoolean("REMEMBER", status);
        }
        editor.commit();
    }

    public void findId() {
        btnDangNhap = findViewById(R.id.btnDangNhap);
        btnDangKi = findViewById(R.id.btnDangKi);
        edTaiKhoan = findViewById(R.id.edTaiKhoan);
        edMatKhau = findViewById(R.id.edMatKhau);
        chk_remember = findViewById(R.id.chk_remember);
    }

    private void login() {

        TaiKhoan = edTaiKhoan.getText().toString();
        MatKhau = edMatKhau.getText().toString();

        SQLife sqLife = new SQLife(getApplicationContext());
        ArrayList<NhanVien> nhanViens = new ArrayList<>();
        nhanViens = sqLife.getALLNV();
        boolean check = false;
        for (int i = 0; i < nhanViens.size(); i++) {
            String TaiKhoan2 = nhanViens.get(i).getUserName();
            String MatKhau2 = nhanViens.get(i).getPassWord();

            if (TaiKhoan.toString().equals(TaiKhoan2.toString()) == true) {
                if (MatKhau.toString().equals(MatKhau2.toString()) == true) {
                    String VAITRO = nhanViens.get(i).getVaiTro();
                    NhanVien nhanVienput = nhanViens.get(i);
                    ViewGroup viewGroup = (ViewGroup) findViewById(android.R.id.content);
                    rememberUser(TaiKhoan2, MatKhau2, chk_remember.isChecked());
                    Intent intent = new Intent(DangNhap.this, MainActivity.class);
                    intent.putExtra("VAITRO", VAITRO);
                    intent.putExtra("infomation", (Serializable) nhanVienput);
                    finish();
                    startActivity(intent);
                    return;
                }
                if (MatKhau.toString().equals(MatKhau2.toString()) == false) {
                    error = "Mật Khẩu Chưa Chính Xác";
                    messageError.OpenDialog(DangNhap.this, error);
                    return;
                }
            }
            check = false;


        }
        if (check == false) {
            error = "Tài Khoản Không Tồn Tại";
            messageError.OpenDialog(DangNhap.this, error);
            return;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = getIntent();

        String msg = intent.getStringExtra("success");
        String username = intent.getStringExtra("username");
        String pass = intent.getStringExtra("pass");
        if (msg == null) {

        } else {
            messageError = new MessageError();
            messageError.OpenDialog(this, msg);
            edTaiKhoan.setText(username);
            edMatKhau.setText(pass);
            TaiKhoan = username;
            MatKhau = pass;
        }


    }
}

