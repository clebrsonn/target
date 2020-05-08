package br.com.hyteck.platform.frw;

import br.com.hyteck.platform.service.IServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@AllArgsConstructor
public abstract class AbstractController<Entity> {

    protected final IServices<Entity> service;

    @Operation(summary = "alteração de entidades")
    @PutMapping(value = "{id}")
    public ResponseEntity<Entity> update(@Parameter @PathVariable Long id, @Parameter @RequestBody Entity Entity) {
        Entity entities = service.update(id, Entity);

        return new ResponseEntity<>(entities, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<Entity>> findAll(@Parameter Pageable pageable) {

        Page<Entity> Entitys = service.findall(pageable);
        return new ResponseEntity<>(Entitys, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Optional<Entity>> findById(@Parameter @PathVariable Long id) {
        Optional<Entity> Entity = service.findById(id);
        return ResponseEntity.ok(Entity);
    }

    @PostMapping
    public ResponseEntity<Entity> create(@Parameter @RequestBody Entity entity) {
        final var entitySave = service.create(entity);
        return new ResponseEntity<>(entitySave, HttpStatus.CREATED);
    }

}
