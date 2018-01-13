package com.taotao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.util.IDUtils;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.pojo.*;
import com.taotao.service.ItemService;
import com.taotao.mapper.TbItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 *
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private TbItemMapper itemMapper;
    @Autowired
    private TbItemDescMapper tbItemDescMapper;
    @Autowired
    private TbItemParamItemMapper tbItemParamItemMapper;

    @Override
    public TbItem getItemById(long itemId) {
        TbItemExample example = new TbItemExample();
        TbItemExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(itemId);
        List<TbItem> list = itemMapper.selectByExample(example);
        if(list != null && list.size() > 0) {
            TbItem item = list.get(0);
            return item;
        }
        return null;
    }

    @Override
    public EUDataGridResult getItemList(int page, int rows) {
        TbItemExample example = new TbItemExample();
        PageHelper.startPage(page, rows);
        List<TbItem> list = itemMapper.selectByExample(example);
        PageInfo<TbItem> pageInfo = new PageInfo(list);

        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    @Override
    public TaotaoResult createItem(TbItem tbItem, String desc, String itemParams) throws Exception {
        long itemId = IDUtils.genItemId();
        tbItem.setId(itemId);
        tbItem.setCreated(new Date());
        tbItem.setUpdated(new Date());
        //商品状态，1-正常，2-下架，3-删除
        tbItem.setStatus((byte) 1);
        itemMapper.insert(tbItem);
        TaotaoResult taotaoResult = createItemDesc(itemId, desc);
        createItemParam(itemId, itemParams);
        if(taotaoResult.getStatus() != 200) {
            throw new Exception();
        }
        return TaotaoResult.ok();
    }

    private TaotaoResult createItemParam(long itemId, String paramData) {
        TbItemParamItem paramItem = new TbItemParamItem();
        paramItem.setCreated(new Date());
        paramItem.setUpdated(new Date());
        paramItem.setItemId(itemId);
        paramItem.setParamData(paramData);
        tbItemParamItemMapper.insert(paramItem);
        return TaotaoResult.ok();
    }

    private TaotaoResult createItemDesc(long itemId, String desc) {
        TbItemDesc itemDesc = new TbItemDesc();
        itemDesc.setItemId(itemId);
        itemDesc.setItemDesc(desc);
        itemDesc.setCreated(new Date());
        itemDesc.setUpdated(new Date());
        tbItemDescMapper.insert(itemDesc);
        return TaotaoResult.ok();
    }
}
