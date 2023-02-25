package by.lev.app_exception;

import lombok.Getter;

@Getter
public enum AppError {
    USER_REPOSITORY_ERROR_01("create method"),
    USER_REPOSITORY_ERROR_02("read method"),
    USER_REPOSITORY_ERROR_03("readByUserName method"),
    USER_REPOSITORY_ERROR_04("readAll method"),
    USER_REPOSITORY_ERROR_05("update method"),
    USER_REPOSITORY_ERROR_06("deleteByUserId method"),
    USER_REPOSITORY_ERROR_07("deleteByUserName method"),

    ACCOUNT_REPOSITORY_ERROR_01("create method"),
    ACCOUNT_REPOSITORY_ERROR_02("readByAccountId method"),
    ACCOUNT_REPOSITORY_ERROR_03("readAccountsByUserId method"),
    ACCOUNT_REPOSITORY_ERROR_04("readAll method"),
    ACCOUNT_REPOSITORY_ERROR_05("update method"),
    ACCOUNT_REPOSITORY_ERROR_06("deleteUserAccountsByUserId method"),
    ACCOUNT_REPOSITORY_ERROR_07("deleteByAccountId method"),

    TRANSACTION_REPOSITORY_ERROR_01("create method"),
    TRANSACTION_REPOSITORY_ERROR_02("read method"),
    TRANSACTION_REPOSITORY_ERROR_03("readAllByAccountId method"),
    TRANSACTION_REPOSITORY_ERROR_04("readAll method"),

    TEST_ERROR("");

    private String errorLocation;

    AppError(String errorLocation) {
        this.errorLocation = errorLocation;
    }
}
