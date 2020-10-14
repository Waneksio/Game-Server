package pl.put.poznan.gameserver.accessdata.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.put.poznan.gameserver.accessdata.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByNick(String nick);
}