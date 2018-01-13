package com.taotao.controller;

import com.taotao.service.ItemParamItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ItemParamItemController {


    @Autowired
    private ItemParamItemService itemParamItemService;

    @RequestMapping("/showitem/{itemid}")
    public String getItemParamItem(@PathVariable long itemid, Model model) {
        String result = itemParamItemService.getItemParamByItemId(itemid);
        model.addAttribute("itemParam", result);
        return "item";
    }
}
