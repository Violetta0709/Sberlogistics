package org.veta.tests;

import com.codeborne.pdftest.PDF;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.google.common.collect.Ordering;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.veta.pages.*;

import java.io.File;

import static com.codeborne.pdftest.assertj.Assertions.assertThat;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class SberLogisticsTests {
    RequestProposal request = new RequestProposal();
    RegionalOffices regional = new RegionalOffices();
    BecomingPartner partner = new BecomingPartner();
    CustomClearance services = new CustomClearance();
    OrderingFreight freight = new OrderingFreight();
    TrackingParcel tracking = new TrackingParcel();

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
        request.openPage()
                .askProposal()
                .setUserName("Veta")
                .setNumber("9123456789")
                .setEmail("veta@veta.com")
                .clickSubmit()
                .checkResult();
    }

    //@Disabled
    @Test
    @DisplayName("Checking info about regional offices")
    void checkRegionalOfficesInfoTest() {
        regional.openPage()
                .checkTitle()
                .chooseCity()
                .checkResult();
    }

    //@Disabled
    @Test
    @DisplayName("Becoming partner")
    void fillFormBecomePartnerTest() {
        partner.openPage()
                .checkTitle()
                .connect()
                .checkPickup()
                .becomePartner()
                .setUserName("Veta")
                .setNumber("9123456789")
                .setCity("xxxx")
                .setEmail("veta@veta.com")
                .setAddress("dfghj")
                .clickSubmit()
                .checkResult();
    }

    //@Disabled
    @Test
    @DisplayName("Ordering custom clearance services")
    void fillFormCustomClearanceTest() {
        services.openPage()
                .checkTitle()
                .makeApplication()
                .checkForm()
                .setUserName("Veta")
                .setNumber("3127456985")
                .setEmail("veta@veta.com")
                .uploadFile("pdf/3.pdf")
                .clickSubmit()
                .checkResult();
    }

    //@Disabled
    @Test
    @DisplayName("Ordering freight")
    void fillFormForFreightTest() {
        freight.openPage()
                .orderFreight()
                .popupClose()
                .checkTitle1()
                .checkTitle2()
                .setShipmentAddress("Санкт-Петербург, Кременчугская 9")
                .setShipmentDate("20" + "01" + "2023")
                .setShipmentTime("11" + "00")
                .setShipmentCompany("ООО")
                .setShipmentPhone("9123456890")
                .setShipmentName("Veta Veta")
                .checkAddShipment()
                .checkTitle3()
                .setDeliveryAddress("Москва, Кременчугская 9")
                .setDeliveryDate("27" + "01" + "2023")
                .setDeliveryTime("11" + "00")
                .setDeliveryCompany("АААА")
                .setDeliveryPhone("9123456789")
                .setDeliveryName("Veta Veta")
                .checkAddDelivery()
                .setCargoDescription("Мебель")
                .selectPackType("Короб")
                .setCargoQty("10")
                .setCargoWeight("100")
                .setCargoVolume("100")
                .checkAddCargo()
                .selectTransportCapacity("1т / 7м3 / 3пал.")
                .selectTransportType("Фургон (будка)")
                .selectTransportLoadType("Задняя загрузка")
                .checkExtraEquip()
                .setDesiredCost("100000")
                .checkDocReturn()
                .clickSubmit()
                .checkResult();
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
    @Test
    @DisplayName("Tracking parcel")
    void trackParcelTest() {
        tracking.openPage()
                .setTrackingNumber("123456789")
                .clickSubmit()
                .checkResult();
    }
}


