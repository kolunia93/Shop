package ua.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import ua.entity.Name;



public interface NameRepository extends JpaRepository<Name, Integer>,JpaSpecificationExecutor<Name>{

	
	@Query("SELECT c FROM Name c WHERE c.name = ?1")
	Name findByName(String name);
	
	@Transactional
	@Modifying
	@Query("UPDATE Item i SET i.name='1' WHERE i.name.id=?1")
	void removDependencies(int id);



}
