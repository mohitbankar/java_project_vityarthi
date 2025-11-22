package library;

import java.util.*;

public class TransactionManager {
    private Map<String, Transaction> transactions = new HashMap<>();

    public void addTransaction(Transaction transaction) {
        transactions.put(transaction.getTransactionId(), transaction);
    }

    public Transaction getTransaction(String transactionId) {
        return transactions.get(transactionId);
    }

    public List<Transaction> getUserTransactions(String userId) {
        List<Transaction> result = new ArrayList<>();
        for (Transaction t : transactions.values()) {
            if (t.getUserId().equals(userId)) {
                result.add(t);
            }
        }
        return result;
    }

    public List<Transaction> getBookTransactions(String bookId) {
        List<Transaction> result = new ArrayList<>();
        for (Transaction t : transactions.values()) {
            if (t.getBookId().equals(bookId)) {
                result.add(t);
            }
        }
        return result;
    }

    public List<Transaction> getAllTransactions() {
        return new ArrayList<>(transactions.values());
    }
}
