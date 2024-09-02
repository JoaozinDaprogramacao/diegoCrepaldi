package com.br.crepaldi.imobiliaria.expositor.Users;

import com.br.crepaldi.imobiliaria.expositor.Exceptions.UsernameAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void createUser(String username, String password, String role) {
        // Verifica se já existe um usuário com o mesmo username
        if (userRepository.existsByUsername(username)) {
            throw new UsernameAlreadyExistsException("Usuário digitado já existe");
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(role);
        userRepository.save(user);
    }

}
