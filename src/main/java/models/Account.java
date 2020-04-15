package models;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
@Entity
public class Account implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(unique = true)
    private String username;

    @Column
    private String password;

    @OneToMany(mappedBy = "account")
    private List<Post> posts = new ArrayList<>();

    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinTable(name = "likerAccount_post",
            joinColumns = {@JoinColumn(name = "likerAccount_id")},
            inverseJoinColumns = {@JoinColumn(name = "likedPost_id")})
    private Set<Post> LikedPosts = new HashSet<>();

    public Account() {
    }

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Set<Post> getLikedPosts() {
        return LikedPosts;
    }

    public void setLikedPosts(Set<Post> likedPosts) {
        LikedPosts = likedPosts;
    }

    //following and followers
    // TODO: 2/18/20 cascade type should not be all , edit it
    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinTable(name = "following_follower",
            joinColumns = {@JoinColumn(name = "following_id")},
            inverseJoinColumns = {@JoinColumn(name = "follower_id")})
    private Set<Account> followers = new HashSet<>();

    @ManyToMany(mappedBy = "followers", fetch = FetchType.EAGER)
    private Set<Account> followings = new HashSet<>();


    public Set<Account> getFollowers() {
        return followers;
    }

    public void setFollowers(Set<Account> followers) {
        this.followers = followers;
    }

    public Set<Account> getFollowings() {
        return followings;
    }

    public void setFollowings(Set<Account> followings) {
        this.followings = followings;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(id, account.id) &&
                Objects.equals(username, account.username) &&
                Objects.equals(password, account.password) &&
                Objects.equals(posts, account.posts) &&
                Objects.equals(LikedPosts, account.LikedPosts) &&
                Objects.equals(followers, account.followers) &&
                Objects.equals(followings, account.followings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, posts, LikedPosts, followers, followings);
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
