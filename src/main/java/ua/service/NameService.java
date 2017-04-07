package ua.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.dto.filter.BasicFilter;
import ua.entity.Name;

public interface NameService {
	Name findOne(int id);
	Name findOne(String name);
	List<Name>findAll();
	void save(Name name);
	void delete(int id);
	Page<Name> findAll(BasicFilter filter, Pageable pageable);
	void removDependencies(int id);
}
