package api.projeto_sus_backend.admin.boundary;

import api.projeto_sus_backend.admin.controls.AdminProjections;
import api.projeto_sus_backend.admin.entities.Admin;
import api.projeto_sus_backend.utils.CustomPageable;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * The Class AdminController
 *
 * @author João Lucas Silva de Sousa
 * @sincer 03/07/2024
 */
@RestController
@RequestMapping(path = "v1/admins")
@Tag(name = "Módulo de Administradores")
public class AdminController {

    private final AdminBusiness adminBusiness;

    public AdminController(AdminBusiness adminBusiness) {
        this.adminBusiness = adminBusiness;
    }

    /**
     * Endpoint responsável pela criação de um administrador
     *
     * @param admin;
     * @return ResponseEntity<Void>;
     */
    @Operation(summary = "Endpoint responsável pela criação de um administrador")
    @PostMapping
    @JsonView(AdminProjections.Create.class)
    public ResponseEntity<Void> save(@Validated(AdminProjections.Create.class) @RequestBody Admin admin) {

        adminBusiness.save(admin);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * Endpoint responsável pela busca de todos os administradores do sistema
     *
     * @param page;
     * @param size;
     * @param sorting;
     * @param filter;
     * @return ResponseEntity<Page < Admin>>;
     */
    @Operation(summary = "Endpoint responsável pela busca de todos os administradores do sistema")
    @GetMapping("all")
    @JsonView(AdminProjections.Page.class)
    public ResponseEntity<Page<Admin>> findAll(
            @RequestParam(name = "page", required = false) Integer page,
            @RequestParam(name = "size", required = false) Integer size,
            @RequestParam(name = "sorting", required = false) String sorting,
            @RequestParam(name = "filter", required = false, defaultValue = "") String filter
    ) {
        Page<Admin> response = adminBusiness.findAll(filter, CustomPageable.getInstance(page, size, sorting));

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    /**
     * Endpoint responsável pela busca de um administrador
     *
     * @param id;
     * @return ResponseEntity<Admin>;
     */
    @Operation(summary = "Endpoint responsável pela busca de um administrador")
    @GetMapping
    @JsonView(AdminProjections.Resume.class)
    public ResponseEntity<Admin> findById(@RequestParam(name = "id") UUID id) {
        Admin response = adminBusiness.findById(id);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    /**
     * Endpoint responsável pela atualização de um administrador
     *
     * @param id;
     * @param admin;
     * @return ResponseEntity<Void>;
     */
    @Operation(summary = "Endpoint responsável pela atualização de um administrador")
    @PatchMapping
    @JsonView(AdminProjections.EditablesFields.class)
    public ResponseEntity<Void> update(
            @RequestParam(name = "id") UUID id,
            @Validated(AdminProjections.EditablesFields.class) @RequestBody Admin admin
    ) {
        adminBusiness.update(id, admin);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    /**
     * Endpoint responsável pela deleção de um administrador
     *
     * @param id;
     * @return ResponseEntity<Void>;
     */
    @Operation(summary = "Endpoint responsável pela deleção de um administrador")
    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestParam(name = "id") UUID id) {
        adminBusiness.delete(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
