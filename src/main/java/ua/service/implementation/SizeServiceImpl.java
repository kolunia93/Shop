package ua.service.implementation;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.dto.filter.SizeFilter;
import ua.dto.form.SizeForm;
import ua.entity.Size;
import ua.repository.SizeRepository;
import ua.service.SizeService;
import ua.service.specification.SizeSpecification;

@Service
public class SizeServiceImpl implements SizeService{

	@Autowired
	private SizeRepository reposity;

	@Override
	@Transactional(readOnly=true)
	public SizeForm findOne(int id) {
		Size entity=reposity.findOne(id);
		SizeForm form=new SizeForm();
		form.setId(entity.getId());
		form.setMinSize(String.valueOf(entity.getMin()));
		form.setMaxSize(String.valueOf(entity.getMax()));
		return form;
	}

	
	@Override
	@Transactional(readOnly=true)
	public List<Size> findAll() {	
		return reposity.findAll();
	}

	

	@Override
	public void delete(int id) {
		reposity.delete(id);
		
	}


	@Override
	public Size findOne(String name) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void save(SizeForm size) {
		Size entity=new Size();
		entity.setID(size.getId());
		entity.setItems(size.getItems());
		entity.setMax(Integer.parseInt(size.getMaxSize()));
		entity.setMin(Integer.parseInt(size.getMinSize()));
		reposity.save(entity);
		
	}


	@Override
	public Page<Size> findAll(SizeFilter filter, Pageable pageable) {
		
		return reposity.findAll(new SizeSpecification(filter),pageable);
	}


	@Override
	public void removDependencies(int id) {
		reposity.removDependencies(id);
		
		
	}
	
	
}