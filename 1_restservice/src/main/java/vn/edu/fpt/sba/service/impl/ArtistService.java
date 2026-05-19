package vn.edu.fpt.sba.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.edu.fpt.sba.dto.response.AlbumResponseDTO;
import vn.edu.fpt.sba.dto.response.ArtistDetailResponseDTO;
import vn.edu.fpt.sba.entities.Artist;
import vn.edu.fpt.sba.repositories.ArtistRepository;
import vn.edu.fpt.sba.service.IArtistService;

import java.util.List;

@Service
public class ArtistService implements IArtistService {
    private final ArtistRepository artistRepository;

    public ArtistService(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }
    @Override
    public Page<ArtistDetailResponseDTO> findAll(Pageable pageable) {
        Page<Artist> artist = artistRepository.findAll(pageable);
        return artist.map(this::toDetailResponseDto);

    }
    @Override
    public ArtistDetailResponseDTO findById(Integer id)
    {
        return artistRepository.findById(id).map(this::toDetailResponseDto).orElse(null);
    }
    @Override
    public Artist save(Artist a) {
        return artistRepository.save(a);
    }
    @Override
    public Artist update(Integer id, Artist artistInput){
        return artistRepository.findById(id).map(found -> {
            found.setName(artistInput.getName());
            return artistRepository.save(found);
        }).orElse(null);
    }
    @Override
    public void delete(Integer id){
        artistRepository.deleteById(id);
    }

    private ArtistDetailResponseDTO toDetailResponseDto(Artist artist) {
        List<AlbumResponseDTO> albums = artist.getAlbums().stream().map(
                album -> new AlbumResponseDTO(album.getId(), album.getTitle())
        ).toList();
        return new ArtistDetailResponseDTO(artist.getId(), artist.getName(), albums);
    }
}