package ua.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import ua.entity.Country;

public interface CountryRepository extends JpaRepository<Country, Integer>, JpaSpecificationExecutor<Country>{

	@Query("SELECT c FROM Country c WHERE c.country = ?1")
	Country findByName(String name);
	
	@Transactional
	@Modifying
	@Query("UPDATE Vender i SET i.country='1' WHERE i.country.id=:id")
	void removDependencies(@Param("id")int id);

	
}
