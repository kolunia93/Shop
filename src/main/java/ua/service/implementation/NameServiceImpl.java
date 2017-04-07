package ua.service.implementation;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.dto.filter.BasicFilter;
import ua.entity.Name;
import ua.repository.NameRepository;
import ua.service.NameService;
import ua.service.specification.NameSpecification;


@Service
public class NameServiceImpl implements NameService{

	@Autowired
	private NameRepository reposity;

	@Override
	@Transactional(readOnly=true)
	public Name findOne(int id) {
		return reposity.findOne(id);
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<Name> findAll() {	
		return reposity.findAll();
	}

	@Override
	public void save(Name name) {
		reposity.save(name);	
	}

	@Override
	public void delete(int id) {
		reposity.delete(id);
	}

	@Override
	public Name findOne(String name) {
		return reposity.findByName(name);
	}

	@Override
	public Page<Name> findAll(BasicFilter filter, Pageable pageable) {
		return reposity.findAll(new NameSpecification(filter),pageable);
	}

	@Override
	public void removDependencies(int id) {
		reposity.removDependencies(id);
		
	}

}