package com.done.service.impl;

import com.done.dao.ItemMapper;
import com.done.model.Item;
import com.done.service.IItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Done Lin on 2017/3/11.
 */
@Service
public class ItemServiceImpl implements IItemService {

    @Autowired
    private ItemMapper itemMapper;

    @Override
    public List<Item> getItemByOrder() {
        return itemMapper.queryItemListOrderByUpdate();
    }
}
