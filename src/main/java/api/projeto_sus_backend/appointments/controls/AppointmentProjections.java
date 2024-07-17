package api.projeto_sus_backend.appointments.controls;

import api.projeto_sus_backend.doctor.controls.DoctorProjections;
import api.projeto_sus_backend.pacient.controls.PacientProjections;

import java.time.LocalDate;
import java.util.UUID;

/**
 * The Class AppointmentProjections
 *
 * @author João Lucas Silva de Sousa
 * @sincer 03/07/2024
 */
public class AppointmentProjections {

    public interface Page extends DoctorProjections.ResumeToAppointments {

        UUID getId();

        LocalDate getDate();

        String getHour();

        DoctorProjections.ResumeToAppointments getDoctor();

        PacientProjections.ResumeToAppointment getPacient();
    }

    public interface Resume extends Page {
    }

    public interface Create {

        LocalDate getDate();

        String getHour();
    }

    public interface Update extends Create {
    }
}
