package main.java.task4;

import com.github.javafaker.Faker;
import lombok.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.Random;
import java.util.function.Predicate;
import java.util.regex.Pattern;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RegistrationForm {
    private static final String NULL_OR_EMPTY_TEMPLATE_ERROR = "%s cannot be null or empty.";

    private static final String BIRTHDAY_FORMAT_ERROR = "Invalid birth date format. Please use yyyy-MM-dd.";
    private static final String BIRTHDAY_FUTURE_ERROR = "Birth date cannot be in the future";

    public static final int USERNAME_LENGTH = 8;
    private static final String USERNAME_ONLY_LETTERS_ERROR = "Username must contain only letters from the Latin alphabet.";
    private static final Predicate<String> USERNAME_ONLY_LETTERS_PREDICATE = text -> Pattern.matches("[A-Za-z]+", text);

    public static final int EMAIL_MIN_LENGTH = 5;
    private static final String EMAIL_LOW_LENGTH_ERROR = "Email must be at least 5 characters long.";
    private static final String EMAIL_PATTERN_ERROR = "Email must contain '@' and '.', with no spaces and '.' must follow '@'.";
    private static final Predicate<String> EMAIL_PATTERN_PREDICATE = text -> Pattern.matches("\\w+@\\w+\\.\\w+", text);

    public static final int PASSWORD_LENGTH = 10;  //default value for cases provided pass length is out of required borders;
    private static final int MIN_PASSWORD_LENGTH = 8;
    private static final int MAX_PASSWORD_LENGTH = 16;
    private static final String PASSWORD_LOW_LENGTH_ERROR = "Password must be at least 8 characters long.";
    private static final String PASSWORD_UPPER_CASE_ERROR = "Password must include at least one uppercase letter.";
    private static final Predicate<String> PASSWORD_UPPER_CASE_PREDICATE = s -> s.matches(".*[A-Z].*");

    private static final String PASSWORD_LOWER_CASE_ERROR = "Password must include at least one lowercase letter.";
    private static final Predicate<String> PASSWORD_LOWER_CASE_PREDICATE = s -> s.matches(".*[a-z].*");

    private static final String PASSWORD_NUMBER_ERROR = "Password must include at least one number.";
    private static final Predicate<String> PASSWORD_NUMBER_PREDICATE = s -> s.matches(".*[0-9].*");

    private static final String PASSWORD_SPEC_CHAR_ERROR = "Password must include at least one special character.";
    private static final Predicate<String> PASSWORD_SPEC_CHAR_PREDICATE = s -> s.matches(".*[!@#$%^&*(),.?\"'~:;{}|<>_+-].*");

    private static final Map<String, String> CHAR_CATEGORIES = Map.of(
            "UPPERCASE", "ABCDEFGHIJKLMNOPQRSTUVWXYZ",
            "LOWERCASE", "abcdefghijklmnopqrstuvwxyz",
            "DIGITS", "0123456789",
            "SPECIAL", "!@#$%^&*(),.?\":{}|<>");

    private String username;
    private String email;
    private String password;
    private String confirmPassword;
    private String phoneNumber;
    private String birthDate;

    public RegistrationForm(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.confirmPassword = password;
        this.phoneNumber = "";
        this.birthDate = "";
    }

    public static RegistrationForm createRandomUser() {
        RegistrationForm randomUser = new RegistrationForm();
        randomUser.setUsername(randomUser.generateUsername());
        randomUser.setPassword(randomUser.generatePassword(PASSWORD_LENGTH));
        randomUser.setEmail(randomUser.generateEmail());
        System.out.println(randomUser.getPassword());
        return randomUser;
    }

    public String generateUsername() {
        Faker faker = new Faker();
        return faker.letterify("?".repeat(USERNAME_LENGTH));
    }

    public String generateEmail() {
        Faker faker = new Faker();
        email = faker.internet().emailAddress();
        if (email.length() < EMAIL_MIN_LENGTH) {
            email = "test_" + email;
        }
        return faker.internet().emailAddress();
    }


    public String generatePassword(int passwordLength) {
        int validatedPasswordLength = passwordLength < MIN_PASSWORD_LENGTH || passwordLength > MAX_PASSWORD_LENGTH
                ? PASSWORD_LENGTH : passwordLength; //validate length and set default if wrong length provided

        Faker faker = new Faker();
        //create random string minus number equivalents of checks
        String randomBase = faker.letterify("?".repeat(validatedPasswordLength - CHAR_CATEGORIES.size()));

        StringBuilder password = new StringBuilder(randomBase);
        Random random = new Random();
        //fill in the missing number of characters required by the security check rules
        for (String charSet : CHAR_CATEGORIES.values()) {
            char randomChar = charSet.charAt(random.nextInt(charSet.length()));
            int insertPosition = random.nextInt(password.length() + 1);
            password.insert(insertPosition, randomChar);
        }

        return password.toString();
    }

    public int calculateAge() {
        validateOrThrow(birthDate, this::isNotNullOrEmpty, String.format(NULL_OR_EMPTY_TEMPLATE_ERROR, "Birth date"));
        validateBirthDateFormat();

        LocalDate birthDateParsed = LocalDate.parse(birthDate);
        validateOrThrow(birthDateParsed, date -> !date.isAfter(LocalDate.now()), BIRTHDAY_FUTURE_ERROR);

        return (int) ChronoUnit.YEARS.between(birthDateParsed, LocalDate.now());
    }

    public void displayUserInfo() {
        System.out.println("Username: " + username);
        System.out.println("Email: " + email);
        System.out.println("Age: " + calculateAge());
    }

    public String validateUsername() {
        String result = "";
        try {
            validateOrThrow(username, this::isNotNullOrEmpty, String.format(NULL_OR_EMPTY_TEMPLATE_ERROR, "Username"));
            validateOrThrow(username, USERNAME_ONLY_LETTERS_PREDICATE, USERNAME_ONLY_LETTERS_ERROR);
        } catch (IllegalArgumentException er) {
            result = er.getMessage();
        }
        return result;
    }

    public String validateEmail() {
        String result = "";
        try {
            validateOrThrow(email, this::isNotNullOrEmpty, String.format(NULL_OR_EMPTY_TEMPLATE_ERROR, "Email"));
            validateOrThrow(email, s -> s.length() >= 5, EMAIL_LOW_LENGTH_ERROR);
            validateOrThrow(email, EMAIL_PATTERN_PREDICATE, EMAIL_PATTERN_ERROR);
        } catch (IllegalArgumentException er) {
            result = er.getMessage();
        }
        return result;
    }

    public String validatePassword() {
        String result = "";
        try {
            validateOrThrow(password, this::isNotNullOrEmpty, PASSWORD_LOW_LENGTH_ERROR);
            validateOrThrow(password, s -> s.length() >= MIN_PASSWORD_LENGTH, PASSWORD_LOW_LENGTH_ERROR);
            validateOrThrow(password, PASSWORD_NUMBER_PREDICATE, PASSWORD_NUMBER_ERROR);
            validateOrThrow(password, PASSWORD_UPPER_CASE_PREDICATE, PASSWORD_UPPER_CASE_ERROR);
            validateOrThrow(password, PASSWORD_LOWER_CASE_PREDICATE, PASSWORD_LOWER_CASE_ERROR);
            validateOrThrow(password, PASSWORD_SPEC_CHAR_PREDICATE, PASSWORD_SPEC_CHAR_ERROR);
        } catch (IllegalArgumentException er) {
            result = er.getMessage();
        }
        return result;
    }

    /**
     * Validates the given input matches the provided condition.
     * Throws an exception if the condition is not met.
     */
    private <T> void validateOrThrow(T input, Predicate<T> condition, String errorMessage) {
        if (!condition.test(input)) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    private boolean isNotNullOrEmpty(String value) {
        return value != null && !value.isBlank();
    }

    private void validateBirthDateFormat() {
        try {
            LocalDate.parse(birthDate);
        } catch (DateTimeParseException e) {
            throw new DateTimeParseException(BIRTHDAY_FORMAT_ERROR,
                    e.getMessage(), e.getErrorIndex());
        }
    }
}