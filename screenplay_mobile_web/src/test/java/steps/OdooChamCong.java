package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.questions.Visibility;
import net.serenitybdd.screenplay.waits.WaitUntil;
import questions.VerifyWeekDayColors;
import tasks.VerifyAllColorOfNote;
import ui.BangCongChiTietPage;
import ui.OdooChamCongPage;
import ui.OdooHomeMenuPage;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;
import static org.hamcrest.Matchers.is;

public class OdooChamCong {
    @And("Tìm kiếm nhân viên với tên {string}")
    public void timKiemNhanVienVoiTen(String name) {
        OnStage.theActorInTheSpotlight()
                .attemptsTo(
                        WaitUntil.the(OdooChamCongPage.input_SEARCHBOX,isVisible()).forNoMoreThan(10).seconds());
        OnStage.theActorInTheSpotlight()
                .attemptsTo(
                        Enter.theValue(name).into(OdooChamCongPage.input_SEARCHBOX));
        OnStage.theActorInTheSpotlight()
                .attemptsTo(
                        Click.on(OdooChamCongPage.dropdown_VALUESEARCHMENU));
        OnStage.theActorInTheSpotlight()
                .attemptsTo(
                        Click.on(OdooChamCongPage.btn_PREVIOUSMONTHODOO));

//        OnStage.theActorInTheSpotlight()
//                .attemptsTo(
//                        WaitUntil.the(OdooChamCongPage.result_SEARCH,isVisible()).forNoMoreThan(10).seconds());
    }

    @Then("Verify kết quả chấm công trong tuần")
    public void verifyKetQuaChamCongTrongTuan() {
        OnStage.theActorInTheSpotlight().should(seeThat(VerifyWeekDayColors.areCorrect()));
    }

    @Then("Verify nghỉ KL sáng đi làm chiều ngày {string} với ký hiệu {string}")
    public void verifyNghiKLSangDiLamChieuNgay(String date, String title) {
        OnStage.theActorInTheSpotlight().should(
                seeThat("Date "+ date+ " and ky hieu cong "+ title +" not be correct",
                        Visibility.of(
                                OdooChamCongPage.verify_DATEVAKYHIEUCONG(date,title)), is(true)));
    }

    @Then("Verify nghỉ KL chiều đi làm sáng ngày {string} với ký hiệu {string}")
    public void verifyNghiKLChieuDiLamSangNgayVoiKyHieu(String date, String title) {
        OnStage.theActorInTheSpotlight().should(
                seeThat("Date "+ date+ " and ky hieu cong "+ title +" not be correct",
                        Visibility.of(
                                OdooChamCongPage.verify_DATEVAKYHIEUCONG(date,title)), is(true)));
    }

    @Then("Verify nghỉ thai sản ngày {string} với ký hiệu {string}")
    public void verifyNghiThaiSanNgayVoiKyHieu(String date, String title) {
        OnStage.theActorInTheSpotlight().should(
                seeThat("Date "+ date+ " and ky hieu cong "+ title +" not be correct",
                        Visibility.of(
                                OdooChamCongPage.verify_DATEVAKYHIEUCONG(date,title)), is(true)));
    }

    @Then("Verify nghỉ BHXH ngày {string} với ký hiệu {string}")
    public void verifyNghiBHXHNgayVoiKyHieu(String date, String title) {
        OnStage.theActorInTheSpotlight().should(
                seeThat("Date "+ date+ " and ky hieu cong "+ title +" not be correct",
                        Visibility.of(
                                OdooChamCongPage.verify_DATEVAKYHIEUCONG(date,title)), is(true)));
    }

    @Then("Verify nghỉ dịp đặc biệt ngày {string} với ký hiệu {string}")
    public void verifyNghiDipDacBietNgayVoiKyHieu(String date, String title) {
        OnStage.theActorInTheSpotlight().should(
                seeThat("Date "+ date+ " and ky hieu cong "+ title +" not be correct",
                        Visibility.of(
                                OdooChamCongPage.verify_DATEVAKYHIEUCONG(date,title)), is(true)));
    }

    @Then("Verify nghỉ phép sáng đi làm chiều ngày {string} với ký hiệu {string}")
    public void verifyNghiPhepSangDiLamChieuNgayVoiKyHieu(String date, String title) {
        OnStage.theActorInTheSpotlight().should(
                seeThat("Date "+ date+ " and ky hieu cong "+ title +" not be correct",
                        Visibility.of(
                                OdooChamCongPage.verify_DATEVAKYHIEUCONG(date,title)), is(true)));
    }

    @Then("Verify đơn xác nhận công được duyệt ngày {string} với ký hiệu {string}")
    public void verifyDonXacNhanCongDuocDuyetNgayVoiKyHieu(String date, String title) {
        OnStage.theActorInTheSpotlight().should(
                seeThat("Date "+ date+ " and ky hieu cong "+ title +" not be correct",
                        Visibility.of(
                                OdooChamCongPage.verify_DATEVAKYHIEUCONG(date,title)), is(true)));
    }

    @Then("Verify đơn điều động công tác được duyệt ngày {string} với ký hiệu {string}")
    public void verifyDonDieuDongCongTacDuocDuyetNgayVoiKyHieu(String date, String title) {
        OnStage.theActorInTheSpotlight().should(
                seeThat("Date "+ date+ " and ky hieu cong "+ title +" not be correct",
                        Visibility.of(
                                OdooChamCongPage.verify_DATEVAKYHIEUCONG(date,title)), is(true)));
    }

    @Then("Verify ngày {string} không có kết quả chấm công với ký hiệu {string}")
    public void verifyNgayKhongCoKetQuaChamCongVoiKyHieu(String date, String title) {
        OnStage.theActorInTheSpotlight().should(
                seeThat("Date "+ date+ " and ky hieu cong "+ title +" not be correct",
                        Visibility.of(
                                OdooChamCongPage.verify_DATEVAKYHIEUCONG(date,title)), is(true)));
    }
}
