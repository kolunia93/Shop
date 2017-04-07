package ua.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ua.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer>,JpaSpecificationExecutor<Comment>{

	@Query("SELECT u FROM Comment u LEFT JOIN FETCH u.item i LEFT JOIN FETCH u.user  WHERE i.id=:id")
	List<Comment> findById(@Param("id")int id);

	@Query("SELECT u FROM Comment u LEFT JOIN FETCH u.user i  WHERE i.username=:name")
	List<Comment> findByUser(@Param("name")String name);

	@Query("SELECT distinct u FROM Comment u Inner JOIN u.item i WHERE i.id=:id")
	List<Comment> findByItems(@Param("id")int id);

	@Query("SELECT u FROM Comment u LEFT JOIN FETCH u.item ")
	List<Comment> findAllFetchItem();



}

