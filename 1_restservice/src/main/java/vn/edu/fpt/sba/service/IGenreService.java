package vn.edu.fpt.sba.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.edu.fpt.sba.dto.response.GenreResponseDTO;
import vn.edu.fpt.sba.entities.Genre;

public interface IGenreService {
    Page<GenreResponseDTO> findAll(Pageable pageable);
    Genre findById(int id);
    Genre save(Genre genre);
    Genre update(int id, Genre genre);
    void delete(int id);
}
