package baigiamasis.UsersOfSportOrganizer.user;

import jakarta.persistence.*;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

@Entity
//@RequiredArgsConstructor
//@NoArgsConstructor
@Data
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(nullable = false, unique = true, length = 30)
    private String username;

    @NotNull
    @Column(nullable = false, unique = true)
    private String password;

    @NotNull
    @Column(nullable = false)
    private String role;

    public User() {

    }

    public User(@NotNull String username, @NotNull String password, @NotNull String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}