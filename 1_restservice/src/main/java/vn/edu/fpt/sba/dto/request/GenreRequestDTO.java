package vn.edu.fpt.sba.dto.request;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record GenreRequestDTO(
        @NotBlank
        @Size(min = 3, max = 100, message = "Genre name must be at least 3 characters")
        String name,

        @Size(max = 1000, message = "Description must not exceed 1000 characters")
        String description,

        @DecimalMin(value = "0.0", message = "Popularity score must be at least 0")
        @DecimalMax(value = "99.99", message = "Popularity score must not exceed 99.99")
        BigDecimal popularityScore,

        Boolean isActive
) {
}
