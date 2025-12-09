import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Class Test cho SinhVienService, sử dụng Nested Classes để quản lý
 * các môi trường setUp() khác nhau cho chức năng Thêm và Sửa.
 */
public class SinhVienServiceTest {

    // Khai báo service ở cấp độ cao nhất
    private SinhVienService service;

    // --- TEST CHỨC NĂNG THÊM SINH VIÊN (CREATE) ---
    // Môi trường test: Danh sách sinh viên phải trống trước mỗi test
    @Nested
    class ThemSinhVienTests {

        // Setup chỉ chạy trước các test trong lớp ThemSinhVienTests
        @BeforeEach
        void setUp() {
            service = new SinhVienService();
        }

        // 1. Test case HỢP LỆ (Phân vùng tương đương)
        @Test
        void themSinhVien_DuLieuHopLe_ThanhCong() {
            // Điểm: 5.0 (trong [0, 10]), Tuổi: 25 (trong [18, 30]), Tên hợp lệ
            SinhVien sv = new SinhVien("SV001", "Nguyen Van An", 5.0, "CNTT1", 25);
            assertTrue(service.themSinhVien(sv));
            assertEquals(1, service.getDanhSachSinhVien().size());
        }

        // 2. Test case Biên (Điểm MIN và Tuổi MIN)
        @Test
        void themSinhVien_BienDuLieu_Min_ThanhCong() {
            // Điểm: 0.0 (Biên dưới), Tuổi: 18 (Biên dưới)
            SinhVien sv = new SinhVien("SV002", "Tran Thi Binh", 0.0, "QTKD2", 18);
            assertTrue(service.themSinhVien(sv));
        }

        // 3. Test case Biên (Điểm MAX và Tuổi MAX)
        @Test
        void themSinhVien_BienDuLieu_Max_ThanhCong() {
            // Điểm: 10.0 (Biên trên), Tuổi: 30 (Biên trên)
            SinhVien sv = new SinhVien("SV003", "Le Duc Cuong", 10.0, "CNTT1", 30);
            assertTrue(service.themSinhVien(sv));
        }

        // 4. Test case KHÔNG HỢP LỆ (Điểm dưới biên: Phân vùng không hợp lệ Thấp)
        @Test
        void themSinhVien_DiemNhoHonKhong_ThatBai() {
            // Điểm: -0.1 (Không hợp lệ), Tuổi: 25 (Hợp lệ)
            SinhVien sv = new SinhVien("SV004", "Pham Thuy Duong", -0.1, "QTKD2", 25);
            assertFalse(service.themSinhVien(sv));
            assertEquals(0, service.getDanhSachSinhVien().size());
        }

        // 5. Test case KHÔNG HỢP LỆ (Tên chứa ký tự đặc biệt)
        @Test
        void themSinhVien_TenChuaKyTuDacBiet_ThatBai() {
            // Tên: "Hoang @ Van E" (Không hợp lệ), Điểm/Tuổi: Hợp lệ
            SinhVien sv = new SinhVien("SV005", "Hoang @ Van E", 7.5, "CNTT1", 20);
            assertFalse(service.themSinhVien(sv));
            assertEquals(0, service.getDanhSachSinhVien().size());
        }

        // 6. Test case KHÔNG HỢP LỆ (Tuổi trên biên: Phân vùng không hợp lệ Cao)
        @Test
        void themSinhVien_TuoiLonHonBaMuoi_ThatBai() {
            // Tuổi: 31 (Không hợp lệ)
            SinhVien sv = new SinhVien("SV006", "Nguyen Van F", 8.0, "CNTT1", 31);
            assertFalse(service.themSinhVien(sv));
            assertEquals(0, service.getDanhSachSinhVien().size());
        }

        // 7. Test case KHÔNG HỢP LỆ (Tên rỗng/khoảng trắng)
        @Test
        void themSinhVien_TenRong_ThatBai() {
            // Tên: " " (Không hợp lệ)
            SinhVien sv = new SinhVien("SV007", " ", 8.0, "CNTT1", 20);
            assertFalse(service.themSinhVien(sv));
            assertEquals(0, service.getDanhSachSinhVien().size());
        }
    }

    // --- TEST CHỨC NĂNG SỬA THÔNG TIN SINH VIÊN (UPDATE) ---
    // Môi trường test: Danh sách phải có sẵn một sinh viên để sửa
    @Nested
    class SuaSinhVienTests {
        private final String TEST_ID = "SV_SUA";

        // Setup chỉ chạy trước các test trong lớp SuaSinhVienTests
        @BeforeEach
        void setUp() {
            service = new SinhVienService();
            // Thêm một sinh viên ban đầu để có dữ liệu sửa
            service.themSinhVien(new SinhVien(TEST_ID, "Sinh Vien Ban Dau", 7.0, "KTPM", 22));
            assertEquals(1, service.getDanhSachSinhVien().size()); // Đảm bảo đã thêm
        }

        // 1. Test case HỢP LỆ (Phân vùng tương đương)
        @Test
        void suaThongTinSinhVien_DuLieuHopLe_ThanhCong() {
            // Tên mới hợp lệ: "Ten Moi", Điểm mới hợp lệ: 8.5
            boolean result = service.suaThongTinSinhVien(TEST_ID, "Ten Moi Hop Le", 8.5);
            assertTrue(result);
            SinhVien sv = service.getDanhSachSinhVien().get(0);
            assertEquals("Ten Moi Hop Le", sv.getTen());
            assertEquals(8.5, sv.getDiem());
        }

        // 2. Test case Biên (Điểm MIN)
        @Test
        void suaThongTinSinhVien_BienDiemMin_ThanhCong() {
            // Điểm mới: 0.0 (Biên dưới)
            assertTrue(service.suaThongTinSinhVien(TEST_ID, "Ten M", 0.0));
            assertEquals(0.0, service.getDanhSachSinhVien().get(0).getDiem());
        }

        // 3. Test case Biên (Điểm MAX)
        @Test
        void suaThongTinSinhVien_BienDiemMax_ThanhCong() {
            // Điểm mới: 10.0 (Biên trên)
            assertTrue(service.suaThongTinSinhVien(TEST_ID, "Ten M", 10.0));
            assertEquals(10.0, service.getDanhSachSinhVien().get(0).getDiem());
        }

        // 4. Test case KHÔNG HỢP LỆ (Điểm dưới biên)
        @Test
        void suaThongTinSinhVien_DiemNhoHonKhong_ThatBai() {
            // Điểm mới: -0.1 (Không hợp lệ)
            assertFalse(service.suaThongTinSinhVien(TEST_ID, "Ten M", -0.1));
            // Đảm bảo dữ liệu cũ không bị thay đổi
            assertEquals(7.0, service.getDanhSachSinhVien().get(0).getDiem());
        }

        // 5. Test case KHÔNG HỢP LỆ (Tên chứa số)
        @Test
        void suaThongTinSinhVien_TenChuaSo_ThatBai() {
            // Tên mới: "Ten 123" (Không hợp lệ)
            assertFalse(service.suaThongTinSinhVien(TEST_ID, "Ten 123", 8.0));
            // Đảm bảo tên cũ không bị thay đổi
            assertEquals("Sinh Vien Ban Dau", service.getDanhSachSinhVien().get(0).getTen());
        }

        // 6. Test case KHÔNG HỢP LỆ (ID không tồn tại)
        @Test
        void suaThongTinSinhVien_IDKhongTonTai_ThatBai() {
            // ID: "SV_FAKE" (Không tồn tại)
            assertFalse(service.suaThongTinSinhVien("SV_FAKE", "Ten Hop Le", 8.0));
        }
    }
}