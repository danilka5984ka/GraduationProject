package test;

import com.codeborne.selenide.logevents.SelenideLogger;
import data.DBHelper;
import data.DataHelper;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import page.AppPage;

import static com.codeborne.selenide.Selenide.open;

public class CreditPaymentTest {

    AppPage tourPage = new AppPage();

    @BeforeEach
    void setUp() {
        open("http://localhost:8080");
    }

    @AfterEach
    void clearDB() {
        DBHelper.clearDB();
    }

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @Test
    @DisplayName("1) HappyPath CreditCard Status: APPROVED")
    void shouldSuccessfulBuyByCreditCardApproved() {
        tourPage.completeCreditForm(DataHelper.getFirstNumber(), DataHelper.getMonth(0), DataHelper.getYear(0), DataHelper.getEngHolder(), DataHelper.getCVC());
        tourPage.continueClick();
        Assertions.assertAll(
                () -> tourPage.acceptAssertion(),
                () -> tourPage.creditApprovedStatusAssertion(),
                () -> tourPage.creditAcceptCountAssertion(),
                () -> tourPage.orderAcceptCountAssertion()
        );
    }

    @Test
    @DisplayName("2) HappyPath CreditCard Status: DECLINED")
    void shouldFailureBuyByCreditCardDeclined() {
        tourPage.completeCreditForm(DataHelper.getSecondNumber(), DataHelper.getMonth(0), DataHelper.getYear(0), DataHelper.getEngHolder(), DataHelper.getCVC());
        tourPage.continueClick();
        Assertions.assertAll(
                () -> tourPage.denialAssertion(),
                () -> tourPage.creditDeclinedStatusAssertion(),
                () -> tourPage.payDenialCountAssertion(),
                () -> tourPage.orderDenialCountAssertion()
        );
    }

    @Test
    @DisplayName("3) UnHappyPath CreditCard Random Invalid CardField")
    void shouldFailureBuyByCreditCardInvalidCardField() {
        tourPage.completeCreditForm(DataHelper.getRandomNumber(), DataHelper.getMonth(0), DataHelper.getYear(0), DataHelper.getEngHolder(), DataHelper.getCVC());
        tourPage.continueClick();
        Assertions.assertAll(
                () -> tourPage.denialAssertion(),
                () -> tourPage.payDenialCountAssertion(),
                () -> tourPage.orderDenialCountAssertion()
        );
    }

    // Тестирование поля "Номер карты"

    @Test
    @DisplayName("4) CreditCard Test CardField: 16 symbols")
    void shouldSuccessfulBuyByValidCreditCardFieldSixteenSymbols() {
        tourPage.completeCreditForm(DataHelper.getFirstNumber(), DataHelper.getMonth(0), DataHelper.getYear(0), DataHelper.getEngHolder(), DataHelper.getCVC());
        tourPage.continueClick();
        Assertions.assertAll(
                () -> tourPage.acceptAssertion(),
                () -> tourPage.creditApprovedStatusAssertion(),
                () -> tourPage.creditAcceptCountAssertion(),
                () -> tourPage.orderAcceptCountAssertion()
        );
    }

    @Test
    @DisplayName("5) CreditCard Test CardField: 15 symbols")
    void shouldFailureBuyByInvalidCreditCardFieldFifteenSymbols() {
        tourPage.completeCreditForm(DataHelper.getFifteenRandomNumber(), DataHelper.getMonth(0), DataHelper.getYear(0), DataHelper.getEngHolder(), DataHelper.getCVC());
        tourPage.continueClick();
        Assertions.assertAll(
                () -> tourPage.numberFieldFormatError(),
                () -> tourPage.creditDenialCountAssertion(),
                () -> tourPage.orderDenialCountAssertion()
        );
    }

    @Test
    @DisplayName("6) CreditCard Test CardField: Empty")
    void shouldFailureBuyByInvalidCreditCardFieldEmpty() {
        tourPage.completeCreditForm(DataHelper.getEmptyNumber(), DataHelper.getMonth(0), DataHelper.getYear(0), DataHelper.getEngHolder(), DataHelper.getCVC());
        tourPage.continueClick();
        Assertions.assertAll(
                () -> tourPage.numberFieldFormatError(),
                () -> tourPage.creditDenialCountAssertion(),
                () -> tourPage.orderDenialCountAssertion()
        );
    }

