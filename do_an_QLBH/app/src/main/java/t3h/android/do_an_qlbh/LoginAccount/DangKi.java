package t3h.android.do_an_qlbh.LoginAccount;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import t3h.android.do_an_qlbh.Entity.NhanVien;
import t3h.android.do_an_qlbh.Database.SQLife;
import t3h.android.do_an_qlbh.MessageError;
import t3h.android.do_an_qlbh.R;

import java.util.ArrayList;

public class DangKi extends AppCompatActivity {

    String error = "";
    private MessageError messageError;

    EditText edTaiKhoan, edMatKhau, edMatKhau2;
    Button btnNext, btnPre;

    Boolean checkName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ki);
        findId();
        messageError = new MessageError();
        btnNext.setOnClickListener(view -> {
            if (messageError.checkFillDangKi(DangKi.this,edTaiKhoan,edMatKhau,edMatKhau2)) {
                register();
            }
        });

        btnPre.setOnClickListener(v -> {
            Intent intent = new Intent(this, DangNhap.class);
            startActivity(intent);
        });
    }

    private void findId() {
        edTaiKhoan = findViewById(R.id.edAddTaiKhoan);
        edTaiKhoan.setInputType(InputType.TYPE_CLASS_TEXT);
        edMatKhau = findViewById(R.id.edAddMatKhau);
        edMatKhau2 = findViewById(R.id.edNhapLaiMK);
        btnNext = findViewById(R.id.btnNext_DK);
        btnPre = findViewById(R.id.btnPre_DK);
    }



    private void register(){
        String TaiKhoan = edTaiKhoan.getText().toString().trim();
        String MatKhau = edMatKhau.getText().toString().trim();
        SQLife sqLife = new SQLife(getApplicationContext());
        ArrayList<NhanVien> nhanViens = new ArrayList<>();
        nhanViens = sqLife.getALLNV();
        for (int i = 0; i < nhanViens.size(); i++) {
            String TaiKhoan2 = nhanViens.get(i).getUserName();
            checkName = TaiKhoan.toString().equals(TaiKhoan2.toString());
            if (checkName) {
                error= "Tài Khoản Đã Tồn Tại";
                messageError.OpenDialog(DangKi.this,error);
                return;
            }
        }
        Intent intent = new Intent(DangKi.this, StepDangKi.class);
        intent.putExtra("TaiKhoan", TaiKhoan);
        intent.putExtra("MatKhau", MatKhau);
        startActivity(intent);
    }
}