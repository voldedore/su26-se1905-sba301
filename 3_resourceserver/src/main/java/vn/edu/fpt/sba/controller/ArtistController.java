package vn.edu.fpt.sba.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.fpt.sba.configuration.GenericConfig;
import vn.edu.fpt.sba.dto.response.ArtistDetailResponseDTO;
import vn.edu.fpt.sba.dto.request.ArtistRequestDTO;
import vn.edu.fpt.sba.dto.response.PageResponseDTO;
import vn.edu.fpt.sba.entities.Artist;
import vn.edu.fpt.sba.exception.ApiError;
import vn.edu.fpt.sba.exception.ExampleArtistException;
import vn.edu.fpt.sba.service.impl.ArtistService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/artists")
@Tag(name = "Artist APIs", description = "APIs for managing Artists")
public class ArtistController {
    private ArtistService artistService;

    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }

    @GetMapping("")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = PageResponseDTO.class)
                    )
            )
    })
    @Parameter(name = "page", description = "Page number (default: 1)", example = "1", schema = @Schema(type = "integer"))
    @Parameter(name = "size", description = "Page size (default: 10)", example = "10", schema = @Schema(type = "integer"))
    public PageResponseDTO<ArtistDetailResponseDTO> artistList(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "size", defaultValue = "5") Integer size
    ) {
        if (page == null) {
            page = 1;
        }
        if (size == null) {
            size = GenericConfig.DEFAULT_PAGINATION_SIZE;
        }
        Pageable pageable = PageRequest.of(page - 1, size); // Be careful this is a potential bug
        Page<ArtistDetailResponseDTO> pageRes = artistService.findAll(pageable);
        return PageResponseDTO.of(pageRes);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get artist by ID", description = "This API will return an artist by its ID")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ArtistDetailResponseDTO.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Artist not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class))
            )
    })
    public ArtistDetailResponseDTO getArtistById(@PathVariable Integer id) {
        // Vi du minh hoa
        if (id == 79) {
            throw new ExampleArtistException();
        }
        return artistService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Artist createArtist(@RequestBody @Valid ArtistRequestDTO artistRequestDto) {
        Artist artist = new Artist();
        artist.setName(artistRequestDto.name());
        return artistService.save(artist);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Artist> updateArtist(@PathVariable Integer id, @RequestBody Artist artist) {
        Artist updatedArtist = artistService.update(id, artist);
        if (updatedArtist == null) {
            ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updatedArtist);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArtist(@PathVariable Integer id) {
        artistService.delete(id);

        return ResponseEntity.noContent().build();
    }

//    @ExceptionHandler(RuntimeException.class)
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    public Map<String, String> handleNotFound(RuntimeException ex) {
//        return Map.of("error", ex.getMessage());
//    }
}
