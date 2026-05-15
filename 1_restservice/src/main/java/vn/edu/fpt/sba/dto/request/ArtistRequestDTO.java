package vn.edu.fpt.sba.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ArtistRequestDTO(
        @NotBlank
        @Size(min = 3, max = 100, message = "name min 3 character")
        String name) {
}
