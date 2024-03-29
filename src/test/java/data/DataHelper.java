package data;

import com.github.javafaker.Faker;
import com.github.javafaker.service.RandomService;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataHelper {

    @Value
    public static class Card {
        String number;
        String month;
        String year;
        String holder;
        String cvc;
    }

    private static final Faker fakerEng = new Faker(Locale.ENGLISH, new RandomService());
    private static final Faker fakerRu = new Faker(new Locale("ru", "RU"), new RandomService());

    public static String getFirstNumber() {
        return "4444 4444 4444 4441";
    }

    public static String getSecondNumber() {
        return "4444 4444 4444 4442";
    }

    public static String getRandomNumber() {
        return fakerEng.numerify("#### #### #### ####");
    }

    public static String getFifteenRandomNumber() {
        return fakerEng.numerify("#### #### #### ###");
    }

    public static String getOneRandomNumber() {
        return fakerEng.numerify("#");
    }

    public static String getEmptyNumber() {
        return "";
    }

    public static String getMonth(int plusMonth) {
        return LocalDate.now().plusMonths(plusMonth).format(DateTimeFormatter.ofPattern("MM"));
    }

    public static String getThirteenMonth() {
        return "13";
    }

    public static String getTwelveMonth() {
        return "12";
    }

    public static String getElevenMonth() {
        return "11";
    }

    public static String getFirstMonth() {
        return "01";
    }

    public static String getSecondMonth() {
        return "02";
    }

    public static String getZeroMonth() {
        return "00";
    }

    public static String getOneSymbolMonth() {
        return "4";
    }

    public static String getEmptyMonth() {
        return "";
    }

    public static String getYear(int plusYears) {
        return LocalDate.now().plusYears(plusYears).format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String getEmptyYear() {
        return "";
    }

    public static String getOneSymbolYear() {
        return "1";
    }

    public static String getEngHolder() {
        return fakerEng.name().name().toUpperCase();
    }

    public static String getChoiceSymbolHolders(String countSymbols) {
        return fakerEng.letterify(countSymbols).toUpperCase();
    }

    public static String getEmptyHolder() {
        return "";
    }

    public static String getSpacesHolders() {
        return "    ";
    }

    public static String getSpecialCharactersHolder() {
        return "!~@#$%^&*(,.)+-/' '?";
    }

    public static String getNumerifyHolder(String countSymbols) {
        return fakerEng.numerify(countSymbols);
    }

    public static String getEngHolderLowerCase() {
        return fakerEng.name().name().toLowerCase();
    }

    public static String getRuHolder() {
        return fakerRu.name().name().toUpperCase();
    }

    public static String getCVC() {
        return fakerEng.numerify("###");
    }

    public static String getTwoSymbolCVC() {
        return fakerEng.numerify("##");
    }

    public static String getOneSymbolCVC() {
        return fakerEng.numerify("#");
    }

    public static String getZeroSymbolCVC() {
        return "000";
    }

    public static String getEmptyCVC() {
        return "";
    }

}