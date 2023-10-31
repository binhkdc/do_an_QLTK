package t3h.android.do_an_qlbh;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import t3h.android.do_an_qlbh.LoginAccount.DangNhap;

public class HelloWorld extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("binhkdc","oncreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("binhkdc","onstart");
        setContentView(R.layout.activity_hello_word);
        new Handler().postDelayed(() -> startActivity(new Intent(getApplicationContext(), DangNhap.class)),2500);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("binhkdc","onpause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("binhkdc","onresume");

    }
}