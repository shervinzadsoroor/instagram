package models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class User {

    private Long id;

    private String firstName;

    private String lastName;

}
