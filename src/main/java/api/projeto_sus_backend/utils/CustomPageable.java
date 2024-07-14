package api.projeto_sus_backend.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Objects;
import java.util.Optional;

/**
 * The Class CustomPageable
 *
 * @author João Lucas Silva de Sousa
 * @sincer 01/07/2024
 */
public class CustomPageable extends PageRequest {

    protected CustomPageable(int pageNumber, int pageSize, Sort sort) {
        super(pageNumber, pageSize, sort);
    }

    public static Pageable getInstance(Integer page, Integer size, final String sorting) {
        page = Optional.ofNullable(page).orElse(0);
        size = Optional.ofNullable(size).orElse(5);

        if (Objects.isNull(sorting) || sorting.isBlank()) {
            return PageRequest.of(page, size);
        }

        final Sort sort = sorting.startsWith("-") ?
                Sort.by(Sort.Direction.DESC, sorting.replace("-", "")) : Sort.by(Sort.Direction.ASC, sorting);

        return PageRequest.of(page, size, sort);
    }
}
