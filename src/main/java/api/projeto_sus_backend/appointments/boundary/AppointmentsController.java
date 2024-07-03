package api.projeto_sus_backend.appointments.boundary;

import api.projeto_sus_backend.appointments.controls.AppointmentProjections;
import api.projeto_sus_backend.appointments.entities.Appointment;
import api.projeto_sus_backend.utils.CustomPageable;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * The Class AppointmentsController
 *
 * @author João Lucas Silva de Sousa
 * @sincer 02/07/2024
 */
@RestController
@RequestMapping(path = "v1/appointments")
@Tag(name = "Módulo de Consultas")
public class AppointmentsController {

    private final AppointmentBusiness appointmentBusiness;

    public AppointmentsController(AppointmentBusiness appointmentBusiness) {
        this.appointmentBusiness = appointmentBusiness;
    }

    /**
     * Endpoint responsável por criar uma consulta
     *
     * @param appointment;
     * @return ResponseEntity<Void>;
     */
    @Operation(summary = "Endpoint responsável por criar uma consulta")
    @PostMapping
    public ResponseEntity<Void> save(
            @RequestParam(name = "pacientId") UUID pacientId,
            @RequestParam(name = "doctorId") UUID doctorId,
            @Valid @RequestBody Appointment appointment) {

        appointmentBusiness.save(pacientId, doctorId, appointment);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    /**
     * Endpoint responsável por buscar todas as consultas paginadas
     *
     * @return ResponseEntity<Page < Appointment>>;
     */
    @Operation(summary = "Endpoint responsável por buscar todas as consultas paginadas")
    @GetMapping("all")
    @JsonView(AppointmentProjections.Page.class)
    public ResponseEntity<Page<Appointment>> findAll(
            @RequestParam(name = "page", required = false) Integer page,
            @RequestParam(name = "size", required = false) Integer size,
            @RequestParam(name = "sorting", required = false) String sorting,
            @RequestParam(name = "filter", required = false, defaultValue = "") String filter
    ) {

        Page<Appointment> response = appointmentBusiness.findAll(filter, CustomPageable.getInstance(page, size, sorting));

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    /**
     * Endpoint responsável por buscar uma consulta
     *
     * @return ResponseEntity<Page < Appointment>>;
     */
    @Operation(summary = "Endpoint responsável por buscar uma consulta")
    @GetMapping
    public ResponseEntity<Appointment> findById(@RequestParam("id") UUID id) {

        Appointment response = appointmentBusiness.findById(id);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary = "Endpoint responsável por atualizar uma consulta")
    @PatchMapping
    public ResponseEntity<Void> update(
            @RequestParam(name = "id") UUID id,
            @Valid @RequestBody Appointment appointment
    ) {
        appointmentBusiness.update(id, appointment);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    /**
     * Endpoint responsável por deletar uma consulta
     *
     * @param id;
     * @return ResponseEntity<Void>;
     */
    @Operation(summary = "Endpoint responsável por deletar uma consulta")
    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestParam("id") UUID id) {
        appointmentBusiness.delete(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
