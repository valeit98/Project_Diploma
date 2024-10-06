package ru.netology.diploma.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class DataHelp {

    private DataHelp() {
    }

    @Value
    public static class UserCardInfo {
        String number;
        String month;
        String year;
        String name;
        String securityCodes;
    }

    private static String generateDate(int shift, String pattern) {
        return LocalDate.now().plusDays(shift).format(DateTimeFormatter.ofPattern(pattern));
    }

    private static String generateName() {
        Faker faker = new Faker(new Locale("en"));
        return faker.name().firstName() + " " + faker.name().lastName();
    }

    private static String generateSecurityCode() {
        Faker faker = new Faker(new Locale("en"));
        return String.valueOf(faker.number().numberBetween(100, 999));
    }

    private static Map<String, UserCardInfo> tests = new HashMap<>();

    public static void creatingTestDataMap() {
        tests.put("scenario_01", new UserCardInfo("4444 4444 4444 4441", generateDate(0, "MM"), generateDate(0, "yy"), "Marta Gromovay", "587"));
        tests.put("scenario_02", new UserCardInfo("4444 4444 4444 4441", generateDate(0, "MM"), generateDate(365 * 5, "yy"), "Marta Gromova", "490"));
        tests.put("scenario_03", new UserCardInfo("4444 4444 4444 4441", generateDate(0, "MM"), generateDate(0, "yy"), "Mar Gro", "254"));

        tests.put("scenario_04", new UserCardInfo("4444 4444 4444 4442", generateDate(30 * 3, "MM"), generateDate(365 * 2, "yy"), generateName(), generateSecurityCode()));
        tests.put("scenario_05", new UserCardInfo(" ", generateDate(30 * 3, "MM"), generateDate(365 * 2, "yy"), generateName(), generateSecurityCode()));
        tests.put("scenario_06", new UserCardInfo("0000 0000 0000 0000", generateDate(30 * 3, "MM"), generateDate(365 * 2, "yy"), generateName(), generateSecurityCode()));
    }

    public static UserCardInfo generateUser(String scenario) {
        return tests.get(scenario);
    }

    @Value
    public static class ExpectedData {
        String newRowsDB;
        String price;
        String status;
    }

    public static ExpectedData validDataToBay() {
        return new ExpectedData("011", "45000", "APPROVED");
    }

    public static ExpectedData validDataOnCredit() {
        return new ExpectedData("111", "45000", "APPROVED");
    }

    public static ExpectedData notValidDataToBay() {
        return new ExpectedData("011", "0", "DECLINED");
    }

    public static ExpectedData notValidDataOnCredit() {
        return new ExpectedData("101", "", "DECLINED");
    }

    public static ExpectedData notValidInfo() {
        return new ExpectedData("000", "", "");
    }
}
