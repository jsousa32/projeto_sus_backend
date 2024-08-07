package api.projeto_sus_backend.appointments.boundary;

import api.projeto_sus_backend.appointments.controls.AppointmentProjections;
import api.projeto_sus_backend.appointments.entities.Appointment;
import api.projeto_sus_backend.utils.CustomPageable;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
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
     * @param authenticationToken;
     * @param pacientId;
     * @param doctorId;
     * @param appointment;
     * @return ResponseEntity<Void>;
     */
    @Operation(summary = "Endpoint responsável por criar uma consulta")
    @PostMapping
    @JsonView(AppointmentProjections.Create.class)
    public ResponseEntity<Void> save(
            JwtAuthenticationToken authenticationToken,
            @RequestParam(name = "pacientId", required = false) UUID pacientId,
            @RequestParam(name = "doctorId") UUID doctorId,
            @Valid @RequestBody Appointment appointment) {

        appointmentBusiness.save(authenticationToken, pacientId, doctorId, appointment);

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
            JwtAuthenticationToken authenticationToken,
            @RequestParam(name = "page", required = false) Integer page,
            @RequestParam(name = "size", required = false) Integer size,
            @RequestParam(name = "sorting", required = false) String sorting,
            @RequestParam(name = "filter", required = false, defaultValue = "") String filter
    ) {

        Page<Appointment> response = appointmentBusiness.findAll(authenticationToken, filter, CustomPageable.getInstance(page, size, sorting));

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    /**
     * Endpoint responsável por buscar uma consulta
     *
     * @return ResponseEntity<Page < Appointment>>;
     */
    @Operation(summary = "Endpoint responsável por buscar uma consulta")
    @GetMapping
    @JsonView(AppointmentProjections.Resume.class)
    public ResponseEntity<Appointment> findById(@RequestParam("id") UUID id) {

        Appointment response = appointmentBusiness.findById(id);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    /**
     * Endpoint responsável por retornar os horários disponíveis do médico para cadastrar uma consulta
     *
     * @param authenticationToken;
     * @param doctorId;
     * @param dateAppointment;
     * @return ResponseEntity<List < String>>;
     */
    @Operation(summary = "Endpoint responsável por retornar os horários disponíveis do médico para cadastrar uma consulta")
    @GetMapping("avaliable-times")
    public ResponseEntity<List<String>> avaliableTimes(
            JwtAuthenticationToken authenticationToken,
            @RequestParam("doctorId") UUID doctorId,
            @RequestParam("dateAppointment") @JsonFormat(pattern = "dd/MM/yyyy") LocalDate dateAppointment
    ) {
        List<String> response = appointmentBusiness.avaliableTimes(authenticationToken, doctorId, dateAppointment);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    /**
     * Endpoint responsável por atualizar uma consulta
     *
     * @param id;
     * @param appointment;
     * @return ResponseEntity<Void>;
     */
    @Operation(summary = "Endpoint responsável por atualizar uma consulta")
    @PatchMapping
    @JsonView(AppointmentProjections.Update.class)
    public ResponseEntity<Void> update(
            JwtAuthenticationToken authenticationToken,
            @RequestParam(name = "pacientId", required = false) UUID pacientId,
            @RequestParam(name = "id") UUID id,
            @Valid @RequestBody Appointment appointment
    ) {
        appointmentBusiness.update(authenticationToken,pacientId, id, appointment);

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
