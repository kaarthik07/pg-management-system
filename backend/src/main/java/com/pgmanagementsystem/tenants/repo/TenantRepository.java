package com.pgmanagementsystem.tenants.repo;

import com.pgmanagementsystem.tenants.model.Tenant;

import java.util.List;
import java.util.Optional;

public interface TenantRepository {
    Tenant save(Tenant t);

    Optional<Tenant> findById(String id);

    void deleteById(String id);

    List<Tenant> findAll();
}
