package org.veta.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class RegionalOffices {

    private SelenideElement
            pageIntro = $(".page-intro"),
            city = $(byText("Барнаул")),
            regionInfo = $(".region");

    public RegionalOffices openPage() {
        open("/about/regions");
        //$(".cookie-popup__button").click();
        return this;
    }

    public RegionalOffices checkTitle() {
        pageIntro.shouldHave(text("Региональные офисы"));
        return this;
    }

    public RegionalOffices chooseCity() {
        city.click();
        return this;
    }

    public RegionalOffices checkResult() {
        regionInfo.shouldHave(text("Адрес"))
                .shouldHave(text("E-mail"));
        return this;
    }

}


