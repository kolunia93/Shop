package ua.service.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import ua.dto.filter.SizeFilter;
import ua.entity.Size;


public class SizeSpecification implements Specification<Size>{
	
	private final SizeFilter filter;
	
	private final List<Predicate> predicates = new ArrayList<>();
	
	public SizeSpecification(SizeFilter filter) {
		this.filter = filter;
	}
	
	private void filterBySize(Root<Size> root, CriteriaQuery<?> query, CriteriaBuilder cb){
		if(filter.getMax()!=null&&filter.getMin()!=null){
			predicates.add(cb.between(root.get("max"), filter.getMin(), filter.getMax()));

		}else if(filter.getMax()!=null){
			predicates.add(cb.lessThanOrEqualTo(root.get("max"), filter.getMax()));

		}else if(filter.getMin()!=null){
			predicates.add(cb.greaterThanOrEqualTo(root.get("max"), filter.getMin()));

		}
		if(filter.getMax()!=null&&filter.getMin()!=null){

			predicates.add(cb.between(root.get("min"), filter.getMin(), filter.getMax()));
		}else if(filter.getMax()!=null){

			predicates.add(cb.lessThanOrEqualTo(root.get("min"), filter.getMax()));
		}else if(filter.getMin()!=null){

			predicates.add(cb.greaterThanOrEqualTo(root.get("min"), filter.getMin()));
		}
	}

	@Override
	public Predicate toPredicate(Root<Size> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		fetch(root, query);
		query.distinct(true);
		filterBySize(root, query, cb);
		if(predicates.isEmpty())return null;
		Predicate[] array = new Predicate[predicates.size()];
		predicates.toArray(array);
		return cb.and(array);
		
	}

	private void fetch(Root<Size> root, CriteriaQuery<?> query) {
		if(query.getResultType()!=Long.class){
			
		}
		
	}
	

	
	
}