package com.andev.integration.dao;

import com.andev.dao.QPredicate;
import com.andev.dao.UserRepository;
import com.andev.dto.UserFilter;
import com.andev.entity.User;
import com.andev.integration.IntegrationTestBase;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.List;

import static com.andev.entity.QUser.user;
import static org.assertj.core.api.Assertions.assertThat;

@RequiredArgsConstructor
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class UserRepositoryTest extends IntegrationTestBase {

    private final UserRepository userRepository;

    @Test
    void whenFindUserByFilter_thenReturnListUsers() {
        UserFilter userFilter = new UserFilter(null, null, "ov");
        List<User> result = userRepository.findAllByFilter(userFilter);
        assertThat(result).hasSize(4);
    }

    @Test
    void checkQuerydslPredicateExecutor() {
        UserFilter filter = new UserFilter(null, "et", null);

        Predicate predicate = QPredicate.builder()
                .add(filter.userName(), user.userName::containsIgnoreCase)
                .add(filter.firstName(), user.firstName::containsIgnoreCase)
                .add(filter.lastName(), user.lastName::containsIgnoreCase)
                .buildAnd();
        Iterable<User> result = userRepository.findAll(predicate);
        assertThat(result).hasSize(2);
    }
}