package t3h.android.do_an_qlbh.Entity;

public class HoaDonCT {
        int MAHDCT;
        String MASP;
        String NAMESP;
        int MAKH;
        String TENKH;
        int NUMBERPHONE;
        int GIATIENHANG;
        int SOTIENTHANHTOAN;
        String NGAYXUAT;
        int MANV;

    public HoaDonCT(int MAHDCT, String MASP, String NAMESP, int MAKH, String TENKH, int NUMBERPHONE, int GIATIENHANG, int SOTIENTHANHTOAN, String NGAYXUAT, int MANV) {
        this.MAHDCT = MAHDCT;
        this.MASP = MASP;
        this.NAMESP = NAMESP;
        this.MAKH = MAKH;
        this.TENKH = TENKH;
        this.NUMBERPHONE = NUMBERPHONE;
        this.GIATIENHANG = GIATIENHANG;
        this.SOTIENTHANHTOAN = SOTIENTHANHTOAN;
        this.NGAYXUAT = NGAYXUAT;
        this.MANV = MANV;
    }

    public HoaDonCT() {
    }

    public int getMAHDCT() {
        return MAHDCT;
    }

    public void setMAHDCT(int MAHDCT) {
        this.MAHDCT = MAHDCT;
    }

    public String getMASP() {
        return MASP;
    }

    public void setMASP(String MASP) {
        this.MASP = MASP;
    }

    public String getNAMESP() {
        return NAMESP;
    }

    public void setNAMESP(String NAMESP) {
        this.NAMESP = NAMESP;
    }

    public int getMAKH() {
        return MAKH;
    }

    public void setMAKH(int MAKH) {
        this.MAKH = MAKH;
    }

    public String getTENKH() {
        return TENKH;
    }

    public void setTENKH(String TENKH) {
        this.TENKH = TENKH;
    }

    public int getNUMBERPHONE() {
        return NUMBERPHONE;
    }

    public void setNUMBERPHONE(int NUMBERPHONE) {
        this.NUMBERPHONE = NUMBERPHONE;
    }

    public int getGIATIENHANG() {
        return GIATIENHANG;
    }

    public void setGIATIENHANG(int GIATIENHANG) {
        this.GIATIENHANG = GIATIENHANG;
    }

    public int getSOTIENTHANHTOAN() {
        return SOTIENTHANHTOAN;
    }

    public void setSOTIENTHANHTOAN(int SOTIENTHANHTOAN) {
        this.SOTIENTHANHTOAN = SOTIENTHANHTOAN;
    }

    public String getNGAYXUAT() {
        return NGAYXUAT;
    }

    public void setNGAYXUAT(String NGAYXUAT) {
        this.NGAYXUAT = NGAYXUAT;
    }

    public int getMANV() {
        return MANV;
    }

    public void setMANV(int MANV) {
        this.MANV = MANV;
    }
}
