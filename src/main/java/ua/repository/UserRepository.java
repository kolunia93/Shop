package ua.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ua.dto.filter.ItemFilter;
import ua.entity.Item;
import ua.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User>{

	@Query("SELECT u FROM User u LEFT JOIN FETCH u.items WHERE u.username=:username")
	User findByUsername(@Param("username")String username);
	
	@Query(value="SELECT i FROM Item i LEFT JOIN FETCH i.descript LEFT JOIN FETCH i.name LEFT JOIN FETCH i.vender LEFT JOIN FETCH i.size LEFT JOIN FETCH i.years LEFT JOIN FETCH i.category",
			countQuery="SELECT count(i.id) FROM Item i")
	Page<User> findAll(Pageable pageable);

	@Query("SELECT u FROM User u WHERE u.email=:email")
	User findByEmail(@Param("email")String email);

	@Query("UPDATE User u SET u.items=?1")
	void saveItem(@Param("item")Item item);

	@Query("SELECT u FROM User u JOIN FETCH u.items i LEFT JOIN FETCH i.name")
			
	List<User> findAllBuy(Pageable pageable);

	@Query(value="SELECT i FROM User i JOIN FETCH i.items u JOIN FETCH u.name n WHERE i.username=:name")
	User findAllitems(@Param("name")String name);
	
	@Query(value="SELECT i FROM Item i LEFT JOIN FETCH i.descript LEFT JOIN FETCH i.name LEFT JOIN FETCH i.vender LEFT JOIN FETCH i.size LEFT JOIN FETCH i.years WHERE i.gender=ua.entity.Gender.FEMALE ",
			countQuery="SELECT count(i.id) FROM Item i")
	List<Item> findAllGirls(ItemFilter filter, Pageable pageable);

	@Query("SELECT u FROM User u LEFT JOIN FETCH u.comments c WHERE c.id=:id")
	User findByComment(@Param("id")int id);

	@Query("SELECT u FROM User u  WHERE u.fone=:fone")
	User findByFone(@Param("fone")String fone);

	@Query("SELECT distinct u FROM User u LEFT JOIN fetch u.items c WHERE c.id=:id")
	List<User> findByItems(@Param("id")int id);

	@Query("SELECT distinct u FROM User u LEFT JOIN FETCH u.items ")
	List<User> findAllFetch();

}