package vn.edu.fpt.sba.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Album {
    @Id
    @Column(name = "AlbumId")
    private int id;
    @Column(name ="Title")
    private String title;
    @ManyToOne
    @JoinColumn(name = "ArtistId")
    private Artist artist;
}
