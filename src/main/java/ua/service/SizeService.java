package ua.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.dto.filter.SizeFilter;
import ua.dto.form.SizeForm;
import ua.entity.Size;

public interface SizeService {
	SizeForm findOne(int id);
	Size findOne(String name);
	List<Size>findAll();
	void save(SizeForm size);
	void delete(int id);
	Page<Size> findAll(SizeFilter filter, Pageable pageable);
	void removDependencies(int id);

}
