package prac.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import prac.user.domain.CustomUserDetails;
import prac.user.enums.EncryptionAlgorithm;

@Service
@RequiredArgsConstructor
public class AuthenticationProviderService implements AuthenticationProvider {

    private final JpaUserDetailsService userDetailsService;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final SCryptPasswordEncoder sCryptPasswordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        CustomUserDetails user = userDetailsService.loadUserByUsername(username);

        EncryptionAlgorithm encAlgorithm = user.getUser().getAlgorithm();

        if (encAlgorithm.equals(EncryptionAlgorithm.BCRYPT)) {
            return checkPassword(user, password, bCryptPasswordEncoder);
        }

        if (encAlgorithm.equals(EncryptionAlgorithm.SCRYPT)) {
            return checkPassword(user, password, sCryptPasswordEncoder);
        }

        throw new BadCredentialsException("Bad credentials");
    }

    private Authentication checkPassword(CustomUserDetails user, String rawPassword, PasswordEncoder passwordEncoder) {
        if (passwordEncoder.matches(rawPassword, user.getPassword())) {
            return new UsernamePasswordAuthenticationToken(
                    user.getUsername(),
                    user.getPassword(),
                    user.getAuthorities()
            );
        }
        throw new BadCredentialsException("Bad credentials");
    }


    @Override
    public boolean supports(Class<?> authentication) {
        return false;
    }
}
