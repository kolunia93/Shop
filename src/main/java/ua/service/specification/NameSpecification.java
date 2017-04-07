package ua.service.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import ua.dto.filter.BasicFilter;
import ua.entity.Name;

public class NameSpecification implements Specification<Name>{
	private final BasicFilter filter;

	public NameSpecification(BasicFilter filter) {
		this.filter = filter;
	}

	@Override
	public Predicate toPredicate(Root<Name> root, CriteriaQuery<?> qery, CriteriaBuilder cb) {
		if(filter.getSearch().isEmpty()) return null;
		return cb.like(root.get("name"), filter.getSearch()+"%");
	}

}
