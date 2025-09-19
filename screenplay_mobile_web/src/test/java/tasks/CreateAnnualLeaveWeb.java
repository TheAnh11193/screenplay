package tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import ui.WebLoginPage;
import utils.JsonDataReader;
import utils.SerenityConfigReader;

import java.util.Map;

public class CreateAnnualLeaveWeb implements Task {
    private final String loainghi;
    private final String ngaybatdau;
    private final String ngayketthuc;
    private final String lydonghi;
    private final String loai;

    public CreateAnnualLeaveWeb(String loainghi,String ngaybatdau, String ngayketthuc, String lydonghi, String loai) {
        this.loainghi = loainghi;
        this.ngaybatdau = ngaybatdau;
        this.ngayketthuc = ngayketthuc;
        this.lydonghi = lydonghi;
        this.loai = loai;
    }

    public static CreateAnnualLeaveWeb withDetails(String loainghi,String ngaybatdau, String ngayketthuc, String lydonghi, String loai) {
        return Tasks.instrumented(CreateAnnualLeaveWeb.class, loainghi, ngaybatdau,ngayketthuc,lydonghi,loai);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Click.on(WebLoginPage.icon_NGHIPHEP),
                Click.on(WebLoginPage.btn_LOAINGHI),
                Click.on(WebLoginPage.value_LOAINGHIORNGHI(loainghi)),
                Enter.theValue(ngaybatdau).into(WebLoginPage.txtbx_NGAYBATDAU),
                Enter.theValue(ngayketthuc).into(WebLoginPage.txtbx_NGAYKETTHUC),
                Enter.theValue(lydonghi).into(WebLoginPage.txtbx_LYDONGHI),
                Click.on(WebLoginPage.btn_LOAI),
                Click.on(WebLoginPage.value_LOAINGHIORNGHI(loai))
//                Click.on(WebLoginPage.btn_DANGKYNGHI)
        );
    }
}
