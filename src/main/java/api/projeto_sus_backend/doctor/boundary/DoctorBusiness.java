package api.projeto_sus_backend.doctor.boundary;

import api.projeto_sus_backend.doctor.controls.DoctorGateway;
import api.projeto_sus_backend.doctor.entities.Doctor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * The Class DoctorBusiness
 *
 * @author João Lucas Silva de Sousa
 * @sincer 02/07/2024
 */
@Component
public class DoctorBusiness {

    private final DoctorGateway doctorGateway;

    public DoctorBusiness(DoctorGateway doctorGateway) {
        this.doctorGateway = doctorGateway;
    }

    /**
     * Metodo responsável por criar um médico
     *
     * @param doctor;
     */
    public void save(Doctor doctor) {

        doctorGateway.existsByCrm(doctor.getCrm());

        doctorGateway.existsByEmail(doctor.getEmail());

        doctorGateway.existsByDocument(doctor.getDocument());

        doctorGateway.save(doctor);
    }

    /**
     * Metodo responsável por buscar todos os médicos
     *
     * @param filter;
     * @param pageable;
     * @return Page<Doctor>;
     */
    public Page<Doctor> findAll(String filter, Pageable pageable) {
        return doctorGateway.findAll(filter, pageable);
    }

    /**
     * Metodo responsável por buscar um paciente
     *
     * @param id;
     * @return Doctor;
     */
    public Doctor findById(UUID id) {
        return doctorGateway.findByIdResume(id);
    }

    /**
     * Metodo responsável por atualizar um paciente
     *
     * @param id;
     * @param doctor;
     */
    public void update(UUID id, Doctor doctor) {
        Doctor doctorDB = doctorGateway.findById(id);

        if (!doctor.getEmail().equals(doctorDB.getEmail())) {
            doctorGateway.existsByEmail(doctor.getEmail());
        }

        BeanUtils.copyProperties(doctor, doctorDB, "id", "crm", "document", "password");

        doctorGateway.save(doctor);
    }

    /**
     * Metodo responsável por reativar um médico
     *
     * @param id;
     */
    public void active(UUID id) {
        doctorGateway.active(id);
    }

    /**
     * Metodo responsável por desativar um médico
     *
     * @param id;
     */
    public void disable(UUID id) {
        doctorGateway.disable(id);
    }


}
