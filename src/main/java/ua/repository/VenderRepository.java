package ua.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import ua.entity.Vender;

public interface VenderRepository extends JpaRepository<Vender, Integer>,JpaSpecificationExecutor<Vender>{

	@Query("SELECT v FROM Vender v LEFT JOIN FETCH v.country WHERE v.id=:id")
	Vender findOne(@Param("id")int id);
	
	@Query("SELECT v FROM Vender v WHERE v.vender=?1")
	Vender findByName(String name);

	@Query(value="SELECT v FROM Vender v LEFT JOIN FETCH v.country",
			countQuery="SELECT count(v.id) FROM Vender v")
	Page<Vender> findAll(Pageable pageable);

	@Transactional
	@Modifying
	@Query("UPDATE Item i SET i.vender='1' WHERE i.vender.id=:id")
	void removDependencies(@Param("id")int id);
}
