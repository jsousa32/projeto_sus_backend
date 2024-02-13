package api.sus.utils;

import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * The Class Mapper
 *
 * @author João Lucas Silva de Sousa
 * @sincer 13/02/2024
 */
public class Mapper {

    private final ModelMapper mapper = new ModelMapper();


    public <T> T converter(Object from, Class<T> to) {
        if (Objects.isNull(from)) {
            return null;
        }

        return mapper.map(from, to);
    }

    public <S, T> List<T> converter(List<S> from, Class<T> to) {
        if (Objects.isNull(from) || from.isEmpty()) {
            return null;
        }

        return from.stream().map(value -> mapper.map(value, to)).collect(Collectors.toList());
    }
}
