package com.team.retrofitandrxjava.http;

import com.team.retrofitandrxjava.exception.CustomException;

import retrofit.RetrofitError;

/**
 * Created by leejonghun on 15. 7. 28.
 */
public class ErrorHandler {

    public static CustomException errorHandling(final RetrofitError retrofitError) {
        RetrofitError.Kind kind = retrofitError.getKind();

        if (kind == RetrofitError.Kind.UNEXPECTED) {
            return new CustomException("An internal error occurred while attempting to execute a request.");
        }
        else if (kind == RetrofitError.Kind.HTTP) {
            switch (retrofitError.getResponse().getStatus()) {
                case 400:
                    return new CustomException("Bad Request");
                case 401:
                    return new CustomException("Unauthorized");
                case 404:
                    return new CustomException("Not Found Server");
                case 500:
                    return new CustomException("Internal Server Error");
            }
        }
        else if (kind == RetrofitError.Kind.CONVERSION) {
            return new CustomException("An exception was thrown while (de)serializing a body.");
        }
        return null;
    }
}
