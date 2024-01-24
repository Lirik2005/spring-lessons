package org.example.database.repository;

import org.example.bpp.Auditing;
import org.example.bpp.InjectBean;
import org.example.bpp.Transaction;
import org.example.database.entity.Company;
import org.example.database.pool.ConnectionPool;

import javax.annotation.PostConstruct;
import java.util.Optional;

/**
 * Аннотация @Transaction и @Auditing будет закрывать нашу транзакцию после выполнения операций
 */
@Transaction
@Auditing
public class CompanyRepository implements CrudRepository<Integer, Company>{

    /**
     * Создаем свою аннотацию, что заинжектать бин и понять как работает BeanPostProcessor. BeanPostProcessor будет смотреть аннотации
     * над полями бинов и, если она будет присутствовать, то будет искать в application context соответствующий бин и инжектать в поле.
     * Это позволит уйти от файла application.xml
     */
    @InjectBean
    private ConnectionPool connectionPool;

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
