package com.epam.http.response;

import java.util.List;

import static com.epam.commons.EnumUtils.getAllEnumValues;
import static com.epam.commons.LinqUtils.first;

/**
 * Created by Roman_Iovlev on 12/19/2016.
 */
public enum ResponseStatusType {
    OK(2), REDIRECT(3), ERROR(4), SERVER_ERROR(5);

    public int firstNumber;
    public static List<ResponseStatusType> allValues() {
        return getAllEnumValues(ResponseStatusType.class);
    }
    ResponseStatusType(int num) { firstNumber = num; }
    public static ResponseStatusType getStatusTypeFromCode(int code) {
        int firstNum = code/100;
        return first(allValues(), type -> type.firstNumber == firstNum);
    }
}
