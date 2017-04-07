package ua.service.implementation;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.dto.filter.ItemFilter;
import ua.dto.form.ItemForm;
import ua.entity.Descript;
import ua.entity.Item;
import ua.entity.Name;
import ua.entity.Size;
import ua.entity.Years;
import ua.repository.DescriptRepository;
import ua.repository.ItemsRepository;
import ua.repository.NameRepository;
import ua.repository.SizeRepository;
import ua.repository.YearsRepository;
import ua.service.FileWriter;
import ua.service.FileWriter.Folder;
import ua.service.ItemService;
import ua.service.specification.ItemBoy;
import ua.service.specification.ItemGirl;
import ua.service.specification.ItemSpecification;


@Service
public class ItemServiceImpl implements ItemService{

	@Autowired
	private FileWriter fileWriter;
	@Autowired
	private ItemsRepository itemReposity;
	@Autowired
	private YearsRepository yearsReposity;
	@Autowired
	private SizeRepository sizeReposity;
	@Autowired
	private NameRepository nameReposity;
	@Autowired
	private DescriptRepository descriptReposity;

	@Override
	@Transactional(readOnly=true)
	public ItemForm findOne(int id) {
		Item entity=itemReposity.findOne(id);
		ItemForm form=new ItemForm();
		form.setCategory(entity.getCategory());
		form.setDescript(entity.getDescript().getDescript());
		form.setGender(entity.getGender());
		form.setId(entity.getId());
		form.setSizeMax(String.valueOf(entity.getSize().getMax()));
		form.setSizeMin(String.valueOf(entity.getSize().getMin()));
		form.setMaxYears(String.valueOf(entity.getYears().getMax()));
		form.setMinYears(String.valueOf(entity.getYears().getMin()));
		form.setName(entity.getName().getName());
		form.setPrice(String.valueOf(entity.getPrice()));
		form.setRemain(String.valueOf(entity.getRemain()));
		form.setSeason(entity.getSeason());
		form.setVender(entity.getVender());
		return form;
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<Item> findAll() {
		
		return itemReposity.findAll();
	}

	@Override
	public void save(Item items) {
		itemReposity.save(items);	
	}

	@Override
	public void delete(int id) {
		itemReposity.delete(id);
	}

	@Override
	@Transactional
	public void save(ItemForm itemForm) {
		Item item = new Item();
		Years years=new Years();
		Size size=new Size();
		Name name=new Name();
		Descript descript=new Descript();
		item.setID(itemForm.getId());
		item.setCategory(itemForm.getCategory());
		item.setGender(itemForm.getGender());
		item.setPrice(new BigDecimal(itemForm.getPrice().replace(',', '.')));
		item.setRemain(Integer.valueOf(itemForm.getRemain()));
		item.setVender(itemForm.getVender());
		item.setSeason(itemForm.getSeason());
		
		size.setMin(Integer.valueOf(itemForm.getSizeMin()));
		size.setMax(Integer.valueOf(itemForm.getSizeMax()));
		years.setMin(Integer.valueOf(itemForm.getMinYears()));
		years.setMax(Integer.valueOf(itemForm.getMaxYears()));
		name.setName(itemForm.getName());
		descript.setDescript(itemForm.getDescript());
		
		yearsReposity.save(years);
		sizeReposity.save(size);
		nameReposity.save(name);
		descriptReposity.save(descript);

		item.setDescript(descript);
		item.setName(name);
		item.setSize(size);
		item.setYears(years);
		item=itemReposity.saveAndFlush(item);
		if(fileWriter.write(Folder.ITEM, itemForm.getFile(), item.getId())){
			if(item.getVersion()==null)item.setVersion(0);
			else item.setVersion(item.getVersion()+1);
		}
		
		itemReposity.save(item);
		}

	@Override
	public Page<Item> findAll(ItemFilter filter, Pageable pageable) {
		Page<Item> items = itemReposity.findAll(new ItemSpecification(filter),pageable);
		return items;
	}

	@Override
	public Item findById(int id) {
		return itemReposity.findOne(id);
	}

	@Override
	public Page<Item> findAllBoy(ItemFilter filter, Pageable pageable) {
		Page<Item> items=itemReposity.findAll(new ItemBoy(filter),pageable);
		return items;
	}

	@Override
	public Page<Item> findAllGirls(ItemFilter filter, Pageable pageable) {
		Page<Item> items=itemReposity.findAll(new ItemGirl(filter),pageable);
		return items;
	}

	@Override
	public int findCount(int id) {
		Integer count = itemReposity.findCount(id);
		if(count==null)return 0;
		return count;
	}

	@Override
	public Item findOneItem(int id) {
		// TODO Auto-generated method stub
		return itemReposity.findOne(id);
	}
	
}