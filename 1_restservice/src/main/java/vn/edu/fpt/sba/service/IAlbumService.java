package vn.edu.fpt.sba.service;

import vn.edu.fpt.sba.dto.response.AlbumDetailResponseDTO;
import vn.edu.fpt.sba.entities.Album;

import java.util.List;

public interface IAlbumService {
     List<Album> findAll();
     AlbumDetailResponseDTO findByID(Integer id);
     Album save (Album albumInput);
     Album update (Integer id, Album albumInput);
     void delete (Integer id);
}