    @Test
    @DisplayName("7) CreditCard Test CardField: One Symbol")
    void shouldFailureBuyByInvalidCreditCardFieldOneSymbol() {
        tourPage.completeCreditForm(DataHelper.getOneRandomNumber(), DataHelper.getMonth(0), DataHelper.getYear(0), DataHelper.getEngHolder(), DataHelper.getCVC());
        tourPage.continueClick();
        Assertions.assertAll(
                () -> tourPage.numberFieldFormatError(),
                () -> tourPage.creditDenialCountAssertion(),
                () -> tourPage.orderDenialCountAssertion()
        );
    }

    // Тестирование поля "месяц"

    @Test
    @DisplayName("8) CreditCard Test MonthField: 11 month")
    void shouldSuccessfulBuyByValidTwelveMonthMonthFieldCreditCard() {
        tourPage.completeCreditForm(DataHelper.getFirstNumber(), DataHelper.getElevenMonth(), DataHelper.getYear(1), DataHelper.getEngHolder(), DataHelper.getCVC());
        tourPage.continueClick();
        Assertions.assertAll(
                () -> tourPage.acceptAssertion(),
                () -> tourPage.creditApprovedStatusAssertion(),
                () -> tourPage.creditAcceptCountAssertion(),
                () -> tourPage.orderAcceptCountAssertion()
        );
    }

    @Test
    @DisplayName("9) CreditCard Test MonthField 12 month")
    void shouldSuccessfulBuyByValidElevenMonthMonthFieldCreditCard() {
        tourPage.completeCreditForm(DataHelper.getFirstNumber(), DataHelper.getTwelveMonth(), DataHelper.getYear(1), DataHelper.getEngHolder(), DataHelper.getCVC());
        tourPage.continueClick();
        Assertions.assertAll(
                () -> tourPage.acceptAssertion(),
                () -> tourPage.creditApprovedStatusAssertion(),
                () -> tourPage.creditAcceptCountAssertion(),
                () -> tourPage.orderAcceptCountAssertion()
        );
    }

    @Test
    @DisplayName("10) CreditCard Test MonthField: 01 month")
    void shouldSuccessfulBuyByValidFirstMonthMonthFieldCreditCard() {
        tourPage.completeCreditForm(DataHelper.getFirstNumber(), DataHelper.getFirstMonth(), DataHelper.getYear(1), DataHelper.getEngHolder(), DataHelper.getCVC());
        tourPage.continueClick();
        Assertions.assertAll(
                () -> tourPage.acceptAssertion(),
                () -> tourPage.creditApprovedStatusAssertion(),
                () -> tourPage.creditAcceptCountAssertion(),
                () -> tourPage.orderAcceptCountAssertion()
        );
    }

    @Test
    @DisplayName("11) CreditCard Test MonthField 02 month")
    void shouldSuccessfulBuyByValidSecondMonthMonthFieldCreditCard() {
        tourPage.completeCreditForm(DataHelper.getFirstNumber(), DataHelper.getSecondMonth(), DataHelper.getYear(1), DataHelper.getEngHolder(), DataHelper.getCVC());
        tourPage.continueClick();
        Assertions.assertAll(
                () -> tourPage.acceptAssertion(),
                () -> tourPage.creditApprovedStatusAssertion(),
                () -> tourPage.creditAcceptCountAssertion(),
                () -> tourPage.orderAcceptCountAssertion()
        );
    }

    @Test
    @DisplayName("12) CreditCard Test MonthField: 13 month")
    void shouldFailureBuyByInvalidThirteenMonthMonthFieldCreditCard() {
        tourPage.completeCreditForm(DataHelper.getFirstNumber(), DataHelper.getThirteenMonth(), DataHelper.getYear(1), DataHelper.getEngHolder(), DataHelper.getCVC());
        tourPage.continueClick();
        Assertions.assertAll(
                () -> tourPage.monthFieldPeriodError(),
                () -> tourPage.creditDenialCountAssertion(),
                () -> tourPage.orderDenialCountAssertion()
        );
    }

