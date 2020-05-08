package models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

//@AllArgsConstructor
//@NoArgsConstructor
//@Builder

@Entity
public class Comment implements Serializable {
    private static final long serialVersionUID = -6503511648008024597L;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return numOfLike == comment.numOfLike &&
                Objects.equals(id, comment.id) &&
                Objects.equals(author, comment.author) &&
                Objects.equals(context, comment.context) &&
                Objects.equals(commentedPost, comment.commentedPost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, author, context, numOfLike, commentedPost);
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
