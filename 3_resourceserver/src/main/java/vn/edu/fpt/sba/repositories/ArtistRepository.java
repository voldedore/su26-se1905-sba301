package vn.edu.fpt.sba.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.fpt.sba.entities.Artist;
import vn.edu.fpt.sba.entities.User;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Integer> {

}
