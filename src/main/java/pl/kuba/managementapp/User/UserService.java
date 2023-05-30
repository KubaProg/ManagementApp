package pl.kuba.managementapp.User;

import jakarta.transaction.Transactional;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.kuba.managementapp.User.dto.UserCredentialsDto;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private static final String USER_ROLE = "USER";
    private static final String ADMIN_AUTHORITY = "ROLE_ADMIN";
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, UserRoleRepository userRoleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<User> findUserByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public Optional<UserCredentialsDto> findCredentialByEmail(String email){
        return userRepository.findByEmail(email)
                .map(UserCredentialsDtoMapper::map);
    }

    public User findCurrentUser(){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(email).orElseThrow(NoSuchElementException::new);
    }

    public Long findCurrentUserId(){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findIdByEmail(email).orElseThrow(NoSuchElementException::new);
    }

    @Transactional
    public void deleteUserByEmail(String email){
        userRepository.deleteByEmail(email);
    }

    public List<User> findAllEmployees(){
        List<User> allUsers = userRepository.findAll();
        return allUsers.stream()
                .filter(user -> user.getRoles().stream().anyMatch(role -> role.getName().equals("USER")))
                .toList();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }

    public boolean checkIfUserExistByEmail(String email){
        return userRepository.existsUserByEmail(email);
    }

}
