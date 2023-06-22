package pl.javastart.demo.user;

import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserCredentialsDtoMapper {
    public UserCredentialsDto map(UserBasic userBasic) {
        String username = userBasic.getUsername();
        String password = userBasic.getPassword();
        Set<String> roles = userBasic.getRoles()
                .stream()
                .map(UserRole::getName)
                .collect(Collectors.toSet());
        return new UserCredentialsDto(username, password, roles);
    }
}
