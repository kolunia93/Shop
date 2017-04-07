package ua.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.dto.filter.ItemFilter;
import ua.dto.form.ItemForm;
import ua.entity.Item;


public interface ItemService {
	ItemForm findOne(int id);
	List<Item>findAll();
	void save(ItemForm item);
	void save(Item items);
	void delete(int id);
	Page <Item>findAll(ItemFilter filter, Pageable pageable);
	Item findById(int id);
	Page<Item> findAllBoy(ItemFilter filter, Pageable pageable);
	Page<Item> findAllGirls(ItemFilter filter, Pageable pageable);
	int findCount(int userId);
	Item findOneItem(int id);



}
