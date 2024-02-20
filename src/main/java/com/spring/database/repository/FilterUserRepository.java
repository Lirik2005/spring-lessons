package com.spring.database.repository;

import com.spring.database.entity.User;
import com.spring.dto.UserFilter;

import java.util.List;

/**
 * Обычный интерфейс, реализацию которому мы напишем в классах, которые его имплементируют.
 * Далее этот интерфейс мы также добавим к интерфейсу UserRepository и все взлетит
 */

public interface FilterUserRepository {

    List<User> findAllByFilter(UserFilter filter);

}
