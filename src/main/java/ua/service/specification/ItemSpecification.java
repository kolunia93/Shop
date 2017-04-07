package ua.service.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import ua.dto.filter.ItemFilter;
import ua.entity.Item;
import ua.entity.Name;
import ua.entity.Size;


public class ItemSpecification implements Specification<Item> {
private final ItemFilter filter;
	
	private final List<Predicate> predicates = new ArrayList<>();
	
	public ItemSpecification(ItemFilter filter) {
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
	
	private void filterByCategory(Root<Item> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		if(!(filter.getCategoryId().isEmpty())){
			if(!(filter.getCategoryId().contains(1))){
			predicates.add(root.get("category").in(filter.getCategoryId()));
		}}
	}
	
	private void filterByVender(Root<Item> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		
		if(!(filter.getVenderId().isEmpty())){
			if(!(filter.getVenderId().contains(1))){
			predicates.add(root.get("vender").in(filter.getVenderId()));
		}}
	}
	
	private void filterByGender(Root<Item> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		if(!(filter.getGenderSerch().isEmpty())){
			predicates.add(root.get("gender").in(filter.getGenderSerch()));
		}
		
	}
	
	private void filterBySeason(Root<Item> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		if(!(filter.getSeasonSerch().isEmpty())){
			System.out.println(filter.getSeasonSerch()+"season");
			predicates.add(root.get("season").in(filter.getSeasonSerch()));
		}else{
			System.out.println("season null");
		}
	}
	
	private void filterBySize(Root<Item> root, CriteriaQuery<?> query, CriteriaBuilder cb){
		Join<Item, Size> joinSize = root.join("size");
		if(filter.getMaxS()!=null&&filter.getMinS()!=null){
			predicates.add(cb.between(joinSize.get("max"), filter.getMinS(), filter.getMaxS()));

		}else if(filter.getMaxS()!=null){
			predicates.add(cb.lessThanOrEqualTo(joinSize.get("max"), filter.getMaxS()));

		}else if(filter.getMinS()!=null){
			predicates.add(cb.greaterThanOrEqualTo(joinSize.get("max"), filter.getMinS()));

		}
		if(filter.getMaxS()!=null&&filter.getMinS()!=null){

			predicates.add(cb.between(joinSize.get("min"), filter.getMinS(), filter.getMaxS()));
		}else if(filter.getMaxS()!=null){

			predicates.add(cb.lessThanOrEqualTo(joinSize.get("min"), filter.getMaxS()));
		}else if(filter.getMinS()!=null){

			predicates.add(cb.greaterThanOrEqualTo(joinSize.get("min"), filter.getMinS()));
		}
	}

	private void filterByPrice(Root<Item> root, CriteriaQuery<?> query, CriteriaBuilder cb){
		if(filter.getMaxPriceInt()!=null&&filter.getMinPriceInt()!=null){
			predicates.add(cb.between(root.get("price"), filter.getMinPriceInt(), filter.getMaxPriceInt()));
		}else if(filter.getMaxPriceInt()!=null){
			predicates.add(cb.lessThanOrEqualTo(root.get("price"), filter.getMaxPriceInt()));
		}else if(filter.getMinPriceInt()!=null){
			predicates.add(cb.greaterThanOrEqualTo(root.get("price"), filter.getMinPriceInt()));
		}
	}
	
	private void filterBySearch(Root<Item> root, CriteriaQuery<?> query, CriteriaBuilder cb){
		if(!filter.getSearch().isEmpty()){
			Join<Item, Name> join = root.join("name");
			predicates.add(cb.like(join.get("name"),(filter.getSearch()+"%")));
			
			
		
		}
	}
	
	@Override
	public Predicate toPredicate(Root<Item> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		fetch(root, query);
		query.distinct(true);
		filterByPrice(root, query, cb);	
		filterByCategory(root, query, cb);
		filterByVender(root, query, cb);
		filterByGender(root, query, cb);
		filterBySeason(root, query, cb);
		filterBySearch(root, query, cb);
		filterBySize(root, query, cb);
		if(predicates.isEmpty())return null;
		Predicate[] array = new Predicate[predicates.size()];
		predicates.toArray(array);
		return cb.and(array);
		
	}
}
