package com.zetdc.crime.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Login name (unique) */
    @Column(nullable = false, unique = true, length = 100)
    private String username;

    /** Securely hashed password */
    @Column(nullable = false)
    private String password;

    /** First name */
    @Column(name = "first_name", length = 100, nullable = false)
    private String firstName;

    /** Last name */
    @Column(name = "last_name", length = 100, nullable = false)
    private String lastName;

    /** Contact email (unique) */
    @Column(nullable = false, unique = true, length = 150)
    private String email;

    /** Mobile phone number */
    @Column(name = "phone_number", length = 20)
    private String phoneNumber;

    /** User’s residential or mailing address */
    @Column(length = 255)
    private String address;

    /** Role in the system, e.g. “REPORTER”, “ADMIN” */
    @Column(nullable = false, length = 50)
    private String role;

    /** When this account was created */
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    /** Last update timestamp */
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    /** All crime reports filed by this user */
    @OneToMany(
        mappedBy = "user",
        cascade = CascadeType.ALL,
        orphanRemoval = true,
        fetch = FetchType.LAZY
    )
    private List<CrimeReport> crimeReports = new ArrayList<>();

    public Users() { }

    public Users(String username,
                 String password,
                 String firstName,
                 String lastName,
                 String email,
                 String phoneNumber,
                 String address,
                 String role) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.role = role;
    }

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = createdAt;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    // ─── Getters & Setters ────────────────────────────────────────────────

    public Long getId() { return id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }

    public List<CrimeReport> getCrimeReports() { return crimeReports; }
    public void addCrimeReport(CrimeReport report) {
        crimeReports.add(report);
        report.setUser(this);
    }
    public void removeCrimeReport(CrimeReport report) {
        crimeReports.remove(report);
        report.setUser(null);
    }

}
