package lt.Karolis.MovieReviewTest.controller;

import lt.Karolis.MovieReviewTest.dto.DeleteUserRequest;
import lt.Karolis.MovieReviewTest.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private UserRepository userRepository;

    public AdminController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/deleteUser")
    public ResponseEntity<String> deleteUser(@RequestBody DeleteUserRequest request) {
        try {
            userRepository.deleteById(userRepository.findByEmail(request.getEmail()).getUserId());
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred while deleting user!", HttpStatus.EXPECTATION_FAILED);
        }
        return new ResponseEntity<>("User " + request.getEmail() + " successfully deleted!",
                HttpStatus.OK);
    }

}
