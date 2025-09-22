package com.pgmanagementsystem.tenants.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class TenantRequest {
    @NotBlank
    @Size(max = 100)
    private String fullName;

    @NotBlank
    @Pattern(
            regexp = "^(\\+91)?[6-9][0-9]{9}$",
            message = "Invalid mobile number. Must be a valid Indian mobile number (10 digits, starting with 6-9, with optional +91)."
    )
    private String phone;


    @Size(max = 120)
    private String email;

    @NotBlank
    private String status; // ACTIVE, NOTICE, LEFT

    private String currentRoomId; // optional
    private LocalDate startDate;  // optional

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
}
