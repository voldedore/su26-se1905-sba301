package vn.edu.fpt.sba.service;

import vn.edu.fpt.sba.dto.response.ArtistDetailResponseDTO;
import vn.edu.fpt.sba.entities.Artist;

import java.util.List;

public interface IArtistService {
    List<ArtistDetailResponseDTO> findAll();
    ArtistDetailResponseDTO findById(Integer id);
    Artist save(Artist artistInput);
    Artist update(Integer id, Artist artistInput);
    void delete(Integer id);
}
