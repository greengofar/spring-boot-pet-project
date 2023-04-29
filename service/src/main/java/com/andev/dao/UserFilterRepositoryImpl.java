package com.andev.dao;

import com.andev.dto.UserFilter;
import com.andev.entity.User;
import com.andev.entity.User_;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;


@RequiredArgsConstructor
public class UserFilterRepositoryImpl implements UserFilterRepository {

    private final EntityManager entityManager;

    @Override
    public List<User> findAllByFilter(UserFilter filter) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteria = cb.createQuery(User.class);
        Root<User> user = criteria.from(User.class);

        List<Predicate> predicates = CriteriaPredicate.builder()
                .add(filter.userName(), usName -> cb.equal(user.get(User_.USER_NAME), usName))
                .add(filter.firstName(), fstName -> cb.equal(user.get(User_.FIRST_NAME), fstName))
                .add(filter.lastName(), lstName -> cb.equal(user.get(User_.LAST_NAME), lstName))
                .getPredicates();

        criteria.select(user).where(predicates.toArray(Predicate[]::new));

        return entityManager.createQuery(criteria)
                .getResultList();
    }
}
