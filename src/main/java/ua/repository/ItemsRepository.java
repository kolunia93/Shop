package ua.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ua.entity.Item;




public interface ItemsRepository extends JpaRepository<Item, Integer>,JpaSpecificationExecutor<Item>{

	@Query("SELECT i FROM Item i LEFT JOIN FETCH i.name  LEFT JOIN FETCH i.vender  LEFT JOIN FETCH i.category  LEFT JOIN FETCH i.descript  LEFT JOIN FETCH i.size  LEFT JOIN FETCH i.years ")
	List<Item> findAll();	
	
	@Query("SELECT i FROM Item i LEFT JOIN FETCH i.name  LEFT JOIN FETCH i.vender  LEFT JOIN FETCH i.category  LEFT JOIN FETCH i.descript  LEFT JOIN FETCH i.size "+
	" LEFT JOIN FETCH i.years LEFT JOIN FETCH i.ordersItem  WHERE i.id=:id")
	Item findOne(@Param("id")int id);
	

	@Query(value="SELECT i FROM Item i LEFT JOIN FETCH i.descript LEFT JOIN FETCH i.name LEFT JOIN FETCH i.vender LEFT JOIN FETCH i.size LEFT JOIN FETCH i.years",
			countQuery="SELECT count(i.id) FROM Item i")
	Page<Item> findAll(Pageable pageable);

	@Query("SELECT u.count FROM User u  WHERE u.id=?1")
	Integer findCount(int id);

	@Query("SELECT u FROM Item u LEFT JOIN FETCH u.comments c WHERE c.id=:id")
	Item findByComment(@Param("id")int id);
	
}
