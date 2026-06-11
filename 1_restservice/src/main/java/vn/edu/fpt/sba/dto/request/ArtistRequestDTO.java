package vn.edu.fpt.sba.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ArtistRequestDTO(
        @NotBlank
        @Size(min = 3, max = 100, message = "Artist name must be at least 3 characters")
        String name) {
}
