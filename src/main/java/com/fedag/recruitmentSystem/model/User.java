package com.fedag.recruitmentSystem.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name")
    private String firstname;

    @Column(name = "last_name")
    private String lastname;

    @Column(name = "email")
    private String email;

    @Column(name = "birthday")
    private Date birthday;

    @OneToOne(cascade = CascadeType.ALL,
    mappedBy = "user",fetch = FetchType.LAZY)
    @JsonManagedReference
    private Exam exam;

    @OneToMany(cascade = CascadeType.ALL,
    mappedBy = "user",fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<UserFeedback> userFeedbackList;

    @OneToMany(cascade = CascadeType.ALL,
    mappedBy = "user",fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Resume> resumeList;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL
            ,fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<VacancyResponse> vacancyResponseList;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL
            ,fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Message> messageList;
}
