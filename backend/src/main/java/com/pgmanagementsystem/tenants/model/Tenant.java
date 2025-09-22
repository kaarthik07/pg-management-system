package com.pgmanagementsystem.tenants.model;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

public class Tenant {
    private String id;
    private String fullName;
    private String phone;
    private String email;
    private String status; // ACTIVE, NOTICE, LEFT
    private String currentRoomId; // optional
    private LocalDate startDate;

    public Tenant() {
    }

    public Tenant(String id, String fullName, String phone, String email, String status, String currentRoomId, LocalDate startDate) {
        this.id = id;
        this.fullName = fullName;
        this.phone = phone;
        this.email = email;
        this.status = status;
        this.currentRoomId = currentRoomId;
        this.startDate = startDate;
    }

    public static Tenant newFrom(String fullName, String phone, String email, String status, String currentRoomId, LocalDate startDate) {
        return new Tenant(UUID.randomUUID().toString(), fullName, phone, email, status, currentRoomId, startDate);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCurrentRoomId() {
        return currentRoomId;
    }

    public void setCurrentRoomId(String currentRoomId) {
        this.currentRoomId = currentRoomId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tenant tenant = (Tenant) o;
        return Objects.equals(id, tenant.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
