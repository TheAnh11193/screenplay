@xacnhancong
Feature: Xac nhan cong

#  @ID_21
#  Scenario: User opens login
#    Given The "user" open the login page
#    When I open bang cong chi tiet
#    When I verify detailed work sheet
#    When The "approver" open the odoo page

  @ID-26
  Scenario: Thong tin can xac nhan
    Given The "user" open the login page
    When I open bang cong chi tiet
    And Chọn ngày "2025-09-03" cần tạo đơn
#    And Chọn thời điểm xác nhận công
#    Then Kiểm tra list thời điểm xác nhận công hiển thị
    And Chọn ngày để xác nhận công với nội dung lỗi "ID_26"
    Then Verify nội dung lỗi "ID_26"

  @ID-27 @ID_28 @ID_29 @ID_30
  Scenario: Thong tin can xac nhan
    Given The "user" open the login page
    When I open bang cong chi tiet
    And Chọn ngày "2025-09-03" cần tạo đơn
    And Select "Giờ vào và giờ ra" cho thời điểm cần xác nhận công
    And Select "Đi muộn/về sớm" cho lý do xác nhận giờ vào
    And Select "Tai nạn, thiên tai, hỏa hoạn" cho "Lý do đi muộn"
    And Select "Đi muộn/về sớm" cho lý do xác nhận giờ ra
    And Select "Tai nạn, thiên tai, hỏa hoạn" cho "Lý do về sớm"

#  @ID-2
#  Scenario: Thong tin can xac nhan
#    Given The "user1" open the login page
#    When I open bang cong chi tiet
#    And Chọn ngày "2025-09-18" cần tạo đơn
#    And Chọn ngày để tạo xác nhận công thành công với nội dung "Gio vao va gio ra"
#    Given The "reviewer" open the login page
#    When I open phê duyệt xác nhận công
#    And Chọn đơn ngày "2025-09-11" cần phê duyệt xác nhận công
#    And Chọn "Phê Duyệt" xác nhận công