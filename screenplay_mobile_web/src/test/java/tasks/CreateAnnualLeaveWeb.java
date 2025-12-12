package tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import ui.WebEbizLoginPage;

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
//                Click.on(WebEbizLoginPage.icon_NGHIPHEP),
//                Click.on(WebEbizLoginPage.btn_LOAINGHI),
//                Click.on(WebEbizLoginPage.value_LOAINGHIORNGHI(loainghi)),
//                Enter.theValue(ngaybatdau).into(WebEbizLoginPage.txtbx_NGAYBATDAU),
//                Enter.theValue(ngayketthuc).into(WebEbizLoginPage.txtbx_NGAYKETTHUC),
//                Enter.theValue(lydonghi).into(WebEbizLoginPage.txtbx_LYDONGHI),
//                Click.on(WebEbizLoginPage.btn_LOAI),
//                Click.on(WebEbizLoginPage.value_LOAINGHIORNGHI(loai))
//                Click.on(WebLoginPage.btn_DANGKYNGHI)
        );
    }
}
