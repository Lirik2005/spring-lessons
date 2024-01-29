package org.example.database.repository;

import org.example.bpp.Auditing;
import org.example.bpp.Transaction;
import org.example.database.entity.Company;
import org.example.database.pool.ConnectionPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

/**
 * Аннотация @Transaction и @Auditing будет закрывать нашу транзакцию после выполнения операций
 */
@Transaction
@Auditing
public class CompanyRepository implements CrudRepository<Integer, Company> {

    /**
     * Аннотация @Autowired - это уже готовое решение в Spring для инжекта бинов.
     * Вместо @Autowired также можно использовать аннотацию @Resource, однако для нее надо подключать отдельную зависимость
     * jakarta.annotation:jakarta.annotation-api. Данная аннотация нужна, чтобы удовлетворять спецификации JSR-250. В реальной практике
     * используется @Autowired, которая имеет более широкий функционал.
     * Аннотация @Qualifier нам нужна, чтобы указать, какой именно бин мы хотим получить (в application.xml у нас уже есть 2 бина для
     * CompanyRepository).
     * ВМЕСТО аннотации @Qualifier("pool2") можно просто указать название бина прямо при его объявлении. То есть, вместо ConnectionPool
     * connectionPool, мы можем написать ConnectionPool pool2 (то есть указать id бина) и Spring отдаст нам этот бин.
     * Также аннотацию @Autowired можно поставить и над сеттером для бина ConnectionPool pool2 (и так тоже отработает, но так делать не
     * стоит).
     * Лучше всего аннотацию @Autowired ставить над конструктором!!!
     */
//    @Resource(name = "pool1")
    @Autowired
//    @Qualifier("pool2")
    private ConnectionPool pool2;

    /**
     * Также аннотацию @Autowired можно ставить над коллекциями и тогда у нас вернется коллекция
     */
    @Autowired
    private List<ConnectionPool> pools;

    /**
     * Аннотация @Value позволяет заинжектить поля из application.properties, используя Spring Expression Language
     */

    @Value("${db.pool.size}")
    private Integer poolSize;


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
