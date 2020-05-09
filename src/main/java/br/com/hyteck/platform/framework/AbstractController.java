package br.com.hyteck.platform.framework;

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

import javax.validation.Valid;
import java.util.Optional;

@AllArgsConstructor
public abstract class AbstractController<Entity> {

    protected abstract IServices<Entity> getService();

    @Operation(summary = "alteração de entidades")
    @PutMapping(value = "{id}")
    public ResponseEntity<Entity> update(@Parameter @PathVariable Long id,@Valid @Parameter @RequestBody Entity Entity) {
        Entity entities = getService().update(id, Entity);

        return new ResponseEntity<>(entities, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<Entity>> findAll(@Parameter @Valid Pageable pageable) {

        Page<Entity> Entitys = getService().findall(pageable);
        return new ResponseEntity<>(Entitys, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Optional<Entity>> findById(@Parameter @PathVariable Long id) {
        Optional<Entity> Entity = getService().findById(id);
        return ResponseEntity.ok(Entity);
    }

    @PostMapping
    public ResponseEntity<Entity> create(@Parameter @RequestBody @Valid Entity entity) {
        final var entitySave = getService().create(entity);
        return new ResponseEntity<>(entitySave, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@Parameter @PathVariable Long id) {
        getService().delete(id);
    }

}
