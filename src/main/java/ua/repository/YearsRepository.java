package ua.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import ua.entity.Years;

public interface YearsRepository extends JpaRepository<Years, Integer>,JpaSpecificationExecutor<Years>{

	@Query("SELECT y FROM Years y WHERE y.min= ?1 AND y.max= ?1")
	Years findByName(String name);

	@Query("SELECT y FROM Years y WHERE y.min=?1")
	Years findByMin(Double min);

	@Transactional
	@Modifying
	@Query("UPDATE Item i SET i.years='1' WHERE i.years.id=?1")
	void removDependencies(int id);


}