    @Test
    @DisplayName("13) CreditCard Test MonthField: 00 month")
    void shouldFailureBuyByInvalidZeroMonthMonthFieldCreditCard() {
        tourPage.completeCreditForm(DataHelper.getFirstNumber(), DataHelper.getZeroMonth(), DataHelper.getYear(1), DataHelper.getEngHolder(), DataHelper.getCVC());
        tourPage.continueClick();
        Assertions.assertAll(
                () -> tourPage.monthFieldPeriodError(),
                () -> tourPage.creditDenialCountAssertion(),
                () -> tourPage.orderDenialCountAssertion()
        );
    }

    @Test
    @DisplayName("14) CreditCard Negative Test MonthField: Empty month")
    void shouldFailureBuyByInvalidEmptyMonthMonthFieldCreditCard() {
        tourPage.completeCreditForm(DataHelper.getFirstNumber(), DataHelper.getEmptyMonth(), DataHelper.getYear(1), DataHelper.getEngHolder(), DataHelper.getCVC());
        tourPage.continueClick();
        Assertions.assertAll(
                () -> tourPage.monthFieldFormatError(),
                () -> tourPage.creditDenialCountAssertion(),
                () -> tourPage.orderDenialCountAssertion()
        );
    }

    @Test
    @DisplayName("15) CreditCard Test MonthField: One Symbol month")
    void shouldFailureBuyByInvalidOneSymbolMonthMonthFieldCreditCard() {
        tourPage.completeCreditForm(DataHelper.getFirstNumber(), DataHelper.getOneSymbolMonth(), DataHelper.getYear(1), DataHelper.getEngHolder(), DataHelper.getCVC());
        tourPage.continueClick();
        Assertions.assertAll(
                () -> tourPage.monthFieldFormatError(),
                () -> tourPage.creditDenialCountAssertion(),
                () -> tourPage.orderDenialCountAssertion()
        );
    }

    // Тестирование поля "год"

    @Test
    @DisplayName("16) CreditCard Test YearField Current Year")
    void shouldSuccessfulBuyByValidCurrentYearYearFieldCreditCard() {
        tourPage.completeCreditForm(DataHelper.getFirstNumber(), DataHelper.getMonth(0), DataHelper.getYear(0), DataHelper.getEngHolder(), DataHelper.getCVC());
        tourPage.continueClick();
        Assertions.assertAll(
                () -> tourPage.acceptAssertion(),
                () -> tourPage.creditApprovedStatusAssertion(),
                () -> tourPage.creditAcceptCountAssertion(),
                () -> tourPage.orderAcceptCountAssertion()
        );
    }

    @Test
    @DisplayName("17) CreditCard Test YearField: Current Year Plus One")
    void shouldSuccessfulBuyByValidCurrentYearPlusOneYearFieldCreditCard() {
        tourPage.completeCreditForm(DataHelper.getFirstNumber(), DataHelper.getMonth(0), DataHelper.getYear(1), DataHelper.getEngHolder(), DataHelper.getCVC());
        tourPage.continueClick();
        Assertions.assertAll(
                () -> tourPage.acceptAssertion(),
                () -> tourPage.creditApprovedStatusAssertion(),
                () -> tourPage.creditAcceptCountAssertion(),
                () -> tourPage.orderAcceptCountAssertion()
        );
    }

    @Test
    @DisplayName("18) CreditCard Test YearField Current Year Plus Five")
    void shouldSuccessfulBuyByValidCurrentYearPlusFiveYearFieldCreditCard() {
        tourPage.completeCreditForm(DataHelper.getFirstNumber(), DataHelper.getMonth(0), DataHelper.getYear(5), DataHelper.getEngHolder(), DataHelper.getCVC());
        tourPage.continueClick();
        Assertions.assertAll(
                () -> tourPage.acceptAssertion(),
                () -> tourPage.creditApprovedStatusAssertion(),
                () -> tourPage.creditAcceptCountAssertion(),
                () -> tourPage.orderAcceptCountAssertion()
        );
    }

