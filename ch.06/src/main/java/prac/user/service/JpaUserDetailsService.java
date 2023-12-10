package prac.user.service;

import java.util.function.Supplier;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import prac.user.domain.CustomUserDetails;
import prac.user.domain.User;
import prac.user.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class JpaUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Supplier<UsernameNotFoundException> s =
                () -> new UsernameNotFoundException(
                        "Problem durign authentication!"
                );

        User user = userRepository
                .findUserByUsername(username)
                .orElseThrow(s);
        return new CustomUserDetails(user);
    }
}
