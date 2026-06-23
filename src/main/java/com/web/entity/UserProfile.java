package com.web.entity;


import javax.persistence.*;

@Entity
@Table(name = "user_Profiles")
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "age")
    private int age;

    @Column(name = "country")
    private String country;

    @Column(name = "preferences")
    private String preferences;

    @Column(name = "photo_url", columnDefinition = "TEXT")
    private String photoUrl;


    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }

    public String getPreferences() { return preferences; }
    public void setPreferences(String preferences) { this.preferences = preferences; }

    public String getPhotoUrl() { return photoUrl; }
    public void setPhotoUrl(String photoUrl) { this.photoUrl = photoUrl; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}