    @Test
    @DisplayName("19) CreditCard Test YearField: Current Year Plus Four")
    void shouldSuccessfulBuyByValidCurrentYearPlusFourYearFieldCreditCard() {
        tourPage.completeCreditForm(DataHelper.getFirstNumber(), DataHelper.getMonth(0), DataHelper.getYear(4), DataHelper.getEngHolder(), DataHelper.getCVC());
        tourPage.continueClick();
        Assertions.assertAll(
                () -> tourPage.acceptAssertion(),
                () -> tourPage.creditApprovedStatusAssertion(),
                () -> tourPage.creditAcceptCountAssertion(),
                () -> tourPage.orderAcceptCountAssertion()
        );
    }

    @Test
    @DisplayName("20) CreditCard Test YearField: Current Year Minus One")
    void shouldFailureBuyByValidCurrentYearMinusOneYearFieldCreditCard() {
        tourPage.completeCreditForm(DataHelper.getFirstNumber(), DataHelper.getMonth(0), DataHelper.getYear(-1), DataHelper.getEngHolder(), DataHelper.getCVC());
        tourPage.continueClick();
        Assertions.assertAll(
                () -> tourPage.yearFieldMinusPeriodError(),
                () -> tourPage.creditDenialCountAssertion(),
                () -> tourPage.orderDenialCountAssertion()
        );
    }

    @Test
    @DisplayName("21) CreditCard Test YearField: Current Year Plus Six")
    void shouldFailureBuyByValidCurrentYearPlusSixYearFieldCreditCard() {
        tourPage.completeCreditForm(DataHelper.getFirstNumber(), DataHelper.getMonth(0), DataHelper.getYear(6), DataHelper.getEngHolder(), DataHelper.getCVC());
        tourPage.continueClick();
        Assertions.assertAll(
                () -> tourPage.yearFieldPeriodError(),
                () -> tourPage.creditDenialCountAssertion(),
                () -> tourPage.orderDenialCountAssertion()
        );
    }

    @Test
    @DisplayName("22) CreditCard Test YearField: Current Year Empty")
    void shouldFailureBuyByInvalidYearEmptyYearFieldCreditCard() {
        tourPage.completeCreditForm(DataHelper.getFirstNumber(), DataHelper.getMonth(0), DataHelper.getEmptyYear(), DataHelper.getEngHolder(), DataHelper.getCVC());
        tourPage.continueClick();
        Assertions.assertAll(
                () -> tourPage.yearFieldFormatError(),
                () -> tourPage.creditDenialCountAssertion(),
                () -> tourPage.orderDenialCountAssertion()
        );
    }

    @Test
    @DisplayName("23) CreditCard Test YearField: Current Year One Symbol")
    void shouldFailureBuyByInvalidYearOneSymbolYearFieldCreditCard() {
        tourPage.completeCreditForm(DataHelper.getFirstNumber(), DataHelper.getMonth(0), DataHelper.getOneSymbolYear(), DataHelper.getEngHolder(), DataHelper.getCVC());
        tourPage.continueClick();
        Assertions.assertAll(
                () -> tourPage.yearFieldFormatError(),
                () -> tourPage.creditDenialCountAssertion(),
                () -> tourPage.orderDenialCountAssertion()
        );
    }

    // Тестирование поля "Владелец"

    @Test
    @DisplayName("24) CreditCard Test HolderField: Three Symbols")
    void shouldSuccessfulBuyByValidHolderFieldThreeSymbolsCreditCard() {
        tourPage.completeCreditForm(DataHelper.getFirstNumber(), DataHelper.getMonth(0), DataHelper.getYear(0), DataHelper.getChoiceSymbolHolders("???"), DataHelper.getCVC());
        tourPage.continueClick();
        Assertions.assertAll(
                () -> tourPage.acceptAssertion(),
                () -> tourPage.creditApprovedStatusAssertion(),
                () -> tourPage.creditAcceptCountAssertion(),
                () -> tourPage.orderAcceptCountAssertion()
        );
    }

    @Test
    @DisplayName("25) CreditCard Test HolderField: Four Symbols")
    void shouldSuccessfulBuyByValidHolderFieldFourSymbolsCreditCard() {
        tourPage.completeCreditForm(DataHelper.getFirstNumber(), DataHelper.getMonth(0), DataHelper.getYear(0), DataHelper.getChoiceSymbolHolders("????"), DataHelper.getCVC());
        tourPage.continueClick();
        Assertions.assertAll(
                () -> tourPage.acceptAssertion(),
                () -> tourPage.creditApprovedStatusAssertion(),
                () -> tourPage.creditAcceptCountAssertion(),
                () -> tourPage.orderAcceptCountAssertion()
        );
    }

