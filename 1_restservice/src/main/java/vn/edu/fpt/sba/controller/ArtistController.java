package vn.edu.fpt.sba.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.fpt.sba.dto.response.ArtistDetailResponseDTO;
import vn.edu.fpt.sba.dto.request.ArtistRequestDTO;
import vn.edu.fpt.sba.entities.Artist;
import vn.edu.fpt.sba.exception.ExampleArtistException;
import vn.edu.fpt.sba.service.impl.ArtistService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/artists")
public class ArtistController {
    private ArtistService artistService;

    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }

    @GetMapping("")
    public List<ArtistDetailResponseDTO> artistList(){
        return artistService.findAll();
    }

    @GetMapping("/{id}")
    public ArtistDetailResponseDTO getArtistById(@PathVariable Integer id){
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
