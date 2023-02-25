package by.lev.metainf;

import lombok.Getter;

@Getter
public enum SQLScript {
    UR_READ("SELECT * FROM Users WHERE userId = "),
    UR_READ_BY_NAME("SELECT * FROM Users WHERE name = '"),
    UR_READ_ALL("SELECT * FROM Users"),
    UR_READ_NAMES("SELECT name FROM Users"),
    UR_CREATE("INSERT INTO Users (name, address) VALUES (?, ?)"),
    UR_UPDATE("UPDATE Users SET address = ? WHERE userId = ?"),
    UR_DELETE("DELETE FROM Users WHERE userId = ?"),
    UR_DELETE_BY_NAME("DELETE FROM Users WHERE name = ?"),

    AR_READ("SELECT * FROM Accounts WHERE accountId = "),
    AR_READ_BY_USER_ID("SELECT * FROM Accounts WHERE userId = "),
    AR_READ_ALL("SELECT * FROM Accounts"),
    AR_CREATE("INSERT INTO Accounts (userId, balance, currency) VALUES (?, ?, ?)"),
    AR_UPDATE("UPDATE Accounts SET balance = ? WHERE accountId = ?"),
    AR_DELETE("DELETE FROM Accounts WHERE accountId = ?"),
    AR_DELETE_ACCOUNTS_BY_USER_ID("DELETE FROM Accounts WHERE userId = ?"),

    TR_READ("SELECT * FROM Transactions WHERE transactionId = "),
    TR_READ_BY_ACCOUNT_ID("SELECT * FROM Transactions WHERE accountId = "),
    TR_READ_ALL("SELECT * FROM Transactions"),
    TR_CREATE("INSERT INTO Transactions (accountId, amount) VALUES (?, ?)");

    private final String script;

    SQLScript(String script) {
        this.script = script;
    }
}
