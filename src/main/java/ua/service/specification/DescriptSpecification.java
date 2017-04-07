package ua.service.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import ua.dto.filter.BasicFilter;
import ua.entity.Descript;

public class DescriptSpecification implements Specification<Descript>{
	private final BasicFilter filter;

	public DescriptSpecification(BasicFilter filter) {
		this.filter = filter;
	}

	@Override
	public Predicate toPredicate(Root<Descript> root, CriteriaQuery<?> qery, CriteriaBuilder cb) {
		if(filter.getSearch().isEmpty()) return null;
		return cb.like(root.get("descript"), filter.getSearch()+"%");
	}

}

