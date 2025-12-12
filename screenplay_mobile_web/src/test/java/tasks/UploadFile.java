package tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Scroll;
import net.serenitybdd.screenplay.actions.ScrollTo;
import ui.WebEbizChuyenTienPage;

public class UploadFile implements Task {
    private final String absolutePath;

    public UploadFile(String absolutePath){
        this.absolutePath = absolutePath;
    }

    public static UploadFile from(String absolutePath){
        return Tasks.instrumented(UploadFile.class,absolutePath);
    }

    @Override
    public <T extends Actor> void performAs(T actor){
        actor.attemptsTo(Scroll.to(WebEbizChuyenTienPage.input_UPLOAD));
        WebEbizChuyenTienPage.input_UPLOAD.resolveFor(actor).sendKeys(absolutePath);
    }
}