    @Test
    @DisplayName("26) CreditCard Test HolderField: Twenty Symbols")
    void shouldSuccessfulBuyByValidHolderFieldTwentySymbolsCreditCard() {
        tourPage.completeCreditForm(DataHelper.getFirstNumber(), DataHelper.getMonth(0), DataHelper.getYear(0), DataHelper.getChoiceSymbolHolders("????????????????????"), DataHelper.getCVC());
        tourPage.continueClick();
        Assertions.assertAll(
                () -> tourPage.acceptAssertion(),
                () -> tourPage.creditApprovedStatusAssertion(),
                () -> tourPage.creditAcceptCountAssertion(),
                () -> tourPage.orderAcceptCountAssertion()
        );
    }

    @Test
    @DisplayName("27) CreditCard Test HolderField: Nineteen Symbols")
    void shouldSuccessfulBuyByValidHolderFieldNineteenSymbolsCreditCard() {
        tourPage.completeCreditForm(DataHelper.getFirstNumber(), DataHelper.getMonth(0), DataHelper.getYear(0), DataHelper.getChoiceSymbolHolders("???????????????????"), DataHelper.getCVC());
        tourPage.continueClick();
        Assertions.assertAll(
                () -> tourPage.acceptAssertion(),
                () -> tourPage.creditApprovedStatusAssertion(),
                () -> tourPage.creditAcceptCountAssertion(),
                () -> tourPage.orderAcceptCountAssertion()
        );
    }

    @Test
    @DisplayName("28) CreditCard Test HolderField With: SpaceBar")
    void shouldSuccessfulBuyByValidHolderFieldWithSpaceBarCreditCard() {
        tourPage.completeCreditForm(DataHelper.getFirstNumber(), DataHelper.getMonth(0), DataHelper.getYear(0), DataHelper.getChoiceSymbolHolders("??????? ???????"), DataHelper.getCVC());
        tourPage.continueClick();
        Assertions.assertAll(
                () -> tourPage.acceptAssertion(),
                () -> tourPage.creditApprovedStatusAssertion(),
                () -> tourPage.creditAcceptCountAssertion(),
                () -> tourPage.orderAcceptCountAssertion()
        );
    }

    @Test
    @DisplayName("29) CreditCard Test HolderField With: Dash")
    void shouldSuccessfulBuyByValidHolderFieldWithDashCreditCard() {
        tourPage.completeCreditForm(DataHelper.getFirstNumber(), DataHelper.getMonth(0), DataHelper.getYear(0), DataHelper.getChoiceSymbolHolders("???????-???????"), DataHelper.getCVC());
        tourPage.continueClick();
        Assertions.assertAll(
                () -> tourPage.acceptAssertion(),
                () -> tourPage.creditApprovedStatusAssertion(),
                () -> tourPage.creditAcceptCountAssertion(),
                () -> tourPage.orderAcceptCountAssertion()
        );
    }

    @Test
    @DisplayName("30) CreditCard Test HolderField: TwentyOne Symbols")
    void shouldFailureBuyByInvalidHolderFieldTwentyOneSymbolsCreditCard() {
        tourPage.completeCreditForm(DataHelper.getFirstNumber(), DataHelper.getMonth(0), DataHelper.getYear(0), DataHelper.getChoiceSymbolHolders("?????????????????????"), DataHelper.getCVC());
        tourPage.continueClick();
        Assertions.assertAll(
                () -> tourPage.acceptAssertion(),
                () -> tourPage.creditApprovedStatusAssertion(),
                () -> tourPage.creditAcceptCountAssertion(),
                () -> tourPage.orderAcceptCountAssertion()
        );
    }

