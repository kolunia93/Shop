package ua.service.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import ua.dto.filter.VenderFilter;
import ua.entity.Vender;

public class VenderSpecification implements Specification<Vender>{
	
	
	private final VenderFilter filter;
	private final List<Predicate> predicates = new ArrayList<>();
	
	public VenderSpecification(VenderFilter filter) {
		this.filter = filter;
	}
	
	private void fetch(Root<Vender> root, CriteriaQuery<?> query) {
		if(query.getResultType()!=Long.class){
			root.fetch("country", JoinType.LEFT);
		}
	}
	private void filterByCountry(Root<Vender> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		if(!(filter.getCountryId().isEmpty())){
			predicates.add(root.get("country").in(filter.getCountryId()));
		}
	}
	private void filterByVender(Root<Vender> root, CriteriaQuery<?> query, CriteriaBuilder cb){
		if(!filter.getVender().isEmpty()){
			predicates.add(cb.like(root.get("vender"), filter.getVender()+"%"));
		}
	}
	

	@Override
	public Predicate toPredicate(Root<Vender> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		fetch(root, query);
		query.distinct(true);
		filterByCountry(root, query, cb);
		filterByVender(root, query, cb);
		if(predicates.isEmpty())return null;
		Predicate[] array = new Predicate[predicates.size()];
		predicates.toArray(array);
		return cb.and(array);
		
	}

	



	
	
	

}
