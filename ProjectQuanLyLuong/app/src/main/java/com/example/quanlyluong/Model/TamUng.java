    package com.example.quanlyluong.Model;

    public class TamUng {
        String soPhieu, ngayUng, soTien, maNhanVien;

        public String getSoPhieu() {
            return soPhieu;
        }

        public void setSoPhieu(String soPhieu) {
            this.soPhieu = soPhieu;
        }

        public String getNgayUng() {
            return ngayUng;
        }

        public void setNgayUng(String ngayUng) {
            this.ngayUng = ngayUng;
        }

        public String getSoTien() {
            return soTien;
        }

        public void setSoTien(String soTien) {
            this.soTien = soTien;
        }

        public String getMaNhanVien() {
            return maNhanVien;
        }

        public void setMaNhanVien(String maNhanVien) {
            this.maNhanVien = maNhanVien;
        }

        @Override
        public String toString() {
            return "TamUng{" +
                    "soPhieu='" + soPhieu + '\'' +
                    ", ngayUng='" + ngayUng + '\'' +
                    ", soTien='" + soTien + '\'' +
                    ", maNhanVien='" + maNhanVien + '\'' +
                    '}';
        }
    }
