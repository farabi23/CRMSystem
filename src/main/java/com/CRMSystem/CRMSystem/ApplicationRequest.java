package com.CRMSystem.CRMSystem;


import jakarta.persistence.*;

@Entity
@Table(name="ApplicationRequest")
public class ApplicationRequest {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="id")
    private Long id;

    @Column(name="userName", length = 250)
    private String userName;


    @Column(name = "commentary", length = 250)
    private String commentary;

    @Column(name = "phone", length = 250)
    private String phone;

    @Column(name = "handled", length = 50)
    private boolean handled;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "course_id")
    private Courses course;

    public ApplicationRequest() {

    }

    public Courses getCourse() {
        return course;
    }

    public void setCourse(Courses course) {
        this.course = course;
    }

    public ApplicationRequest(String userName, String commentary, String phone, boolean handled, Courses course) {
        this.userName = userName;

        this.commentary = commentary;
        this.phone = phone;
        this.handled = handled;
        this.course = course;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public String getCommentary() {
        return commentary;
    }

    public void setCommentary(String commentary) {
        this.commentary = commentary;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isHandled() {
        return handled;
    }

    public void setHandled(boolean handled) {
        this.handled = handled;
    }
}
