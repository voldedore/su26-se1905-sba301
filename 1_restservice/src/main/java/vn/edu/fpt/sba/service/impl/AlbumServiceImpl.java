package vn.edu.fpt.sba.service.impl;

import org.springframework.stereotype.Service;
import vn.edu.fpt.sba.dto.response.AlbumDetailResponseDTO;
import vn.edu.fpt.sba.dto.response.ArtistResponseDTO;
import vn.edu.fpt.sba.entities.Album;
import vn.edu.fpt.sba.repositories.AlbumRepository;
import vn.edu.fpt.sba.service.IAlbumService;

import java.util.List;

@Service
public class AlbumServiceImpl implements IAlbumService {
    private final AlbumRepository albumRepository;

    public AlbumServiceImpl(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    @Override
    public List<Album> findAll() {
        return List.of();
    }

    @Override
    public AlbumDetailResponseDTO findByID(Integer id) {
        Album album = albumRepository.findById(id).orElse(null);
        if (album != null) {
            return new AlbumDetailResponseDTO(
                    album.getId(),
                    album.getTitle(),
                    new ArtistResponseDTO(album.getArtist().getId(), album.getArtist().getName())
            );
        }
        return null;
    }

    @Override
    public Album save(Album albumInput) {
        return null;
    }

    @Override
    public Album update(Integer id, Album albumInput) {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }
}
