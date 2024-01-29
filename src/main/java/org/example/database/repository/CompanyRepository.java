package org.example.database.repository;

import org.example.bpp.Auditing;
import org.example.bpp.Transaction;
import org.example.database.entity.Company;
import org.example.database.pool.ConnectionPool;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

/**
 * Аннотация @Transaction и @Auditing будет закрывать нашу транзакцию после выполнения операций
 * Аннотация @Repository (внутри помечена как @Component) это аналог аннотации @Component и используется над уровнем репозиторием
 */

@Repository
@Transaction
@Auditing
public class CompanyRepository implements CrudRepository<Integer, Company> {

    /**
     * ВМЕСТО аннотации @Qualifier("pool2") можно просто указать название бина прямо при его объявлении. То есть, вместо ConnectionPool
     * connectionPool, мы можем написать ConnectionPool pool1 (то есть указать id бина, который мы указали в аннотации @Component
     * соответствующего класс) и Spring отдаст нам этот бин.
     * Лучше всего аннотацию @Autowired ставить над конструктором!!!
     * Здесь над конструктором мы не ставим @Autowired, так как он будет вызван автоматически. Явно указывать аннотацию над конструктором
     * надо тогда, когда в классе имеется несколько конструкторов!!!
     */

    private final ConnectionPool pool1;

    private final List<ConnectionPool> pools;

    private final Integer poolSize;

    /**
     * Аннотация @Value позволяет заинжектить поля из application.properties, используя Spring Expression Language
     */
    public CompanyRepository(ConnectionPool pool1,
                             List<ConnectionPool> pools,
                             @Value("${db.pool.size}") Integer poolSize) {
        this.pool1 = pool1;
        this.pools = pools;
        this.poolSize = poolSize;
    }

    @PostConstruct
    private void init() {
        System.out.println("Init company repository");
    }

    @Override
    public Optional<Company> findById(Integer id) {
        System.out.println("Find by id method...");
        return Optional.of(new Company(id));
    }

    @Override
    public void delete(Company entity) {
        System.out.println("Delete method...");
    }
}
