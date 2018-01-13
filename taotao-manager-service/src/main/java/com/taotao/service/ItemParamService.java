package com.taotao.service;

import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItemParam;

public interface ItemParamService {

    EUDataGridResult list(Integer page, Integer size);

    TaotaoResult getItemParamByCid(long itemCatid);

    TaotaoResult insertItemParam(TbItemParam itemParam);
}
