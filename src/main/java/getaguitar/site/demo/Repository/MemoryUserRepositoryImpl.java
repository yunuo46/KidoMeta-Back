package getaguitar.site.demo.Repository;

import getaguitar.site.demo.Entity.MemoryUserEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class MemoryUserRepositoryImpl implements MemoryUserRepository {

    private static final Map<Integer, MemoryUserEntity> users = new ConcurrentHashMap<>();

    @Override
    public void save(MemoryUserEntity user) {
        users.put(user.getId(), user);
    }

    @Override
    public Optional<MemoryUserEntity> findByUsername(String username) {
        for (MemoryUserEntity user : users.values()) {
            if (user.getUsername().equals(username)) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<MemoryUserEntity> findBySessionId(String sessionId) {
        for (MemoryUserEntity user : users.values()) {
            if (user.getSessionId().equals(sessionId)) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<MemoryUserEntity> findAll() {
        return new ArrayList<>(users.values());
    }

    @Override
    public void deleteBySessionId(String sessionId) {
        users.values().removeIf(user -> user.getSessionId().equals(sessionId));
    }

    @Override
    public void updateX(String username, int x) {
        for (MemoryUserEntity user : users.values()) {
            if (user.getUsername().equals(username)) {
                user.setX(x);
            }
        }
    }

    @Override
    public void updateY(String username, int y) {
        for (MemoryUserEntity user : users.values()) {
            if (user.getUsername().equals(username)) {
                user.setY(y);
            }
        }
    }

}
