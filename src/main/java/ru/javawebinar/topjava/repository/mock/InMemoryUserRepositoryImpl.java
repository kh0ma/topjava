package ru.javawebinar.topjava.repository.mock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.util.UsersUtil;
import ru.javawebinar.topjava.util.exception.ExceptionUtil;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * GKislin
 * 15.06.2015.
 */
@Repository
public class InMemoryUserRepositoryImpl implements UserRepository {
    private static final Logger LOG = LoggerFactory.getLogger(InMemoryUserRepositoryImpl.class);
    private Map<Integer, User> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        UsersUtil.USERS.forEach(this::save);
    }

    @Override
    public boolean delete(int id) {
        LOG.info("delete user " + id);
        return repository.remove(id)!=null;
    }

    @Override
    public User save(User user) {
        LOG.info("save user " + user);
        if (user.isNew()) {
            user.setId(counter.incrementAndGet());
        }
        repository.put(user.getId(), user);
        return user;
    }

    @Override
    public User get(int id) {
        LOG.info("get user" + id);
        return repository.get(id);
    }

    @Override
    public List<User> getAll() {
        LOG.info("getAll users");
        List list = new ArrayList(repository.values());
        Collections.sort(list);
        return list;
    }

    @Override
    public User getByEmail(String email) {
        LOG.info("getUserByEmail " + email);
        List<User> users = getAll()
                .stream()
                .filter(user -> user.getEmail() == email)
                .collect(Collectors.toList());
        if(users.size() == 0) {
            ExceptionUtil.checkNotFound(users.size() == 0,"There is no user by this email: " + email);
            return null;
        }
        else if(users.size() > 1) {
            ExceptionUtil.checkNotFound(users.size() == 0,"There are two or more users with this email: " + email);
            return null;
        }
        return users.get(0);
    }
}
