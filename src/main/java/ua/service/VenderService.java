package ua.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.dto.filter.VenderFilter;
import ua.entity.Vender;

public interface VenderService {
	Vender findOne(int id);
	Vender findOne(String name);
	List<Vender>findAll();
	void save(Vender vender);
	void delete(int id);
	Page<Vender> findAll(VenderFilter filter, Pageable pageable);
	void removDependencies(int id);
}
