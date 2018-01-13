package com.taotao.service.impl;

import com.taotao.common.pojo.EUTreeNode;
import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemCatServiceImpl implements ItemCatService{

    @Autowired
    private TbItemCatMapper tbItemCatMapper;


    @Override
    public List<EUTreeNode> getItemCatList(long parentId) {
        TbItemCatExample example = new TbItemCatExample();
        //设置查询条件
        TbItemCatExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        //执行查询
        List<TbItemCat> list = tbItemCatMapper.selectByExample(example);
        //分类列表转换成EUTreeNode列表
        List<EUTreeNode> euTreeNodes = new ArrayList<>();
        for(TbItemCat itemCat : list) {
            EUTreeNode euTreeNode = new EUTreeNode();
            euTreeNode.setId(itemCat.getId());
            euTreeNode.setText(itemCat.getName());
            euTreeNode.setState(itemCat.getIsParent() ? "closed" : "open");
            euTreeNodes.add(euTreeNode);
        }

        return euTreeNodes;
    }
}
