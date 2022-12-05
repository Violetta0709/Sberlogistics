package org.veta.tests;

import com.codeborne.pdftest.PDF;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.veta.pages.*;

import java.io.File;

import static com.codeborne.pdftest.assertj.Assertions.assertThat;
import static com.codeborne.selenide.Selenide.*;
import static org.veta.tests.testdata.TestData.*;


public class SberLogisticsTests extends TestBase {
    RequestProposal request = new RequestProposal();
    RegionalOffices regional = new RegionalOffices();
    BecomingPartner partner = new BecomingPartner();
    CustomClearance services = new CustomClearance();
    OrderingFreight freight = new OrderingFreight();
    TrackingParcel tracking = new TrackingParcel();
    UserAgreement content = new UserAgreement();

    //@Disabled
    @Test
    @DisplayName("Request proposal for business")
    void fillFormForProposalTest() {
        request.openPage()
                .askProposal()
                .setUserName(fullName)
                .setNumber(phone)
                .setEmail(email)
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
                .setUserName(fullName)
                .setNumber(phone)
                .setCity(city)
                .setEmail(email)
                .setAddress(address)
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
                .setUserName(fullName)
                .setNumber(phone)
                .setEmail(email)
                .uploadFile(filePath)
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
                .setShipmentAddress(address)
                .setShipmentDate(shipDate)
                .setShipmentTime(shipTime)
                .setShipmentCompany(companyName)
                .setShipmentPhone(phone)
                .setShipmentName(fullName)
                .checkAddShipment()
                .checkTitle3()
                .setDeliveryAddress(address)
                .setDeliveryDate(delDate)
                .setDeliveryTime(delTime)
                .setDeliveryCompany(companyName)
                .setDeliveryPhone(phone)
                .setDeliveryName(fullName)
                .checkAddDelivery()
                .setCargoDescription(cargoType)
                .selectPackType(packType)
                .setCargoQty(cargoQty)
                .setCargoWeight(cargoWeight)
                .setCargoVolume(cargoVolume)
                .checkAddCargo()
                .selectTransportCapacity(transpCapacity)
                .selectTransportType(transpType)
                .selectTransportLoadType(loadType)
                .checkExtraEquip()
                .setDesiredCost(cost)
                .checkDocReturn()
                .clickSubmit()
                .checkResult();
    }

    //@Disabled
    @Test
    @DisplayName("Check user agreement content")
    void checkUserAgreementTest() throws Exception {
        content.openPage();
        File downloadedFile = content.getLink().download();
        PDF pdf = new PDF(downloadedFile);
        assertThat(pdf.text).contains(info);
    }

    //@Disabled
    @Test
    @DisplayName("Tracking parcel")
    void trackParcelTest() {
        tracking.openPage()
                .setTrackingNumber(tracknumber)
                .clickSubmit()
                .checkResult();
    }
}


