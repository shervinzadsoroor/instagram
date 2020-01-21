package models;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
public class Post implements Serializable, Comparable<Post> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String title;

    @Column
    private String tag;

    @Column
    private int numOfLiked;

    @OneToMany(mappedBy = "commentedPost")
    private List<Comment> comments = new ArrayList<>();

    @ManyToOne
    private Account account;

    @ManyToMany(mappedBy = "LikedPosts")
    private Set<Account> likerAccounts = new HashSet<>();

    public Set<Account> getLikerAccounts() {
        return likerAccounts;
    }

    public void setLikerAccounts(Set<Account> likerAccounts) {
        this.likerAccounts = likerAccounts;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public int getNumOfLiked() {
        return numOfLiked;
    }

    public void setNumOfLiked(int numOfLiked) {
        this.numOfLiked = numOfLiked;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", tag='" + tag + '\'' +
                ", numOfLiked=" + numOfLiked +
                ", comments=" + comments +
                '}';
    }


    @Override
    public int compareTo(Post otherPost) {
        // sorting according to the numOfLiked from higher to lower
        return otherPost.getNumOfLiked() - numOfLiked;
    }
}
