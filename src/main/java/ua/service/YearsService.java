package ua.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.dto.filter.YearsFilter;
import ua.dto.form.YearsForm;
import ua.entity.Years;

public interface YearsService {
	YearsForm findOne(int id);
	Years findOne(Double min);
	Years findOne(String name);
	List<Years>findAll();
	void save(YearsForm years);
	void delete(int id);
	Page<Years> findAll(YearsFilter filter, Pageable pageable);
	void removDependencies(int id);

}
