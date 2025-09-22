package com.pgmanagementsystem.tenants.repo;

import com.pgmanagementsystem.tenants.model.Tenant;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryTenantRepository implements TenantRepository {

    private final Map<String, Tenant> store = new ConcurrentHashMap<>();

    @Override
    public Tenant save(Tenant t) {
        store.put(t.getId(), t);
        return t;
    }

    @Override
    public Optional<Tenant> findById(String id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public void deleteById(String id) {
        store.remove(id);
    }

    @Override
    public List<Tenant> findAll() {
        return new ArrayList<>(store.values());
    }
}
