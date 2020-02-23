package models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

//@AllArgsConstructor
//@NoArgsConstructor
//@Builder

@Entity
public class Comment {
    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Account author;

    @Column
    private String context;

    @Column
    int numOfLike;

    @ManyToOne
    private Post commentedPost;

    public Comment() {
    }

    public Comment(Account author, String context, int numOfLike,Post commentedPost) {
        this.author = author;
        this.context = context;
        this.numOfLike = numOfLike;
        this.commentedPost = commentedPost;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumOfLike() {
        return numOfLike;
    }

    public void setNumOfLike(int numOfLike) {
        this.numOfLike = numOfLike;
    }

    public Account getAuthor() {
        return author;
    }

    public void setAuthor(Account author) {
        this.author = author;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public Post getCommentedPost() {
        return commentedPost;
    }

    public void setCommentedPost(Post commentedPost) {
        this.commentedPost = commentedPost;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", author=" + author +
                ", context='" + context + '\'' +
                ", numOfLike=" + numOfLike +
                '}';
    }
}
