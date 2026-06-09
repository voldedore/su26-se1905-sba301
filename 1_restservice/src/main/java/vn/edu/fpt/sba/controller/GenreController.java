package vn.edu.fpt.sba.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import vn.edu.fpt.sba.dto.response.GenreResponseDTO;
import vn.edu.fpt.sba.dto.response.PageResponseDTO;
import vn.edu.fpt.sba.entities.Genre;
import vn.edu.fpt.sba.service.IGenreService;

@RestController
@RequestMapping("/api/genres")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GenreController {

    IGenreService genreService;

    // List GET /api/genres
    @GetMapping
    public PageResponseDTO<GenreResponseDTO> genreList(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "5") Integer size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<GenreResponseDTO> genrePage = genreService.findAll(pageable);
        return PageResponseDTO.of(genrePage);
    }

    @GetMapping("/test")
    public PageResponseDTO<GenreResponseDTO> genreList2(
            @ParameterObject @PageableDefault(page = 0, size = 5) Pageable pageable
    ) {
        return PageResponseDTO.of(genreService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public Genre getGenreById(@PathVariable int id) {
        return genreService.findById(id);
    }
}
