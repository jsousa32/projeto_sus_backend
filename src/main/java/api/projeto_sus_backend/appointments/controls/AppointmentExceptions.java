package api.projeto_sus_backend.appointments.controls;

import api.projeto_sus_backend.application.controls.ApplicationException;
import org.springframework.http.HttpStatus;

/**
 * The Class AppointmentExceptions
 *
 * @author João Lucas Silva de Sousa
 * @sincer 03/07/2024
 */
public class AppointmentExceptions {

    public static class NotFound extends ApplicationException {
        public NotFound() {
            super("Consulta não encontrada", HttpStatus.BAD_REQUEST);
        }
    }

    public static class InvalidHourAndDate extends ApplicationException {
        public InvalidHourAndDate() {
            super("Não é possível marcar a consulta devido a data e horário", HttpStatus.BAD_REQUEST);
        }
    }

    public static class FullTime extends ApplicationException {
        public FullTime() {
            super("O horário já esta cheio, tente outro", HttpStatus.BAD_REQUEST);
        }
    }

    public static class HasAppointmentAtDate extends ApplicationException {
        public HasAppointmentAtDate() {
            super("Não é possível cadastrar mais de uma consulta por dia", HttpStatus.BAD_REQUEST);
        }
    }

    public static class HasAppointmentAtWeek extends ApplicationException {
        public HasAppointmentAtWeek() {
            super("Não é possível cadastrar mais de uma consulta por semana", HttpStatus.BAD_REQUEST);
        }
    }
}