    @Test
    @DisplayName("31) CreditCard Test HolderField: Two Symbols")
    void shouldFailureBuyByInvalidHolderFieldTwoSymbolsCreditCard() {
        tourPage.completeCreditForm(DataHelper.getFirstNumber(), DataHelper.getMonth(0), DataHelper.getYear(0), DataHelper.getChoiceSymbolHolders("??"), DataHelper.getCVC());
        tourPage.continueClick();
        Assertions.assertAll(
                () -> tourPage.acceptAssertion(),
                () -> tourPage.creditApprovedStatusAssertion(),
                () -> tourPage.creditAcceptCountAssertion(),
                () -> tourPage.orderAcceptCountAssertion()
        );
    }

    @Test
    @DisplayName("32) CreditCard Test HolderField: Empty")
    void shouldFailureBuyByInvalidHolderFieldEmptyCreditCard() {
        tourPage.completeCreditForm(DataHelper.getFirstNumber(), DataHelper.getMonth(0), DataHelper.getYear(0), DataHelper.getEmptyHolder(), DataHelper.getCVC());
        tourPage.continueClick();
        Assertions.assertAll(
                () -> tourPage.holderFieldEmptyError(),
                () -> tourPage.creditDenialCountAssertion(),
                () -> tourPage.orderDenialCountAssertion()
        );
    }

    @Test
    @DisplayName("33) CreditCard Test HolderField: SpecialCharacters")
    void shouldFailureBuyByInvalidHolderFieldSpecialCharactersCreditCard() {
        tourPage.completeCreditForm(DataHelper.getFirstNumber(), DataHelper.getMonth(0), DataHelper.getYear(0), DataHelper.getSpecialCharactersHolder(), DataHelper.getCVC());
        tourPage.continueClick();
        Assertions.assertAll(
                () -> tourPage.holderFieldEmptyError(),
                () -> tourPage.creditDenialCountAssertion(),
                () -> tourPage.orderDenialCountAssertion()
        );
    }

    @Test
    @DisplayName("34) CreditCard Test HolderField: Spacebars")
    void shouldFailureBuyByInvalidHolderFieldSpacebarsCreditCard() {
        tourPage.completeCreditForm(DataHelper.getFirstNumber(), DataHelper.getMonth(0), DataHelper.getYear(0), DataHelper.getSpacesHolders(), DataHelper.getCVC());
        tourPage.continueClick();
        Assertions.assertAll(
                () -> tourPage.holderFieldEmptyError(),
                () -> tourPage.creditDenialCountAssertion(),
                () -> tourPage.orderDenialCountAssertion()
        );
    }

    @Test
    @DisplayName("35) CreditCard Test HolderField: Numbers")
    void shouldFailureBuyByInvalidHolderFieldNumbersCreditCard() {
        tourPage.completeCreditForm(DataHelper.getFirstNumber(), DataHelper.getMonth(0), DataHelper.getYear(0), DataHelper.getNumerifyHolder("########"), DataHelper.getCVC());
        tourPage.continueClick();
        Assertions.assertAll(
                () -> tourPage.holderFieldFormatError(),
                () -> tourPage.creditDenialCountAssertion(),
                () -> tourPage.orderDenialCountAssertion()
        );
    }

    @Test
    @DisplayName("36) CreditCard Test HolderField: RU")
    void shouldFailureBuyByInvalidHolderFieldRUCreditCard() {
        tourPage.completeCreditForm(DataHelper.getFirstNumber(), DataHelper.getMonth(0), DataHelper.getYear(0), DataHelper.getRuHolder(), DataHelper.getCVC());
        tourPage.continueClick();
        Assertions.assertAll(
                () -> tourPage.acceptAssertion(),
                () -> tourPage.creditApprovedStatusAssertion(),
                () -> tourPage.creditAcceptCountAssertion(),
                () -> tourPage.orderAcceptCountAssertion()
        );
    }

    @Test
    @DisplayName("37) CreditCard Test HolderField: Start And Finish Spacebars")
    void shouldFailureBuyByInvalidHolderFieldStartAndFinishSpacebarsCreditCard() {
        tourPage.completeCreditForm(DataHelper.getFirstNumber(), DataHelper.getMonth(0), DataHelper.getYear(0), DataHelper.getChoiceSymbolHolders("  ??????   ????  "), DataHelper.getCVC());
        tourPage.continueClick();
        Assertions.assertAll(
                () -> tourPage.holderFieldFormatError(),
                () -> tourPage.creditDenialCountAssertion(),
                () -> tourPage.orderDenialCountAssertion()
        );
    }

