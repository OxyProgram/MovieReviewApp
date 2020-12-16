package lt.Karolis.MovieReviewTest.service;

import lt.Karolis.MovieReviewTest.dto.AuthenticationResponse;
import lt.Karolis.MovieReviewTest.dto.LoginRequest;
import lt.Karolis.MovieReviewTest.dto.RegisterRequest;
import lt.Karolis.MovieReviewTest.model.NotificationEmail;
import lt.Karolis.MovieReviewTest.model.User;
import lt.Karolis.MovieReviewTest.model.VerificationToken;
import lt.Karolis.MovieReviewTest.repository.UserRepository;
import lt.Karolis.MovieReviewTest.repository.VerificationTokenRepository;
import lt.Karolis.MovieReviewTest.security.JwtProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final VerificationTokenRepository verificationTokenRepository;
    private final MailService mailService;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;

    String websiteName = "http://localhost:8080";

    public AuthService(PasswordEncoder passwordEncoder, UserRepository userRepository, VerificationTokenRepository verificationTokenRepository, MailService mailService, AuthenticationManager authenticationManager, JwtProvider jwtProvider) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.verificationTokenRepository = verificationTokenRepository;
        this.mailService = mailService;
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
    }


    public boolean signup(RegisterRequest registerRequest) {
        if(userRepository.findByEmail(registerRequest.getEmail()) != null)
            return false;
        User user = new User();
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setCreated(Instant.now());
        user.setEnabled(false);

        userRepository.save(user);

        String token = generateVerificationToken(user);
        String body = "Thank you for signing up to Movie Review App! \n " +
                "Please click on the below url to activate your account : \n" +
                "http://localhost:8080/api/auth/accountVerification/" + token;

        /*mailService.sendMail(new NotificationEmail("Please Activate your Account",
                user.getEmail(), "Thank you for signing up to Movie Review App! \n " +
                "Please click on the below url to activate your account : \n" +
                "http://localhost:8080/api/auth/accountVerification/" + token));*/

        mailService.sendMail(new NotificationEmail("Please Activate your Account",
                user.getEmail(), body));
        return true;

    }

    private String generateVerificationToken(User user) {
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setUser(user);

        verificationTokenRepository.save(verificationToken);
        return token;
    }

    public boolean verifyAccount(String token) {
        Optional<VerificationToken> verificationToken = verificationTokenRepository.findByToken(token);
        if(fetchUserAndEnable(verificationToken.get()))
            return true;
        return false;
    }

    @Transactional
    public boolean fetchUserAndEnable(VerificationToken verificationToken) {
        String email = verificationToken.getUser().getEmail();
        User user = userRepository.findByEmail(email);
        user.setEnabled(true);
        userRepository.save(user);
        verificationTokenRepository.deleteById(verificationToken.getId());
        return true;
    }

    public AuthenticationResponse login(LoginRequest loginRequest) {
        if(userRepository.findByEmail(loginRequest.getEmail()) == null)
            return null;
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.generateToken(authentication);
        return new AuthenticationResponse(token, loginRequest.getEmail(), true);
    }
}
