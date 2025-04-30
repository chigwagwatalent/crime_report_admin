package com.zetdc.crime.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Represents a theft or vandalism incident report,
 * including full address and GPS coordinates.
 */
@Entity
@Table(name = "crime_reports")
public class CrimeReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Who filed this report
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    /**
     * e.g. “Theft”, “Vandalism”
     */
    @Column(name = "incident_type", nullable = false, length = 100)
    private String incidentType;

    /**
     * Free-form details of what happened
     */
    @Column(columnDefinition = "TEXT")
    private String description;

    // ─── Full address details ─────────────────────────────────────────────

    /** Street address or landmark */
    @Column(name = "address_line", length = 255, nullable = false)
    private String addressLine;

    /** City or town where it occurred */
    @Column(name = "city", length = 100, nullable = false)
    private String city;

    /** Province or state */
    @Column(name = "province", length = 100)
    private String province;

    /** District or zone */
    @Column(name = "zone", length = 100)
    private String zone;

    // ─── GPS coordinates ──────────────────────────────────────────────────

    /** Latitude in decimal degrees */
    @Column(name = "latitude", nullable = false)
    private Double latitude;

    /** Longitude in decimal degrees */
    @Column(name = "longitude", nullable = false)
    private Double longitude;

    // ─── Timing & status ─────────────────────────────────────────────────

    /** When the incident was recorded in the system */
    @Column(name = "recorded_at", nullable = false)
    private LocalDateTime recordedAt;

    /** e.g. “Pending”, “Investigating”, “Resolved” */
    @Column(name = "status", length = 50, nullable = false)
    private String status;

    public CrimeReport() { }

    public CrimeReport(Users user,
                       String incidentType,
                       String description,
                       String addressLine,
                       String city,
                       String province,
                       String zone,
                       Double latitude,
                       Double longitude,
                       LocalDateTime recordedAt,
                       String status) {
        this.user = user;
        this.incidentType = incidentType;
        this.description = description;
        this.addressLine = addressLine;
        this.city = city;
        this.province = province;
        this.zone = zone;
        this.latitude = latitude;
        this.longitude = longitude;
        this.recordedAt = recordedAt;
        this.status = status;
    }

    // ─── Getters & Setters ────────────────────────────────────────────────

    public Long getId() { return id; }

    public Users getUser() { return user; }
    public void setUser(Users user) { this.user = user; }

    public String getIncidentType() { return incidentType; }
    public void setIncidentType(String incidentType) { this.incidentType = incidentType; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getAddressLine() { return addressLine; }
    public void setAddressLine(String addressLine) { this.addressLine = addressLine; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getProvince() { return province; }
    public void setProvince(String province) { this.province = province; }

    public String getZone() { return zone; }
    public void setZone(String zone) { this.zone = zone; }

    public Double getLatitude() { return latitude; }
    public void setLatitude(Double latitude) { this.latitude = latitude; }

    public Double getLongitude() { return longitude; }
    public void setLongitude(Double longitude) { this.longitude = longitude; }

    public LocalDateTime getRecordedAt() { return recordedAt; }
    public void setRecordedAt(LocalDateTime recordedAt) { this.recordedAt = recordedAt; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    // (Optional) equals()/hashCode()/toString() …
}
