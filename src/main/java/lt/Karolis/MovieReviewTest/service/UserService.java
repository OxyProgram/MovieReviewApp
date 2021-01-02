package lt.Karolis.MovieReviewTest.service;

import lt.Karolis.MovieReviewTest.dto.NewUserData;
import lt.Karolis.MovieReviewTest.dto.SuccessResponse;
import lt.Karolis.MovieReviewTest.dto.UserInfoResponse;
import lt.Karolis.MovieReviewTest.model.User;
import lt.Karolis.MovieReviewTest.repository.UserRepository;
import lt.Karolis.MovieReviewTest.repository.VerificationTokenRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final VerificationTokenRepository tokenRepository;

    public UserService(UserRepository userRepository, VerificationTokenRepository tokenRepository) {
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
    }

    public UserInfoResponse getUserData(String email) {
        User user = userRepository.findByEmail(email);
        if(user == null)
            return null;
        UserInfoResponse response = new UserInfoResponse();
        response.setUsername(user.getUsername());
        response.setEmail(user.getEmail());
        response.setCreated(user.getCreated());
        response.setNumberOfMovies(user.getNumberOfMovies());

        return response;
    }

    public SuccessResponse changeUserData(HttpServletRequest request, NewUserData data) {
        String email = request.getUserPrincipal().getName();
        User user = userRepository.findByEmail(email);
        if (user == null)
            return null;
        user.setUsername(data.getNewUsername());
        user.setEmail(data.getNewEmail());
        userRepository.save(user);
        return new SuccessResponse(true);

    }

    @Transactional
    public ResponseEntity<SuccessResponse> deleteUser(String email) {
        try {
            tokenRepository.deleteByUser(userRepository.findByEmail(email));
            userRepository.deleteByEmail(email);
        } catch (ConstraintViolationException e) {
            System.out.println(e.getConstraintName());
        }

        return new ResponseEntity<>(new SuccessResponse(true), HttpStatus.OK);
    }


}
