import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SinhVienService {
    // Sử dụng List để lưu trữ dữ liệu, mô phỏng DB
    private List<SinhVien> danhSachSinhVien;

    public SinhVienService() {
        this.danhSachSinhVien = new ArrayList<>();
    }

    /**
     * Thêm sinh viên mới vào danh sách.
     * Áp dụng validation theo yêu cầu:
     * - Diem: [0 - 10]
     * - Tuoi: [18 - 30]
     * - Ten: Không rỗng, không chứa số/ký tự đặc biệt (chỉ chấp nhận chữ cái và khoảng trắng)
     * @param sv đối tượng SinhVien
     * @return true nếu thêm thành công, false nếu dữ liệu không hợp lệ.
     */
    public boolean themSinhVien(SinhVien sv) {

        if (sv == null) {
            return false;
        }

        // 1. Kiểm tra Điểm
        if (sv.getDiem() < 0 || sv.getDiem() > 10) {
            System.out.println("Lỗi: Điểm phải nằm trong khoảng [0 - 10].");
            return false;
        }

        // 2. Kiểm tra Tuổi
        if (sv.getTuoi() < 18 || sv.getTuoi() > 30) {
            System.out.println("Lỗi: Tuổi phải nằm trong khoảng [18 - 30].");
            return false;
        }

        // 3. Kiểm tra Tên
        if (sv.getTen() == null || sv.getTen().trim().isEmpty()) {
            System.out.println("Lỗi: Tên sinh viên không được rỗng.");
            return false;
        }
        // Biểu thức chính quy (Regex) kiểm tra tên: chỉ chấp nhận chữ cái (Unicode) và khoảng trắng
        if (!sv.getTen().matches("^[\\p{L} .'-]+$")) {
            System.out.println("Lỗi: Tên sinh viên không được chứa số hoặc ký tự đặc biệt.");
            return false;
        }

        // Thêm vào danh sách nếu tất cả điều kiện hợp lệ
        return danhSachSinhVien.add(sv);
    }

    /**
     * Tìm kiếm sinh viên theo lớp. (Chức năng Read)
     * @param lop Tên lớp cần tìm
     * @return List<SinhVien> danh sách sinh viên thuộc lớp đó
     */
    public List<SinhVien> timTheoLop(String lop) {
        if (lop == null || lop.trim().isEmpty()) {
            return new ArrayList<>();
        }
        return danhSachSinhVien.stream()
                .filter(sv -> lop.equalsIgnoreCase(sv.getLop()))
                .collect(Collectors.toList());
    }

    /**
     * Sửa thông tin (ví dụ: tên, điểm) của sinh viên theo ID. (Chức năng Update)
     * Áp dụng validation tương tự như hàm thêm (chỉ cho điểm và tên).
     * @param id ID của sinh viên cần sửa
     * @param newTen Tên mới
     * @param newDiem Điểm mới
     * @return true nếu sửa thành công, false nếu không tìm thấy ID hoặc dữ liệu mới không hợp lệ
     */
    public boolean suaThongTinSinhVien(String id, String newTen, double newDiem) {
        for (SinhVien sv : danhSachSinhVien) {
            if (sv.getId().equals(id)) {
                // 1. Kiểm tra Điểm mới
                if (newDiem < 0 || newDiem > 10) {
                    System.out.println("Lỗi Sửa: Điểm phải nằm trong khoảng [0 - 10].");
                    return false;
                }

                // 2. Kiểm tra Tên mới
                if (newTen == null || newTen.trim().isEmpty()) {
                    System.out.println("Lỗi Sửa: Tên sinh viên không được rỗng.");
                    return false;
                }
                // Biểu thức chính quy (Regex) kiểm tra tên
                if (!newTen.matches("^[\\p{L} .'-]+$")) {
                    System.out.println("Lỗi Sửa: Tên sinh viên không được chứa số hoặc ký tự đặc biệt.");
                    return false;
                }

                // Cập nhật thông tin
                sv.setTen(newTen);
                sv.setDiem(newDiem);
                return true;
            }
        }
        return false; // Không tìm thấy ID
    }

    // Hàm hỗ trợ cho Unit Test: Lấy tất cả danh sách
    public List<SinhVien> getDanhSachSinhVien() {
        return danhSachSinhVien;
    }
}