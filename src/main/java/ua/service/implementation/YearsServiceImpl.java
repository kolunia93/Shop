package ua.service.implementation;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.dto.filter.YearsFilter;
import ua.dto.form.YearsForm;
import ua.entity.Years;
import ua.repository.YearsRepository;
import ua.service.YearsService;
import ua.service.specification.YearsSpecification;

@Service
public class YearsServiceImpl implements YearsService{

	@Autowired
	private YearsRepository reposity;

	@Override
	@Transactional(readOnly=true)
	public YearsForm findOne(int id) {
		Years entity=reposity.findOne(id);
		YearsForm form=new YearsForm();
		form.setId(entity.getId());
		form.setMinYears(String.valueOf(entity.getMin()));
		form.setMaxYears(String.valueOf(entity.getMax()));
		return form;
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<Years> findAll() {	
		return reposity.findAll();
	}

	@Override
	public void save(YearsForm form) {
		Years entity=new Years();
		entity.setID(form.getId());
		entity.setItems(form.getItems());
		entity.setMax(Integer.valueOf(form.getMaxYears()));
		entity.setMin(Integer.valueOf(form.getMinYears()));
		reposity.save(entity);
	
	}

	@Override
	public void delete(int id) {
		reposity.delete(id);
		
	}

	@Override
	public Years findOne(String name) {
		return reposity.findByName(name);
	}

	@Override
	public Years findOne(Double min) {
		
		return reposity.findByMin(min);
	}

	@Override
	public Page<Years>findAll(YearsFilter filter, Pageable pageable) {
		return reposity.findAll(new YearsSpecification(filter),pageable);
	}

	@Override
	public void removDependencies(int id) {
		reposity.removDependencies(id);
		
	}
	
}