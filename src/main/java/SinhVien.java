public class SinhVien {
    private String id;
    private String ten;
    private double diem;
    private String lop;
    private int tuoi;

    // Alt + Insert để tạo constructor, getters, setters, toString
    // 1. Constructor không tham số
    public SinhVien() {
    }

    // 2. Constructor đầy đủ tham số
    public SinhVien(String id, String ten, double diem, String lop, int tuoi) {
        this.id = id;
        this.ten = ten;
        this.diem = diem;
        this.lop = lop;
        this.tuoi = tuoi;
    }

    // 3. Getters
    public String getId() {
        return id;
    }

    public String getTen() {
        return ten;
    }

    public double getDiem() {
        return diem;
    }

    public String getLop() {
        return lop;
    }

    public int getTuoi() {
        return tuoi;
    }

    // 4. Setters
    public void setId(String id) {
        this.id = id;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public void setDiem(double diem) {
        this.diem = diem;
    }

    public void setLop(String lop) {
        this.lop = lop;
    }

    public void setTuoi(int tuoi) {
        this.tuoi = tuoi;
    }

    // 5. Override toString (để in ra thông tin dễ đọc hơn)
    @Override
    public String toString() {
        return "SinhVien{" +
                "id='" + id + '\'' +
                ", ten='" + ten + '\'' +
                ", diem=" + diem +
                ", lop='" + lop + '\'' +
                ", tuoi=" + tuoi +
                '}';
    }
}








