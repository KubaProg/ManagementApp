package pl.kuba.managementapp.web.admin;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.kuba.managementapp.User.User;
import pl.kuba.managementapp.User.UserRepository;
import pl.kuba.managementapp.User.UserRole;
import pl.kuba.managementapp.User.UserRoleRepository;

import java.util.NoSuchElementException;

@Service
public class AdminService {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminService(UserRepository userRepository, UserRoleRepository userRoleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void saveUser(User user){
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);
    }

    public UserRole findRoleByName(String name){
        return userRoleRepository.findByName(name).orElseThrow(NoSuchElementException::new);
    }

}
