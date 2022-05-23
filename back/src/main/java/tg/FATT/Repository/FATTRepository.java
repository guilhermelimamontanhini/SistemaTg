package tg.FATT.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import tg.FATT.Model.FATT;

public interface FATTRepository extends JpaRepository<FATT, Long> {
	
	Optional<FATT> findByIdFatt(Long id);

}
