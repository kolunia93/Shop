package ua.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import ua.entity.Category;
public interface CategoryRepository extends JpaRepository<Category, Integer>,JpaSpecificationExecutor<Category> {

	@Query("SELECT c FROM Category c WHERE c.category =?1")
	Category findByName(String name);

	
	@Transactional
	@Modifying
	@Query("UPDATE Item i SET i.category='1' WHERE i.category.id=:id")
	void removDependencies(@Param("id")int id);
		
	
}

