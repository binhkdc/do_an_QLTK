package t3h.android.do_an_qlbh;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import t3h.android.do_an_qlbh.Entity.NhanVien;
import t3h.android.do_an_qlbh.FragmentQL.Fragment_HoaDon;
import t3h.android.do_an_qlbh.FragmentQL.Fragment_KhachHang;
import t3h.android.do_an_qlbh.FragmentQL.Fragment_LoaiSP;
import t3h.android.do_an_qlbh.FragmentQL.Fragment_NhanVien;
import t3h.android.do_an_qlbh.FragmentQL.Fragment_QLTK;
import t3h.android.do_an_qlbh.FragmentQL.Fragment_SanPham;
import t3h.android.do_an_qlbh.FragmentQL.Fragment_ThongKe;
import t3h.android.do_an_qlbh.LoginAccount.DangNhap;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;


public class MainActivity extends AppCompatActivity {
    MaterialToolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    FragmentManager fragmentManager;
    Intent intent;

    @RequiresApi(api = Build.VERSION_CODES.S)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        intent = getIntent();
        checkVaitro();
        handleToolbar();
        drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {
                editNavHeader();
            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
        setNavigationView();
    }

    private void handleToolbar(){
        toolbar = findViewById(R.id.toolBar_);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.outline_menu_white_18);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("HOME");

        drawerLayout = findViewById(R.id.Draw_layout);
        navigationView = findViewById(R.id.Draw_nav);
    }

    private void editNavHeader() {
        TextView txtNameHeader, txtVaiTroHeader, txtDoanhThuHeader;
        txtNameHeader = findViewById(R.id.tvName_header);
        txtVaiTroHeader = findViewById(R.id.tvVaiTro_header);
        txtDoanhThuHeader = findViewById(R.id.tvDoanhThu_header);

        NhanVien nhanVien = (NhanVien) intent.getSerializableExtra("infomation");
        txtNameHeader.setText("Họ Tên: " + nhanVien.getTenNV());
        txtVaiTroHeader.setText("Vai Trò: " + nhanVien.getVaiTro());
        int DoanhThu = 0;
        txtDoanhThuHeader.setText("Doanh Thu: " + DoanhThu + " VNĐ");

    }

    public void DangXuat() {
        intent = new Intent(MainActivity.this, DangNhap.class);
        startActivity(intent);
    }

    private void checkVaitro() {
        String vaiTro = intent.getStringExtra("VAITRO");
        if (vaiTro.equals("Quản Trị") == true) {
            setContentView(R.layout.activity_main);
        }
        if (vaiTro.equals("Nhân Viên") == true) {
            setContentView(R.layout.activity_main2);
        }
    }

    private void setNavigationView() {
        // end edit Nav_header
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.fragment_main, new Fragment_SanPham()).commit();
        navigationView.setNavigationItemSelectedListener(item -> {
            NhanVien nhanVien = (NhanVien) intent.getSerializableExtra("infomation");
            switch (item.getItemId()) {
                case R.id.mnu_QLSanPham:
                    fragmentManager.beginTransaction().replace(R.id.fragment_main, new Fragment_SanPham()).commit();
                    getSupportActionBar().setTitle("Quản Lý Sản Phẩm");
                    break;
                case R.id.mnu_QLLoaiSanPham:
                    fragmentManager.beginTransaction().replace(R.id.fragment_main, new Fragment_LoaiSP()).commit();
                    getSupportActionBar().setTitle("Quản Lý Loại Sản Phẩm");
                    break;
                case R.id.mnu_QLNhanVien:
                    fragmentManager.beginTransaction().replace(R.id.fragment_main, new Fragment_NhanVien()).commit();
                    getSupportActionBar().setTitle("Quản Lý Nhân Viên");
                    break;
                case R.id.mnu_QLKhachHang:
                    fragmentManager.beginTransaction().replace(R.id.fragment_main, new Fragment_KhachHang()).commit();
                    getSupportActionBar().setTitle("Quản Lý Khách Hàng");
                    break;
                case R.id.mnu_QLHoaDon:
                    fragmentManager.beginTransaction().replace(R.id.fragment_main, new Fragment_HoaDon(nhanVien.getMaNV())).commit();
                    getSupportActionBar().setTitle("Quản Lý Hoá Đơn ");
                    break;
                case R.id.mnu_ThongKe:
                    fragmentManager.beginTransaction().replace(R.id.fragment_main, new Fragment_ThongKe()).commit();
                    getSupportActionBar().setTitle("Thống Kê");
                    break;
                    case R.id.mnu_QLTaiKhoanCaNhan:
                        int commit = fragmentManager.beginTransaction().replace(R.id.fragment_main, new Fragment_QLTK(nhanVien.getMaNV())).commit();
                        getSupportActionBar().setTitle("Thông Tin Tài Khoản");
                        break;
                case R.id.mnu_DangXuat:
                    DangXuat();
                    break;
            }
            drawerLayout.closeDrawer(navigationView);
            return false;
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            drawerLayout.openDrawer(navigationView);
        }
        return super.onOptionsItemSelected(item);
    }


}
