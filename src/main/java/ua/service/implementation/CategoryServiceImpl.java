package ua.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.dto.filter.BasicFilter;
import ua.entity.Category;
import ua.repository.CategoryRepository;
import ua.service.CategoryService;
import ua.service.specification.CategorySpecification;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryRepository reposity;

	@Override
	@Transactional(readOnly=true)
	public Category findOne(int id) {
		return reposity.findOne(id);
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<Category> findAll() {	
		return reposity.findAll();
	}

	@Override
	public void save(Category category) {
		reposity.save(category);
		
	}

	@Override
	public void delete(int id) {
		reposity.delete(id);
		
	}

	@Override
	public Category findOne(String name) {
	
		return reposity.findByName(name);
	}

	@Override
	public Page<Category> findAll(BasicFilter filter, Pageable pageable) {
		
		return reposity.findAll(new CategorySpecification(filter),pageable);
	}

	@Override
	public void removDependencies(int id) {
		reposity.removDependencies(id);
		
	}
	
}