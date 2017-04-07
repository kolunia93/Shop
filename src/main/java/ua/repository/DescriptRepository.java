package ua.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import ua.entity.Descript;

public interface DescriptRepository extends JpaRepository<Descript, Integer>,JpaSpecificationExecutor<Descript>{
//
//	@Query("SELECT d FROM Descript b")
//	List<Descript> findAll();
	
	@Query("SELECT c FROM Descript c WHERE c.descript = ?1")
	Descript findByName(String name);

	@Transactional
	@Modifying
	@Query("UPDATE Item i SET i.descript='1' WHERE i.descript.id=?1")
	void removDependencies(int id);
	
	

}
