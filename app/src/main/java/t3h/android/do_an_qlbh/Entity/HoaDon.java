package t3h.android.do_an_qlbh.Entity;

public class HoaDon {
    int MaHD;
    int MaKH;
    String NgayMuaHang;
    String MaSP;
    String TenSP;
    int TongTienHang;
    String TrangThai;

    public HoaDon(int maHD, int maKH, String ngayMuaHang, String maSP, String tenSP, int tongTienHang, String trangThai) {
        MaHD = maHD;
        MaKH = maKH;
        NgayMuaHang = ngayMuaHang;
        MaSP = maSP;
        TenSP = tenSP;
        TongTienHang = tongTienHang;
        TrangThai = trangThai;
    }

    public HoaDon() {
    }

    public String getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(String trangThai) {
        TrangThai = trangThai;
    }

    public int getMaHD() {
        return MaHD;
    }

    public void setMaHD(int maHD) {
        MaHD = maHD;
    }

    public int getMaKH() {
        return MaKH;
    }

    public void setMaKH(int maKH) {
        MaKH = maKH;
    }

    public String getNgayMuaHang() {
        return NgayMuaHang;
    }

    public void setNgayMuaHang(String ngayMuaHang) {
        NgayMuaHang = ngayMuaHang;
    }

    public String getMaSP() {
        return MaSP;
    }

    public void setMaSP(String maSP) {
        MaSP = maSP;
    }

    public String getTenSP() {
        return TenSP;
    }

    public void setTenSP(String tenSP) {
        TenSP = tenSP;
    }

    public int getTongTienHang() {
        return TongTienHang;
    }

    public void setTongTienHang(int tongTienHang) {
        TongTienHang = tongTienHang;
    }
}
