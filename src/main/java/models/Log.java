package models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author shervin zadsoroor
 * this is a logging class
 */
@Entity
public class Log implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String Type;

    @Column
    private String message;

    public Log() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Log log = (Log) o;
        return Objects.equals(id, log.id) &&
                Objects.equals(Type, log.Type) &&
                Objects.equals(message, log.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, Type, message);
    }

    @Override
    public String toString() {
        return "Log{" +
                "id=" + id +
                ", Type='" + Type + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
