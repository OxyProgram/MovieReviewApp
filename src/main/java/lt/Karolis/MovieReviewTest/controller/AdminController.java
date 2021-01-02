package lt.Karolis.MovieReviewTest.controller;

import lt.Karolis.MovieReviewTest.dto.NewUserData;
import lt.Karolis.MovieReviewTest.dto.SuccessResponse;
import lt.Karolis.MovieReviewTest.dto.UserEmailRequest;
import lt.Karolis.MovieReviewTest.dto.UserInfoResponse;
import lt.Karolis.MovieReviewTest.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }


    @RequestMapping("/deleteUser")
    @PostMapping
    public ResponseEntity<SuccessResponse> deleteUser(@RequestBody UserEmailRequest request) {
        return userService.deleteUser(request.getEmail());
    }

    @RequestMapping("/getUserData")
    @PostMapping
    public ResponseEntity<UserInfoResponse> getUserData(@RequestBody UserEmailRequest request) {
        return new ResponseEntity<>(userService.getUserData(request.getEmail()), HttpStatus.OK);
    }

    @RequestMapping("/changeUserData")
    @PostMapping
    public SuccessResponse changeUserData(HttpServletRequest request, @RequestBody NewUserData data) {
        return userService.changeUserData(request, data);
    }

}
