package ru.javawebinar.topjava.repository.mock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.AbstractNamedEntity;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryUserRepositoryImpl implements UserRepository {
    private static final Logger log = LoggerFactory.getLogger(InMemoryUserRepositoryImpl.class);
    private Map<Integer, User> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    public static final int USER_ID = 1;
    public static final int ADMIN_ID = 2;

    {

        save(new User(2,"Admin","admin@yandex.ru","admin", Role.ROLE_ADMIN));
    }

    @Override
    public boolean delete(int id) {
        log.info("delete {}", id);
        int bound = repository.size();
        for (int i = 0; i < bound; i++) {
            if (repository.get(i).getId() == id && repository.get(i).getId()!=null) {
                repository.remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public User save(User user) {
        if (user.isNew()) {
            user.setId(counter.incrementAndGet());
        }
        repository.put(user.getId(), user);
        return user;
    }


    @Override
    public User get(int id) {
        log.info("get {}", id);
        for (int i = 0; i <repository.size() ; i++) {
            if (repository.get(i).getId()==id){
                return repository.get(i);
            }
        }
        return null;
    }

    @Override
    public List<User> getAll() {
        log.info("getAll");
        return repository.values().stream().sorted((Comparator.comparing(AbstractNamedEntity::getName))).collect(Collectors.toList());

    }

    @Override
    public User getByEmail(String email) {
        log.info("getByEmail {}", email);
        return repository.values().stream().filter(user -> user.getEmail().equals(email)).findFirst().orElse(null);
    }
}
