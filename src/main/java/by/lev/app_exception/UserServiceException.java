package by.lev.app_exception;

import lombok.Getter;

@Getter
public class UserServiceException extends Exception{

    private UserServiceError error;
    private String message;

    public UserServiceException(Throwable cause, UserServiceError error, String message) {
        super(cause);
        this.error = error;
        this.message = message;
    }
}
