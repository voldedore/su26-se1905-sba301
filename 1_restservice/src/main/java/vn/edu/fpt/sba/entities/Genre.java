package vn.edu.fpt.sba.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer genreId;
    private String name;

    @Column(name = "Description")
    private String description;

    @Column(name = "PopularityScore", nullable = false)
    @ColumnDefault("50")
    private BigDecimal popularityScore;

    @Column(name = "IsActive", nullable = false)
    @ColumnDefault("1")
    private Boolean isActive;

    @Column(name = "CreatedAt", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "UpdatedAt")
    private LocalDateTime updatedAt;

    public Genre(Integer genreId, String name, String description) {
        this.genreId = genreId;
        this.name = name;
        this.description = description;
    }
}
