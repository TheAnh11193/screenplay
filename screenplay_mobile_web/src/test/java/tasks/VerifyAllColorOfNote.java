package tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import questions.CssColor;
import ui.BangCongChiTietPage;
import ui.WebLoginPage;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;

public class VerifyAllColorOfNote implements Task {

    public static VerifyAllColorOfNote areCorrect() {
        return Tasks.instrumented(VerifyAllColorOfNote.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.should(
                seeThat("Xác Nhận Công",
                        CssColor.of(BangCongChiTietPage.color_XACNHANCONG, "background-color"),
                        equalToIgnoringCase("#ffc83a")),
                seeThat("Chấm Đúng Giờ",
                        CssColor.of(BangCongChiTietPage.color_CHAMDUNGGIO, "background-color"),
                        equalToIgnoringCase("#1ccd83")),
                seeThat("Đi muộn / Về sớm",
                        CssColor.of(BangCongChiTietPage.color_DIMUONVESOM, "background-color"),
                        equalToIgnoringCase("#fe4c53")),
                seeThat("Nghỉ phép",
                        CssColor.of(BangCongChiTietPage.color_NGHIPHEP, "background-color"),
                        equalToIgnoringCase("#1c3867")),
                seeThat("Ngày lễ",
                        CssColor.of(BangCongChiTietPage.color_NGAYLE, "background-color"),
                        equalToIgnoringCase("#714b467")),
                seeThat("Làm việc ngoài văn phòng",
                        CssColor.of(BangCongChiTietPage.color_LAMVIECNGOAIVANPHONG, "background-color"),
                        equalToIgnoringCase("#6788fb")),
                seeThat("Không đi làm",
                        CssColor.of(BangCongChiTietPage.color_KHONGDILAM, "background-color"),
                        equalToIgnoringCase("#7d868d"))
        );
    }
}