    @Test
    @DisplayName("38) CreditCard Test HolderField With Lower Case")
    void shouldFailureBuyByInvalidHolderFieldWithLowerCaseCreditCard() {
        tourPage.completeCreditForm(DataHelper.getFirstNumber(), DataHelper.getMonth(0), DataHelper.getYear(0), DataHelper.getEngHolderLowerCase(), DataHelper.getCVC());
        tourPage.continueClick();
        Assertions.assertAll(
                () -> tourPage.acceptAssertion(),
                () -> tourPage.creditApprovedStatusAssertion(),
                () -> tourPage.creditAcceptCountAssertion(),
                () -> tourPage.orderAcceptCountAssertion()
        );
    }

    // Тестирование поля "CVC"

    @Test
    @DisplayName("39) CreditCard Test CVCField: Three Symbols")
    void shouldSuccessfulBuyByValidCVCFieldThreeSymbolsCreditCard() {
        tourPage.completeCreditForm(DataHelper.getFirstNumber(), DataHelper.getMonth(0), DataHelper.getYear(0), DataHelper.getEngHolder(), DataHelper.getCVC());
        tourPage.continueClick();
        Assertions.assertAll(
                () -> tourPage.acceptAssertion(),
                () -> tourPage.creditApprovedStatusAssertion(),
                () -> tourPage.creditAcceptCountAssertion(),
                () -> tourPage.orderAcceptCountAssertion()
        );
    }


    @Test
    @DisplayName("40) CreditCard Test CVCField One Symbols")
    void shouldFailureBuyByInvalidCVCFieldOneSymbolsCreditCard() {
        tourPage.completeCreditForm(DataHelper.getFirstNumber(), DataHelper.getMonth(0), DataHelper.getYear(0), DataHelper.getEngHolder(), DataHelper.getOneSymbolCVC());
        tourPage.continueClick();
        Assertions.assertAll(
                () -> tourPage.CVCFieldFormatError(),
                () -> tourPage.creditDenialCountAssertion(),
                () -> tourPage.orderDenialCountAssertion()
        );
    }

    @Test
    @DisplayName("41) CreditCard Test CVCField: Two Symbols")
    void shouldFailureBuyByInvalidCVCFieldTwoSymbolsCreditCard() {
        tourPage.completeCreditForm(DataHelper.getFirstNumber(), DataHelper.getMonth(0), DataHelper.getYear(0), DataHelper.getEngHolder(), DataHelper.getTwoSymbolCVC());
        tourPage.continueClick();
        Assertions.assertAll(
                () -> tourPage.CVCFieldFormatError(),
                () -> tourPage.creditDenialCountAssertion(),
                () -> tourPage.orderDenialCountAssertion()
        );
    }

    @Test
    @DisplayName("42) CreditCard Test CVCField: Empty")
    void shouldFailureBuyByInvalidCVCFieldEmptyCreditCard() {
        tourPage.completeCreditForm(DataHelper.getFirstNumber(), DataHelper.getMonth(0), DataHelper.getYear(0), DataHelper.getEngHolder(), DataHelper.getEmptyCVC());
        tourPage.continueClick();
        Assertions.assertAll(
                () -> tourPage.CVCFieldFormatError(),
                () -> tourPage.creditDenialCountAssertion(),
                () -> tourPage.orderDenialCountAssertion()
        );
    }

    @Test
    @DisplayName("43) CreditCard Test CVCField Zero")
    void shouldFailureBuyByInvalidCVCFieldZeroCreditCard() {
        tourPage.completeCreditForm(DataHelper.getFirstNumber(), DataHelper.getMonth(0), DataHelper.getYear(0), DataHelper.getEngHolder(), DataHelper.getZeroSymbolCVC());
        tourPage.continueClick();
        Assertions.assertAll(
                () -> tourPage.acceptAssertion(),
                () -> tourPage.creditApprovedStatusAssertion(),
                () -> tourPage.creditAcceptCountAssertion(),
                () -> tourPage.orderAcceptCountAssertion()
        );
    }
}
