package tdc.edu.vn.kiemtra.Model;

public class DonHang {
    String ma,ngay,soLuong,sanPham,khachHang;

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public String getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(String soLuong) {
        this.soLuong = soLuong;
    }

    public String getSanPham() {
        return sanPham;
    }

    public void setSanPham(String sanPham) {
        this.sanPham = sanPham;
    }

    public String getKhachHang() {
        return khachHang;
    }

    public void setKhachHang(String khachHang) {
        this.khachHang = khachHang;
    }

    @Override
    public String toString() {
        return "DonHang{" +
                "ma='" + ma + '\'' +
                ", ngay='" + ngay + '\'' +
                ", soLuong='" + soLuong + '\'' +
                ", sanPham='" + sanPham + '\'' +
                ", khachHang='" + khachHang + '\'' +
                '}';
    }
}
