package maiordev.expense_tracker_api.util;

public class StaticConstants {

    static final int OK_CODE = 0;
    static final int ERROR_CODE = 1;
    static final int WARNING_CODE = 2;


    public static final String REQUEST_MATCHES_EXPENSE = "/expense/**";
    public static final String REQUEST_MATCHES_USER = "/user/**";

    public static final String ERROR_INVALID_CREDENTIALS = "Invalid Credentials";
    public static final String ERROR_USER_NOT_FOUND = "User not found";

    public static final String OK_MESSAGE_REGISTER_USER = "User registered successfully";
    public static final String OK_MESSAGE_LOGIN_USER = "User logged in successfully";
    public static final String OK_MESSAGE_ADD_EXPENSE = "Expense added successfully";
    public static final String OK_MESSAGE_GET_EXPENSE = "Expense retrieved successfully";
    public static final String OK_MESSAGE_DELETE_EXPENSE = "Expense deleted successfully";
    public static final String OK_MESSAGE_UPDATE_EXPENSE = "Expense updated successfully";
}
