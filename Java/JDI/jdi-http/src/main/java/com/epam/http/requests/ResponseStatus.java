package com.epam.http.requests;

import com.epam.commons.DataClass;
import io.restassured.response.Response;

import static com.epam.http.requests.ResponseStatusType.getStatusTypeFromCode;

/**
 * Created by Roman_Iovlev on 10/22/2017.
 */
public class ResponseStatus extends DataClass<ResponseStatus> {
    public final ResponseStatusType type;
    public final String text;
    public final int code;

    public ResponseStatus(Response response) {
        code = response.statusCode();
        type = getStatusTypeFromCode(code);
        text = response.statusLine();
    }
}
