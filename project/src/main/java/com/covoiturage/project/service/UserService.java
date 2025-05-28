package com.covoiturage.project.service;

import com.covoiturage.project.entity.User;
import com.covoiturage.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    // Méthode qui vérifie si le nom d'utilisateur existe dans la base de données
    public boolean usernameExists(String username) {
        User user = userRepository.findByUsername(username);
        return user != null;
    }

    // Vérifier si l'email existe
    public boolean emailExists(String email) {
        User user = userRepository.findByEmail(email);
        return user != null;
    }

    //méthode qui retourne pending, not_approved, approved
    public List<User> getUsersWithStateNotQualified() {
        return userRepository.findByStateNot(User.UserState.NOT_QUALIFIED);
    }

    //méthode qui retourne pending
    public List<User> getUsersWithStatePending() {
        return userRepository.findByState(User.UserState.PENDING);
    }

    // Méthode pour récupérer les 10 derniers utilisateurs
    public List<User> getRecentUsers() {
        return userRepository.findTop10Users();
    }

    //counting users
    public long countUsers() {
        return userRepository.count();
    }

    //update user state
    public void updateUserState(Long id, String state) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        user.setState(User.UserState.valueOf(state));
        userRepository.save(user);
    }

}
