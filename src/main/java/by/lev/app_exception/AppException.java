package by.lev.app_exception;

import lombok.Getter;


public class AppException extends Exception{

    private AppError error;

    public AppException(AppError error, Throwable cause) {
        super(cause);
        this.error = error;
    }

    public AppError getError() {
        System.err.println(error);
        return error;
    }
}
