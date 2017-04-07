package ua.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.dto.filter.BasicFilter;
import ua.entity.Country;
import ua.repository.CountryRepository;
import ua.service.CountryService;
import ua.service.specification.CountrySpecification;

@Service
public class CountryServiceImpl implements CountryService{

	@Autowired
	private CountryRepository reposity;

	@Override
	@Transactional(readOnly=true)
	public Country findOne(int id) {
		return reposity.findOne(id);
	}

	@Override
	public void save(Country country) {
		reposity.save(country);
	}

	@Override
	public void delete(int id) {
		reposity.delete(id);		
	}

	@Override
	public Country findOne(String name) {
		
		return reposity.findByName(name);
	}

	@Override
	public Page<Country> findAll(BasicFilter filter, Pageable pageable) {
		
		return reposity.findAll(new CountrySpecification(filter),pageable);
	}

	@Override
	public List<Country> findAll() {
		return reposity.findAll();
	}

	@Override
	public void removDependencies(int id) {
		reposity.removDependencies(id);	
	}
}