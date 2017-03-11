package com.done.dao;

import java.util.List;

import com.done.model.Item;
import com.github.abel533.mapper.Mapper;

public interface ItemMapper extends Mapper<Item>{
   
	/**
	 * 按照更新时间降序查找商品
	 * @return
	 */
	public List<Item> queryItemListOrderByUpdate();
}
