package vn.edu.fpt.sba.dto.response;

public record AlbumDetailResponseDTO(
        Integer id,
        String title,
        ArtistResponseDTO artist
) {
}
