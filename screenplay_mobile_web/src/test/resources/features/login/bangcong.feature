#@Bangcong
#Feature: Bang cong
#
#  @ID_01 @ID_02 @ID_03 @ID_04 @ID_05 @ID_06 @ID_08 @ID_09 @ID_10 @ID_11
#  Scenario: Trong các ngày thường
#    Given The "admin" open the odoo page
#    When I open chấm công menu
#    And Tìm kiếm nhân viên với tên "hoadp"
##    Then Verify kết quả chấm công trong tuần
#    Then Verify nghỉ KL sáng đi làm chiều ngày "2025-09-11" với ký hiệu "0,5KL"
#    Then Verify nghỉ KL chiều đi làm sáng ngày "2025-09-09" với ký hiệu "0,5KL"
#    Then Verify nghỉ thai sản ngày "2025-09-15" với ký hiệu "TS"
#    Then Verify nghỉ BHXH ngày "2025-09-16" với ký hiệu "CĐ"
#    Then Verify nghỉ dịp đặc biệt ngày "2025-09-17" với ký hiệu "CĐ1"
#    Then Verify nghỉ phép sáng đi làm chiều ngày "2025-09-10" với ký hiệu "0,5P"
#    Then Verify đơn xác nhận công được duyệt ngày "2025-09-04" với ký hiệu "1"
#    Then Verify đơn điều động công tác được duyệt ngày "2025-09-18" với ký hiệu "CT"
#    Then Verify ngày "2025-09-19" không có kết quả chấm công với ký hiệu "KL"
##    Then Verify nghỉ phép chiều đi làm sáng ngày ""
#
#  @ID_14 @ID_16 @ID_17 @ID_18 @ID_19
#  Scenario: Thong tin can xac nhan
#    Given The "nhanvien" open the login page
#    When I open bang cong chi tiet
#    Then Verify đơn xin nghỉ sáng đi làm chiều ngày "2025-09-10" được phê duyệt
#    Then Verify đơn xác nhận công ngày "2025-09-04" được phê duyệt
#    Then Verify đơn xin nghỉ phép ngày "2025-09-15" được phê duyệt
#    Then Verify đơn điều động công tác ngày "2025-09-18" được phê duyệt
#    Then Verify ngày "2025-09-19" không có kết quả chấm công
