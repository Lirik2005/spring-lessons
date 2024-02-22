package com.spring.database.repository;

import com.spring.database.entity.Role;
import com.spring.database.entity.User;
import com.spring.dto.PersonalInfo;
import com.spring.dto.UserFilter;

import java.util.List;

/**
 * Обычный интерфейс, реализацию которому мы напишем в классах, которые его имплементируют.
 * Далее этот интерфейс мы также добавим к интерфейсу UserRepository и все взлетит
 */

public interface FilterUserRepository {

    List<User> findAllByFilter(UserFilter filter);

    List<PersonalInfo> findAllByCompanyIdAndRole(Integer companyId, Role role);

    void updateCompanyAndRole (List<User> users);
    void updateCompanyAndRoleNamed (List<User> users);
}
