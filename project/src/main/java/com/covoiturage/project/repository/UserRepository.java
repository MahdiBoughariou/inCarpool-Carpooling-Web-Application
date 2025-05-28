package com.covoiturage.project.repository;

import com.covoiturage.project.entity.User;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User findByEmail(String email);

    //recherche sauf not_qualifed
    List<User> findByStateNot(User.UserState state);

    //recherche pending
    List<User> findByState(User.UserState state);

    // Récupérer les 10 derniers utilisateurs
    default List<User> findTop10Users() {
        Pageable pageable = PageRequest.of(0, 10);
        return findAllByOrderByIdDesc(pageable);
    }

    List<User> findAllByOrderByIdDesc(Pageable pageable);
}
