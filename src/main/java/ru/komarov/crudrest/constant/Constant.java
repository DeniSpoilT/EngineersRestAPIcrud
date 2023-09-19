package ru.komarov.crudrest.constant;

public final class Constant {

    public final static String NAME_NOT_EMPTY = "Name should not be empty";
    public final static String LASTNAME_NOT_EMPTY = "Last name should not be empty";
    public final static String LENGTH_OF_NAME_CONSTRAINT = "Should be contain from 2 to 15 characters";
    public final static String DATE_PATTERN = "yyyy-MM-dd";
    public final static String MAX_AGE_CONSTRAINT = "The engineer must be under 65 years of age";
    public final static String MIN_AGE_CONSTRAINT = "The engineer must be over 18 years old";
    public final static String CAR_AVAILABILITY_CONSTRAINT = "The presence of a car must be indicated";
    public final static int MAX_ENGINEER_AGE = 65*12; // 65 years old in months
    public final static int MIN_ENGINEER_AGE = 18*12; // 18 years old in months
    public final static String ADDRESS_CONSTRAINT = "The address must be specified";
    public final static String CONTACT_CONSTRAINT = "The contact person must be specified";
    public final static String PHONE_CONSTRAINT = "The phone number must be specified";
    public final static String PHONE_PATTERN =  "\\d{3}-\\d{3}-\\d{4}";
    public final static String PHONE_PATTERN_CONSTRAINT =  "Incorrect number format";
    public final static String REQUEST_MIN_DATE_CONSTRAINT =  "The date of the repair request should not be earlier " +
            "than a month ago";
    public final static String REQUEST_MAX_DATE_CONSTRAINT =  "The date of the repair request should not be" +
            " in the future";
    public final static String REQUEST_ON_REPAIR_NOT_FOUND = "Request On Repair not found";
    public final static String INVALID_OPERATION = "Invalid operation";
    public static final String ENGINEER_NOT_FOUND = "Engineer not found";
    public static final String REQUEST_ON_REPAIR_CREATED = "Request on repair created";
    public static final Object REQUEST_ON_REPAIR_UPDATED = "Request on repair updated";
    public static final String ENGINEER_CREATED = "Engineer created";
    public static final String ENGINEER_UPDATED = "Engineer updated";
    public static final String ADDRESS_LENGTH_CONSTRAINT = "The length of the address must be from 5 to 100 characters";
    public static final String CONTACT_LENGTH_CONSTRAINT = "The length of the contact person's name should be " +
            "from 2 to 30 characters";

    private Constant() {
    }
}
