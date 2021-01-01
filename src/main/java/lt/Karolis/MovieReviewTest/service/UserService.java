package lt.Karolis.MovieReviewTest.service;

import lt.Karolis.MovieReviewTest.dto.NewUserData;
import lt.Karolis.MovieReviewTest.dto.SuccessResponse;
import lt.Karolis.MovieReviewTest.dto.UserInfoResponse;
import lt.Karolis.MovieReviewTest.model.User;
import lt.Karolis.MovieReviewTest.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
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

    public ResponseEntity<SuccessResponse> deleteUser(String email) {
        userRepository.deleteById(userRepository.findByEmail(email).getUserId());
        return new ResponseEntity<>(new SuccessResponse(true), HttpStatus.OK);
    }


}
