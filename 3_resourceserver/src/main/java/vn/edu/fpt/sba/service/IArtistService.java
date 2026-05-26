package vn.edu.fpt.sba.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.edu.fpt.sba.dto.response.ArtistDetailResponseDTO;
import vn.edu.fpt.sba.entities.Artist;

import java.util.List;

public interface IArtistService {
    Page<ArtistDetailResponseDTO> findAll(Pageable pageable);
    ArtistDetailResponseDTO findById(Integer id);
    Artist save(Artist artistInput);
    Artist update(Integer id, Artist artistInput);
    void delete(Integer id);
}
