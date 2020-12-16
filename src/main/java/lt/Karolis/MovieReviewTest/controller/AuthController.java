package lt.Karolis.MovieReviewTest.controller;

import lt.Karolis.MovieReviewTest.dto.AuthenticationResponse;
import lt.Karolis.MovieReviewTest.dto.LoginRequest;
import lt.Karolis.MovieReviewTest.dto.RegisterRequest;
import lt.Karolis.MovieReviewTest.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }


    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody RegisterRequest registerRequest) {
        boolean emailExists = !authService.signup(registerRequest);
        if(emailExists == false)
            return new ResponseEntity<>("User registration successful!", HttpStatus.OK);
        return new ResponseEntity<>("Email already in use! Please use another email", HttpStatus.IM_USED);
    }

    @GetMapping("/accountVerification/{token}")
    public ResponseEntity<String> verifyAccount(@PathVariable String token) {
        if(authService.verifyAccount(token))
            return new ResponseEntity<>("Account Activated Successfully", HttpStatus.OK);
        return new ResponseEntity<>("Error occurred while activating account.", HttpStatus.CONFLICT);
    }

    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody LoginRequest loginRequest) {
        if(authService.login(loginRequest) == null)
            return new AuthenticationResponse("0", "0", false);
        return authService.login(loginRequest);
    }


}
