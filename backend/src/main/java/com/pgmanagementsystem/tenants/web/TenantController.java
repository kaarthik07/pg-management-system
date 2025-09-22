package com.pgmanagementsystem.tenants.web;

import com.pgmanagementsystem.tenants.dto.TenantRequest;
import com.pgmanagementsystem.tenants.dto.TenantResponse;
import com.pgmanagementsystem.tenants.service.TenantService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tenants")
public class TenantController {

    private final TenantService service;

    public TenantController(TenantService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TenantResponse create(@Valid @RequestBody TenantRequest req) {
        return service.create(req);
    }

    @GetMapping("/{id}")
    public TenantResponse get(@PathVariable String id) {
        return service.get(id);
    }

    @GetMapping
    public List<TenantResponse> list(@RequestParam(required = false) String q,
                                     @RequestParam(required = false) String status,
                                     @RequestParam(required = false, name = "room") String roomId,
                                     @RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "50") int size) {
        return service.list(q, status, roomId, page, size);
    }

    @PutMapping("/{id}")
    public TenantResponse update(@PathVariable String id, @Valid @RequestBody TenantRequest req) {
        return service.update(id, req);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        service.delete(id);
    }
}
