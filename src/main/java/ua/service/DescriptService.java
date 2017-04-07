package ua.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.dto.filter.BasicFilter;
import ua.entity.Descript;

public interface DescriptService {

		Descript findOne(int id);
		Descript findOne(String name);
		List<Descript>findAll();
		void save(Descript name);
		void delete(int id);
		Page<Descript> findAll(BasicFilter filter, Pageable pageable);
		void removDependencies(int id);

}
