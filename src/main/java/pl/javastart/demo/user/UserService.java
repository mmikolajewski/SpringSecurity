package pl.javastart.demo.user;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private static final String USER_ROLE = "USER";
    private static final String USER_ADMIN = "ADMIN";
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;

    private final UserCredentialsDtoMapper userCredentialsDtoMapper;

    public UserService(UserRepository userRepository, UserRoleRepository userRoleRepository, PasswordEncoder passwordEncoder, UserCredentialsDtoMapper userCredentialsDtoMapper) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
        this.userCredentialsDtoMapper = userCredentialsDtoMapper;
    }

    public void register(UserDto registration) {
        UserBasic userBasic = new UserBasic();
        userBasic.setUsername(registration.getUsername());
        String passwordHash = passwordEncoder.encode(registration.getPassword());
        userBasic.setPassword(passwordHash);
        Optional<UserRole> userRole = userRoleRepository.findByName(USER_ROLE);
        userRole.ifPresentOrElse(
                role -> userBasic.getRoles().add(role),
                () -> {
                    throw new NoSuchElementException();
                }
        );
        userRepository.save(userBasic);
    }

    public List<UserBasic> findWithoutCurrentUser() {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findAll()
                .stream()
                .filter(userBasic -> !userBasic.getUsername().equals(currentUser.getName()))
                .collect(Collectors.toList());
    }
    public Optional<UserRole> findAdminRole() {
        return userRoleRepository.findByName(USER_ADMIN);
    }

    @Transactional
    public void giveAdmin(Long id) {
        Optional<UserRole> role = userRoleRepository.findByName(USER_ADMIN);
        Optional<UserBasic> userById = userRepository.findById(id);
        if (userById.isPresent() && role.isPresent()) {
            UserBasic user = userById.get();
            UserRole userRole = role.get();
            user.addRole(userRole);
        }
    }
    @Transactional
    public void removeAdmin(Long id) {
        Optional<UserRole> role = userRoleRepository.findByName(USER_ADMIN);
        Optional<UserBasic> userById = userRepository.findById(id);
        if (userById.isPresent() && role.isPresent()) {
            UserBasic user = userById.get();
            UserRole userRole = role.get();
            user.removeRole(userRole);
        }
    }
    public UserBasic getCurrentUser() {
        String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        UserBasic userBasic = userRepository.findByUsername(currentUsername).orElseThrow();
        return userBasic;

    }
    @Transactional
    public void updateUserDetails(UserBasic newUser) {
        Optional<UserBasic> userById = userRepository.findById(newUser.getId());

        if (userById.isPresent()) {
            UserBasic user = userById.get();
            user.setFirstName(newUser.getFirstName());
            user.setLastName(newUser.getLastName());
            String passwordHash = passwordEncoder.encode(newUser.getPassword());
            user.setPassword(passwordHash);
        }
    }
    public Optional<UserCredentialsDto> findCredentialsByEmail(String username) {
        return userRepository.findByUsername(username)
                .map(userCredentialsDtoMapper::map);
    }
}
