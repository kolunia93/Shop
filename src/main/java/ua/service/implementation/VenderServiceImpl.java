package ua.service.implementation;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.dto.filter.VenderFilter;
import ua.entity.Vender;
import ua.repository.VenderRepository;
import ua.service.VenderService;
import ua.service.specification.VenderSpecification;

@Service
public class VenderServiceImpl implements VenderService{

	@Autowired
	private VenderRepository reposity;

	@Override
	@Transactional(readOnly=true)
	public Vender findOne(int id) {
		return reposity.findOne(id);
	}

	
	@Override
	@Transactional(readOnly=true)
	public List<Vender> findAll() {	
		return reposity.findAll();
	}

	@Override
	public void save(Vender vender) {
		reposity.save(vender);
		
	}

	@Override
	public void delete(int id) {
		reposity.delete(id);
		
	}


	@Override
	public Vender findOne(String name) {
		return reposity.findByName(name);
	}



	@Override
	public Page<Vender> findAll(VenderFilter filter, Pageable pageable) {
		return reposity.findAll(new VenderSpecification(filter),pageable);
	}


	@Override
	public void removDependencies(int id) {
		reposity.removDependencies(id);
		
	}
	
	
}