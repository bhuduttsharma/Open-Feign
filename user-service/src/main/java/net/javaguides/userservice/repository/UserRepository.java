package net.javaguides.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.javaguides.userservice.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
