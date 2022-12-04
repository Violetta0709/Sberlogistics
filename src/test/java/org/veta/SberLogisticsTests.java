package org.veta;

import com.codeborne.pdftest.PDF;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.pdftest.assertj.Assertions.assertThat;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class SberLogisticsTests {
    @BeforeAll
    static void configure() {
        baseUrl = "https://sberlogistics.ru";
        Configuration.browserSize = "1920x1080";
        Configuration.holdBrowserOpen = true;
        Selenide.clearBrowserCookies();
    }

    //@Disabled
    @Test
    @DisplayName("Request proposal for business")
    void fillFormForProposalTest() {
        open("/online_stores");
        $(".cookie-popup__button").click();
        $$(byText("Получить предложение")).last().scrollTo().click();
        //$("#formblock").shouldHave(text("Получить предложение для бизнеса"));
        //$("#sp-chat-widget").click();
        //$("#sp-close-frame").click();
        $("[name=fio]").setValue("Veta Veta");
        $("#form_business_phone").setValue("9123456789");
        $("[name=email]").setValue("veta@veta.com");
        $("#lead_amoform").click();
        $("#formblock").shouldHave(text("Ваше сообщение отправлено!"));

    }


    //@Disabled
    @Test
    @DisplayName("Checking info about regional offices")
    void checkRegionalOfficesInfoTest() {
        open("/about/regions");
        //$(".cookie-popup__button").click();
        $(".page-intro").shouldHave(text("Региональные офисы"));
        $(byText("Барнаул")).click();
        $(".region").shouldHave(text("Адрес"))
                .shouldHave(text("E-mail"));
    }


    //@Disabled
    @Test
    @DisplayName("Becoming partner")
    void fillFormBecomePartnerTest() {
        open("https://sblogistica.ru/partners/");
        $("h1").shouldHave(text("Партнерская программа"));
        $("#connectBtn").click();
        //$("a[href*='pickup-point.html']").click();
        $(".top-pickup-point").shouldHave(text("Сеть партнерских пунктов выдачи заказов"));
        //$(".cookie-popup__button").click();
        $("#intro").click();
        $("#name").sendKeys("Veta");
        $("#phone").sendKeys("912563879");
        $("#city").sendKeys("Kotor");
        $("#mail").sendKeys("veta@veta.com");
        $("#address").sendKeys("RGHJKYSH");
        $("[type=submit]").click();
        $(".popup").shouldHave(text("Спасибо за обращение!"));

    }


    //@Disabled
    @Test
    @DisplayName("Ordering custom clearance services")
    void fillFormCustomClearanceTest() {
        open("https://sblogistica.ru/international/");
        $(".landing-banner").shouldHave(text("Услуги по таможенному оформлению"));
        $(byText("Оставить заявку")).click();
        $("#form").shouldBe(visible);
        $("#username").sendKeys("Veta");
        $("#userphone").sendKeys("3127456985");
        $("#usermail").sendKeys("veta@veta.com");
        $("#fileInput").uploadFromClasspath("pdf/3.pdf");
        $("[type=submit]").click();
        $(".callback-form").shouldHave(text("Успешно отправлено!"));

        //$(".cookie-popup__button").click();
    }

    //@Disabled
    @Test
    @DisplayName("Ordering freight")
    void fillFormForFreightTest() {
        open("/business/freight");
        $("a[href*='/business/freight/order']").click();
        $(".cookie-popup__button").click();
        $(".hero").shouldHave(text("Заказать грузоперевозку"));
        $("#shipment").shouldHave(text("Место отправки"));
        $("#shipment_address").setValue("Санкт-Петербург, Кременчугская 9");
        $("#shipment_date").setValue("20" + "01" + "2023");
        $("#shipment_time").sendKeys("11" + "00");
        $("#shipment_company_name").setValue("ООО");
        $("#shipment_phone").setValue("9123456890");
        $("#shipment_full_name").setValue("Veta Veta");
        $("#add_shipment").shouldBe(visible);
        $("#delivery").shouldHave(text("Место доставки"));
        $("#delivery_address").setValue("Москва, Кременчугская 9");
        $("#delivery_date").setValue("27" + "01" + "2023");
        $("#delivery_time").sendKeys("11" + "00");
        $("#delivery_company_name").setValue("АААА");
        $("#delivery_phone").setValue("9123456789");
        $("#delivery_full_name").setValue("Veta Veta");
        $("#add_delivery").shouldBe(visible);
        $("#cargo").shouldHave(text("Груз"));
        $("#cargo_description").setValue("Мебель");
        $("#cargo_pack_type").selectOptionByValue("Короб");
        $("#cargo_places_quantity").setValue("10");
        $("#cargo_weight").setValue("100");
        $("#cargo_volume").setValue("100");
        $("#add_cargo").shouldBe(visible);
        $("#transport_capacity").selectOptionByValue("1т / 7м3 / 3пал.");
        $("#transport_type").selectOption("Фургон (будка)");
        $("#transport_loading_type").selectOption("Задняя загрузка");
        $("#additional").$(byText("Цепь")).click();
        $("#desired_cost").setValue("10000");
        $("[for=documents_return]").click();
        $(byText("Оформить заявку")).click();
        $("#freight_order").shouldHave(text("Успешно отправлено"));
    }

    //@Disabled
    @Test
    @DisplayName("Check user agreement content")
    void checkUserAgreementTest() throws Exception {
        open("/about/documents/terms_of_use");
        File downloadedFile = $("a[href*='https://sberlogistics.ru/uploads/documents/Пользовательское_соглашение.pdf']").download();
        PDF pdf = new PDF(downloadedFile);
        assertThat(pdf.text).contains("ПОЛЬЗОВАТЕЛЬСКОЕ СОГЛАШЕНИЕ");
    }

    //@Disabled
    @DisplayName("Filling partnershipform")
    @Test
    void fillPartnershipFormTest() {
        open("https://sblogistica.ru/partners/pickup-point.html");
        $("#join").scrollTo();
        $("#name").setValue("Вета");
        $("#city").setValue("Vvv");
        $("#phone").setValue("9105246123");
        $("#mail").setValue("vvv@vvv.com");
        $("#partners-form").$("[type=submit]").click();
        $(".popup").shouldHave(text("Спасибо за обращение"));

    }

    //@Disabled
    @Test
    @DisplayName("Tracking parcel")
    void trackParcelTest() {
        open("https://sblogistica.ru");
        $("[name=number]").setValue("123456789");
        $("#button-parcel").click();
        $("._shiptor_widget_information").shouldHave(text("Информация о посылке"))
                .shouldHave(text("Получатель"))
                .shouldHave(text("Вес"))
                .shouldHave(text("Размер"))
                .shouldHave(text("Услуга"))
                .shouldHave(text("Телефон"))
                .shouldHave(text("Место выдачи"));
    }
}


