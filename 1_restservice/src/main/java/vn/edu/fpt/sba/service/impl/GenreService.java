package vn.edu.fpt.sba.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.edu.fpt.sba.dto.response.GenreResponseDTO;
import vn.edu.fpt.sba.entities.Genre;
import vn.edu.fpt.sba.repositories.GenreRepository;
import vn.edu.fpt.sba.service.IGenreService;

@Service
public class GenreService implements IGenreService {
    private final GenreRepository genreRepository;

    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    private GenreResponseDTO toResponseDTO(Genre genre) {
        return new GenreResponseDTO(
                genre.getGenreId(),
                genre.getName(),
                genre.getDescription(),
                genre.getPopularityScore(),
                genre.getIsActive(),
                genre.getCreatedAt(),
                genre.getUpdatedAt()
        );
    }

    @Override
    public Page<GenreResponseDTO> findAll(Pageable pageable) {
        Page<Genre> genres = genreRepository.findAll(pageable);
        return genres.map(this::toResponseDTO);
    }

    @Override
    public Genre findById(int id) {
        return genreRepository.findById(id).orElse(null);
    }

    @Override
    public Genre save(Genre genre) {
        return null;
    }

    @Override
    public Genre update(int id, Genre genre) {
        return null;
    }

    @Override
    public void delete(int id) {

    }
}
