package ua.service.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import ua.dto.filter.ItemFilter;
import ua.entity.Item;

public class ItemBoy implements Specification<Item> {
	private final ItemFilter filter;
	
	private final List<Predicate> predicates = new ArrayList<>();

	public ItemBoy(ItemFilter filter) {
		filter.setGenderSerch(1);
		this.filter = filter;
	}
	
	private void fetch(Root<Item> root, CriteriaQuery<?> query) {
		if(query.getResultType()!=Long.class){
			root.fetch("vender", JoinType.LEFT);
			root.fetch("name", JoinType.LEFT);
			root.fetch("years", JoinType.LEFT);
			root.fetch("size", JoinType.LEFT);
			root.fetch("category", JoinType.LEFT);
			root.fetch("descript", JoinType.LEFT);
			
		}
	}
	
	private void filterByGender(Root<Item> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		if(!(filter.getGenderSerch()==null)){
			predicates.add(root.get("gender").in(filter.getGenderSerch()));
		}
		
	}
	
	@Override
	public Predicate toPredicate(Root<Item> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		fetch(root, query);
		query.distinct(true);
		filterByGender(root, query, cb);
		if(predicates.isEmpty())return null;
		Predicate[] array = new Predicate[predicates.size()];
		predicates.toArray(array);
		return cb.and(array);
	}
}
