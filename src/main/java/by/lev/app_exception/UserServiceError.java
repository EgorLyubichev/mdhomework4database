package by.lev.app_exception;

import lombok.Getter;

@Getter
public enum UserServiceError {
    ERROR_US_01("Such a name already exists!");

    private String message;

    UserServiceError(String message) {
        this.message = message;
    }
}
