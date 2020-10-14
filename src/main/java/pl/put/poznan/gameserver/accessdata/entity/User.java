package pl.put.poznan.gameserver.accessdata.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Getter
@Setter
@EqualsAndHashCode
@Entity(name = "user")
public class User {

    @Id
    @Column(name = "u_id")
    int id;

    @Column(name = "u_nick")
    private String nick;

    @Column(name = "u_password")
    private String password;

    @Column(name = "u_score")
    private int score;



}