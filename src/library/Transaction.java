package library;

import java.util.Date;

public class Transaction {
    private String transactionId;
    private String userId;
    private String bookId;
    private Date issueDate;
    private Date returnDate;
    private boolean returned;

    public Transaction(String transactionId, String userId, String bookId, Date issueDate) {
        this.transactionId = transactionId;
        this.userId = userId;
        this.bookId = bookId;
        this.issueDate = issueDate;
        this.returned = false;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getUserId() {
        return userId;
    }

    public String getBookId() {
        return bookId;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public boolean isReturned() {
        return returned;
    }

    public void setReturned(Date returnDate) {
        this.returned = true;
        this.returnDate = returnDate;
    }
}
