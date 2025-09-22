package com.pgmanagementsystem.tenants.service;

import com.pgmanagementsystem.tenants.dto.TenantRequest;
import com.pgmanagementsystem.tenants.dto.TenantResponse;
import com.pgmanagementsystem.tenants.model.Tenant;
import com.pgmanagementsystem.tenants.repo.InMemoryTenantRepository;
import com.pgmanagementsystem.tenants.repo.TenantRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

@Service
public class TenantService {

    private final TenantRepository repo = new InMemoryTenantRepository();

    public TenantResponse create(TenantRequest req) {
        Tenant t = Tenant.newFrom(
                req.getFullName(),
                req.getPhone(),
                req.getEmail(),
                req.getStatus(),
                req.getCurrentRoomId(),
                req.getStartDate()
        );
        repo.save(t);
        return toResponse(t);
    }

    public TenantResponse get(String id) {
        Tenant t = repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tenant not found: " + id));
        return toResponse(t);
    }

    public List<TenantResponse> list(String q, String status, String roomId, int page, int size) {
        String qn = q == null ? "" : q.toLowerCase(Locale.ROOT);
        return repo.findAll().stream()
                .filter(t -> qn.isEmpty() || (t.getFullName() != null && t.getFullName().toLowerCase(Locale.ROOT).contains(qn))
                        || (t.getPhone() != null && t.getPhone().toLowerCase(Locale.ROOT).contains(qn)))
                .filter(t -> status == null || Objects.equals(t.getStatus(), status))
                .filter(t -> roomId == null || Objects.equals(t.getCurrentRoomId(), roomId))
                .sorted(Comparator.comparing(Tenant::getFullName, Comparator.nullsLast(String::compareToIgnoreCase)))
                .skip((long) page * size)
                .limit(size)
                .map(this::toResponse)
                .collect(java.util.stream.Collectors.toList());
    }

    public TenantResponse update(String id, TenantRequest req) {
        Tenant existing = repo.findById(id).orElseThrow(() -> new RuntimeException("Tenant not found: " + id));
        existing.setFullName(req.getFullName());
        existing.setPhone(req.getPhone());
        existing.setEmail(req.getEmail());
        existing.setStatus(req.getStatus());
        existing.setCurrentRoomId(req.getCurrentRoomId());
        existing.setStartDate(req.getStartDate());
        repo.save(existing);
        return toResponse(existing);
    }

    public void delete(String id) {
        repo.deleteById(id);
    }

    private TenantResponse toResponse(Tenant t) {
        return new TenantResponse(
                t.getId(),
                t.getFullName(),
                t.getPhone(),
                t.getEmail(),
                t.getStatus(),
                t.getCurrentRoomId(),
                t.getStartDate()
        );
    }
}
