package ru.komarov.crudrest.constant;

public class Constant {

    public final static String NOT_EMPTY = "Should not be empty";
    public final static String LENGTH_OF_NAME_CONSTRAINT = "Should be contain from 2 to 15 characters";
    public final static String DATE_PATTERN = "yyyy-MM-dd";
    public final static String MAX_AGE_CONSTRAINT = "The engineer must be under 65 years of age";
    public final static String MIN_AGE_CONSTRAINT = "The engineer must be over 18 years old";
    public final static String CAR_AVAILABILITY_CONSTRAINT = "The presence of a car must be indicated";
    public final static int MAX_ENGINEER_AGE = 65*12;
    public final static int MIN_ENGINEER_AGE = 18*12;
    public final static String ADDRESS_CONSTRAINT = "The address must be specified";
    public final static String CONTACT_CONSTRAINT = "The contact person must be specified";
    public final static String PHONE_CONSTRAINT = "The phone number must be specified";
    public final static String PHONE_PATTERN =  "\\d{3}-\\d{3}-\\d{4}";
    public final static String PHONE_PATTERN_CONSTRAINT =  "Incorrect number format";
    public final static String REQUEST_DATE_CONSTRAINT =  "The date of the repair request should not be earlier than a month ago";
    public final static String NOT_FOUND = "Request On Repair not found";
    public static final String CREATED = "Request on repair created";
}
