package ua.service.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import ua.dto.filter.YearsFilter;
import ua.entity.Years;

public class YearsSpecification implements Specification<Years>{
	
	private final YearsFilter filter;
	
	private final List<Predicate> predicates = new ArrayList<>();
	
	public YearsSpecification(YearsFilter filter) {
		this.filter = filter;
	}
	
	private void filterByYears(Root<Years> root, CriteriaQuery<?> query, CriteriaBuilder cb){
		if(filter.getMax()!=null&&filter.getMin()!=null){
			predicates.add(cb.between(root.get("max"), ((filter.getMin().intValue())), filter.getMax()));

		}else if(filter.getMax()!=null){
			predicates.add(cb.lessThanOrEqualTo(root.get("max"), filter.getMax()));

		}else if(filter.getMin()!=null){
			predicates.add(cb.greaterThanOrEqualTo(root.get("max"), filter.getMin()));

		}
		if(filter.getMax()!=null&&filter.getMin()!=null){

			predicates.add(cb.between(root.get("min"), filter.getMin().intValue(), filter.getMax()));
		}else if(filter.getMax()!=null){

			predicates.add(cb.lessThanOrEqualTo(root.get("min"), filter.getMax()));
		}else if(filter.getMin()!=null){

			predicates.add(cb.greaterThanOrEqualTo(root.get("min"), filter.getMin()));
		}
	}

	@Override
	public Predicate toPredicate(Root<Years> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		fetch(root, query);
		query.distinct(true);
		filterByYears(root, query, cb);
		if(predicates.isEmpty())return null;
		Predicate[] array = new Predicate[predicates.size()];
		predicates.toArray(array);
		return cb.and(array);
		
	}

	private void fetch(Root<Years> root, CriteriaQuery<?> query) {
		if(query.getResultType()!=Long.class){
			
		}
		
	}
}
