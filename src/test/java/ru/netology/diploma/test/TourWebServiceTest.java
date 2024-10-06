package ru.netology.diploma.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;

import ru.netology.diploma.data.DBaseHelp;
import ru.netology.diploma.data.DataHelp;
import ru.netology.diploma.page.TourPurchasePage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TourWebServiceTest {
    @BeforeAll
    static void setUpAll() {
        DataHelp.creatingTestDataMap();
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    void setup() {
        DBaseHelp.cleanUpDB();
        open("http://localhost:8080");
    }

    @Test
    @DisplayName("Must successfully buy the tour. All fields are valid, current year and month")
    void allFieldsAreValidCurrentYearAndMonthToBuy() {
        var tourPurchasePage = new TourPurchasePage();
        tourPurchasePage.getBayTourButton(DataHelp.generateUser("scenario_01"));
        tourPurchasePage.validPopUp();
        assertEquals(DataHelp.validDataToBay().getNewRowsDB(), DBaseHelp.countAll());
        assertEquals(DataHelp.validDataToBay().getPrice(), DBaseHelp.paymentData().getAmount());
        assertEquals(DataHelp.validDataToBay().getStatus(), DBaseHelp.paymentData().getStatus());
    }

    @Test
    @DisplayName("Must successfully buy the tour. All fields are valid, current year and month plus five years")
    void allFieldsAreValidCurrentYearAndMonthPlusFiveYearsToBuy() {
        var tourPurchasePage = new TourPurchasePage();
        tourPurchasePage.getBayTourButton(DataHelp.generateUser("scenario_02"));
        tourPurchasePage.validPopUp();
        assertEquals(DataHelp.validDataToBay().getNewRowsDB(), DBaseHelp.countAll());
        assertEquals(DataHelp.validDataToBay().getPrice(), DBaseHelp.paymentData().getAmount());
        assertEquals(DataHelp.validDataToBay().getStatus(), DBaseHelp.paymentData().getStatus());
    }

    @Test
    @DisplayName("Must successfully buy the tour.  All fields are valid, first name and last name two letters")
    void allFieldsAreValidFirstNameAndLastNameTwoLettersToBuy() {
        var tourPurchasePage = new TourPurchasePage();
        tourPurchasePage.getBayTourButton(DataHelp.generateUser("scenario_03"));
        tourPurchasePage.validPopUp();
        assertEquals(DataHelp.validDataToBay().getNewRowsDB(), DBaseHelp.countAll());
        assertEquals(DataHelp.validDataToBay().getPrice(), DBaseHelp.paymentData().getAmount());
        assertEquals(DataHelp.validDataToBay().getStatus(), DBaseHelp.paymentData().getStatus());
    }

    @Test
    @DisplayName("Must not successfully pay for the tour. All fields are valid, the number of the second bank card with the status DECLINED")
    void allFieldsAreValidTheNumberOfTheSecondBankCardWithTheStatusDeclinedToBuy() {
        var tourPurchasePage = new TourPurchasePage();
        tourPurchasePage.getBayTourButton(DataHelp.generateUser("scenario_04"));
        tourPurchasePage.notValidPopUp();
        assertEquals(DataHelp.notValidDataToBay().getNewRowsDB(), DBaseHelp.countAll());
        assertEquals(DataHelp.notValidDataToBay().getPrice(), DBaseHelp.paymentData().getAmount());
        assertEquals(DataHelp.notValidDataToBay().getStatus(), DBaseHelp.paymentData().getStatus());
    }

    @Test
    @DisplayName("Must not successfully pay for the tour. All fields are valid, bank card number is empty")
    void allFieldsAreValidBankCardNumberIsEmptyToBuy() {
        var tourPurchasePage = new TourPurchasePage();
        tourPurchasePage.getBayTourButton(DataHelp.generateUser("scenario_05"));
        tourPurchasePage.numberNotCorrect();
        assertEquals(DataHelp.notValidInfo().getNewRowsDB(), DBaseHelp.countAll());
    }

    @Test
    @DisplayName("Must not successfully pay for the tour. All fields are valid, bank card number is filled with zeros")
    void allFieldsAreValidBankCardNumberIsFilledWithZerosToBuy() {
        var tourPurchasePage = new TourPurchasePage();
        tourPurchasePage.getBayTourButton(DataHelp.generateUser("scenario_06"));
        tourPurchasePage.notValidPopUp();
        assertEquals(DataHelp.notValidInfo().getNewRowsDB(), DBaseHelp.countAll());
    }
}