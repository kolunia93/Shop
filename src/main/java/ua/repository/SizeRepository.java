package ua.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import ua.entity.Size;

public interface SizeRepository extends JpaRepository<Size, Integer>,JpaSpecificationExecutor<Size>{

	@Transactional
	@Modifying
	@Query("UPDATE Item i SET i.size='1' WHERE i.size.id=?1")
	void removDependencies(int id);
	

}
