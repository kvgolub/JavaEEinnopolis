package ru.innopolis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.innopolis.entity.User;


@Repository
public interface UserRepository extends JpaRepository<User, String> {
}
