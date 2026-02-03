@EbizChuyentienlongoai
Feature: Ebiz

  @Ebiz01
  Scenario: Ebiz Chuyen tien lo ngoai
    Given The "user" open the Ebiz login page
    When User khoi tao "Chuyen tien theo lo" giao dich
    And User nhap thong tin theo "Chuyen tien theo lo"
    And Kiem tra noi dung file upload
    And User "Xac nhan" giao dich
    And User verify khoi tao giao dich thanh cong
    And User vao "Nhat ki giao dich" de kiem tra
#    When I open bang cong chi tiet
#    And Chọn ngày "2025-09-11" cần tạo đơn