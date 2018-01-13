package com.taotao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbItemParamMapper;
import com.taotao.pojo.TbItemParam;
import com.taotao.pojo.TbItemParamExample;
import com.taotao.pojo.TbItemParamExample.Criteria;
import com.taotao.service.ItemParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ItemParamServiceImpl implements ItemParamService {


    @Autowired
    private TbItemParamMapper tbItemParamMapper;


    @Override
    public EUDataGridResult list(Integer page, Integer rows) {
        TbItemParamExample paramExample = new TbItemParamExample();
        PageHelper.startPage(page, rows);
        List<TbItemParam> list = tbItemParamMapper.selectByExampleWithBLOBs(paramExample);
        PageInfo<TbItemParam> pageInfo = new PageInfo<>(list);
        EUDataGridResult euDataGridResult = new EUDataGridResult();
        euDataGridResult.setTotal(pageInfo.getTotal());
        euDataGridResult.setRows(pageInfo.getList());
        return euDataGridResult;
    }

    @Override
    public TaotaoResult getItemParamByCid(long itemCatid) {
        TbItemParamExample paramExample = new TbItemParamExample();
        Criteria criteria = paramExample.createCriteria();
        criteria.andIdEqualTo(itemCatid);
        List<TbItemParam> list = tbItemParamMapper.selectByExampleWithBLOBs(paramExample);
        if(list != null && list.size() > 0) {
            return TaotaoResult.ok(list.get(0));
        }
        return TaotaoResult.ok();
    }

    @Override
    public TaotaoResult insertItemParam(TbItemParam itemParam) {
        itemParam.setCreated(new Date());
        itemParam.setUpdated(new Date());
        tbItemParamMapper.insert(itemParam);
        return TaotaoResult.ok();
    }
}
