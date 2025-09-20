@xacnhancong
Feature: Xac nhan cong

#  Scenario: User opens login
#    Given The "user" open the login page
#    When I open bang cong chi tiet
#    When I verify detailed work sheet
##    When create annual leave
#    When The "approver" open the odoo page
##    And The "approver" open app mobile
#  @ID-27
#  Scenario: Thong tin can xac nhan
#    Given The "user1" open the login page
#    When I open bang cong chi tiet
#    And Chọn ngày "2025-09-11" cần tạo đơn
#    And Chọn thời điểm xác nhận công
#    Then Kiểm tra list thời điểm xác nhận công hiển thị
##    And Chọn ngày để xác nhận công với nội dung lỗi "Gio vao loi 3"
#
#  @ID-28
#  Scenario: Thong tin can xac nhan
#    Given The "user1" open the login page
#    When I open bang cong chi tiet
#    And Chọn ngày "2025-09-11" cần tạo đơn
#    And Chọn thời điểm xác nhận công
#    Then Kiểm tra list thời điểm xác nhận công hiển thị

  @ID-2
  Scenario: Thong tin can xac nhan
    Given The "user1" open the login page
    When I open bang cong chi tiet
    And Chọn ngày "2025-09-11" cần tạo đơn
    And Chọn ngày để tạo xác nhận công thành công với nội dung "Gio vao 1"
    Given The "reviewer" open the login page
    When I open phê duyệt xác nhận công
    And Chọn đơn ngày "2025-09-11" cần phê duyệt xác nhận công
    And Chọn "Phê Duyệt" xác nhận công