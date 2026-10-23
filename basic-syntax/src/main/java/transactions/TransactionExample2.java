package transactions;

import basicSyntax.dto.User;
import basicSyntax.repository.UserRepository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class TransactionExample2 {

    private final UserRepository repository;

    public TransactionExample2(UserRepository repository){
        this.repository = repository;
    }

    @Transactional(propagation = Propagation.MANDATORY)
    @Transactional(propagation = Propagation.NESTED)
    @Transactional(propagation = Propagation.NEVER)
    @Transactional(propagation = Propagation.REQUIRED)
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Transactional(propagation = Propagation.SUPPORTS)
    public void createUser(User user) {
        repository.save(user);
        throw new RuntimeException("Something failed");
    }

    @Transactional(isolation = Isolation.DEFAULT)
    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void createUser2(User user) {
        repository.save(user);
        throw new RuntimeException("Something failed");
    }
}
