package vn.edu.fpt.sba.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.fpt.sba.dto.response.AlbumDetailResponseDTO;
import vn.edu.fpt.sba.service.IAlbumService;

@RestController
@RequestMapping("/api/albums")
public class AlbumController {
    private final IAlbumService albumService;

    public AlbumController(IAlbumService albumService) {
        this.albumService = albumService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlbumDetailResponseDTO> getAlbumByID(@PathVariable Integer id) {
        AlbumDetailResponseDTO album = albumService.findByID(id);
        if (album == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(album);
    }
}
