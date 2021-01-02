package lt.Karolis.MovieReviewTest.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "User", uniqueConstraints = {
        @UniqueConstraint(columnNames = "ID"),
        @UniqueConstraint(columnNames = "EMAIL") })
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "ID")
    private Long userId;

    @Column(name = "USERNAME")
    @JsonProperty("username")
    private String username;

    @Column(name = "PASSWORD")
    @JsonProperty("password")
    private String password;

    @Column(name = "EMAIL")
    @JsonProperty("email")
    private String email;

    @Column(name = "CREATED")
    private Instant created;

    @Column(name = "ENABLED")
    private boolean enabled;

    @OneToMany(cascade=CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name="USER_ID")
    private List<Movie> movies;

    @Column(name = "NO_OF_MOVIES")
    private int numberOfMovies = 0;

    public User() {
    }

    public User(Long userId, String username, String password, String email, Instant created, boolean enabled, ArrayList<Movie> movies, int numberOfMovies) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.created = created;
        this.enabled = enabled;
        this.movies = movies;
        this.numberOfMovies = numberOfMovies;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Instant getCreated() {
        return created;
    }

    public void setCreated(Instant created) {
        this.created = created;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public List<Movie> getMovieIDs() {
        return movies;
    }

    public void setMovieIDs(List<Movie> movieIDs) {
        this.movies = movieIDs;
    }

    public int getNumberOfMovies() {
        return numberOfMovies;
    }

    public void setNumberOfMovies() {
        this.numberOfMovies++;
    }

    public void addMovie(Movie movie) {
        this.movies.add(movie);
    }


}

