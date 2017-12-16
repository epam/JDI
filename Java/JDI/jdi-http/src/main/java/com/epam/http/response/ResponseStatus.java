package com.epam.http.response;

import com.epam.commons.DataClass;
import io.restassured.response.Response;

/**
 * Created by Roman_Iovlev on 10/22/2017.
 */
public class ResponseStatus extends DataClass<ResponseStatus> {
    public final ResponseStatusType type;
    public final String text;
    public final int code;

    public ResponseStatus(Response response) {
        code = response.statusCode();
        type = ResponseStatusType.getStatusTypeFromCode(code);
        text = response.statusLine().substring(13);
    }
}
