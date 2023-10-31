package t3h.android.do_an_qlbh.Entity;

import java.io.Serializable;

public class NhanVien implements Serializable {
    int MaNV;
    String UserName;
    String PassWord;
    String TenNV;
    int NumberPhone;
    String DiaChi;
    String NgaySinh;
    String GioiTinh;
    String VaiTro;
    int ImageNV;

    public NhanVien(int maNV, String userName, String passWord, String tenNV, int numberPhone, String diaChi, String ngaySinh, String gioiTinh, String vaiTro, int imageNV) {
        MaNV = maNV;
        UserName = userName;
        PassWord = passWord;
        TenNV = tenNV;
        NumberPhone = numberPhone;
        DiaChi = diaChi;
        NgaySinh = ngaySinh;
        GioiTinh = gioiTinh;
        VaiTro = vaiTro;
        ImageNV = imageNV;
    }

    public String getGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        GioiTinh = gioiTinh;
    }

    public NhanVien() {
    }

    public int getMaNV() {
        return MaNV;
    }

    public void setMaNV(int maNV) {
        MaNV = maNV;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassWord() {
        return PassWord;
    }

    public void setPassWord(String passWord) {
        PassWord = passWord;
    }

    public String getTenNV() {
        return TenNV;
    }

    public void setTenNV(String tenNV) {
        TenNV = tenNV;
    }

    public int getNumberPhone() {
        return NumberPhone;
    }

    public void setNumberPhone(int numberPhone) {
        NumberPhone = numberPhone;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }

    public String getNgaySinh() {
        return NgaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        NgaySinh = ngaySinh;
    }

    public String getVaiTro() {
        return VaiTro;
    }

    public void setVaiTro(String vaiTro) {
        VaiTro = vaiTro;
    }

    public int getImageNV() {
        return ImageNV;
    }

    public void setImageNV(int imageNV) {
        ImageNV = imageNV;
    }
}
