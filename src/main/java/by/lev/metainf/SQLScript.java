package by.lev.metainf;

import lombok.Getter;

@Getter
public enum SQLScript {
    USER_REPOSITORY_READ("SELECT * FROM Users WHERE userId = "),
    USER_REPOSITORY_READ_ALL("SELECT * FROM Users"),
    USER_REPOSITORY_CREATE("INSERT INTO Users (name, address) VALUES (?, ?)"),
    USER_REPOSITORY_UPDATE("UPDATE Users SET name = ?, address = ? WHERE userId = ?"),
    USER_REPOSITORY_DELETE("DELETE FROM Users WHERE userId = ?"),
    USER_REPOSITORY_DELETE_BY_NAME("DELETE FROM Users WHERE name = ?"),

    ACCOUNT_REPOSITORY_READ("SELECT * FROM Accounts WHERE accountId = "),
    ACCOUNT_REPOSITORY_READ_ALL("SELECT * FROM Accounts"),
    ACCOUNT_REPOSITORY_CREATE("INSERT INTO Accounts (userId, balance, currency) VALUES (?, ?, ?)"),
    ACCOUNT_REPOSITORY_UPDATE("UPDATE Accounts SET balance = ? WHERE accountId = ?"),
    ACCOUNT_REPOSITORY_DELETE("DELETE FROM Accounts WHERE accountId = ?"),

    TRANSACTION_REPOSITORY_READ("SELECT * FROM Transactions WHERE transactionId = "),
    TRANSACTION_REPOSITORY_READ_BY_ACCOUNT_ID("SELECT * FROM Transactions WHERE accountId = "),
    TRANSACTION_REPOSITORY_READ_ALL("SELECT * FROM Transactions"),
    TRANSACTION_REPOSITORY_CREATE("INSERT INTO Transactions (accountId, amount) VALUES (?, ?)");

    private final String value;

    SQLScript(String value) {
        this.value = value;
    }
}
