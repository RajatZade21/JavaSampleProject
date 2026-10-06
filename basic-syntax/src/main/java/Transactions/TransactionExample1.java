package Transactions;

import org.springframework.transaction.annotation.Transactional;

public class TransactionExample1 {


    @Transactional
    public void createUser() {
        repository.save(user);
        throw new RuntimeException("Something failed");
    }
    //User will NOT be committed to the database
    //RuntimeException is an unchecked exception, and by default Spring rolls back the transaction for unchecked exceptions.
    //Even if Hibernate/JPA sends the INSERT to the database before the method finishes, the transaction hasn't committed yet:
    //The transaction is committed when the @Transactional method completes successfully.


    @Transactional
    public void createUser() throws Exception {
        repository.save(user);
        throw new Exception("Something failed");
    }
    //By default, Spring does not roll back for checked exceptions.
    //You can explicitly configure rollback: @Transactional(rollbackFor = Exception.class)
}
