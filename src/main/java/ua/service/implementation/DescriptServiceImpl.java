package ua.service.implementation;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.dto.filter.BasicFilter;
import ua.entity.Descript;
import ua.repository.DescriptRepository;
import ua.service.DescriptService;
import ua.service.specification.DescriptSpecification;


@Service
public class DescriptServiceImpl implements DescriptService{

	@Autowired
	private DescriptRepository reposity;

	@Override
	@Transactional(readOnly=true)
	public Descript findOne(int id) {
		return reposity.findOne(id);
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<Descript> findAll() {	
		return reposity.findAll();
	}

	@Override
	public void save(Descript Descript) {
		reposity.save(Descript);
		
	}

	@Override
	public void delete(int id) {
		reposity.delete(id);
		
	}


	@Override
	public Descript findOne(String name) {
		return reposity.findByName(name);
	}


	@Override
	public Page<Descript> findAll(BasicFilter filter, Pageable pageable) {
		return reposity.findAll(new DescriptSpecification(filter),pageable);
	}


	@Override
	public void removDependencies(int id) {
		reposity.removDependencies(id);
		
	}	
}