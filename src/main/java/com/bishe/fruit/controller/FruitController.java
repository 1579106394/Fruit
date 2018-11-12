package com.bishe.fruit.controller;

import com.bishe.fruit.pojo.Fruit;
import com.bishe.fruit.service.FruitService;
import com.bishe.fruit.utils.Page;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("api/fruit/")
public class FruitController {

    @Autowired
    private FruitService fruitService;

    /**
     * 水果列表
     * @param model
     * @param p
     * @return
     */
    @RequestMapping("fruitList.html")
    public String fruitList(Model model, Page<Fruit> p) {

        Page<Fruit> page = fruitService.getFruitList(p);

        model.addAttribute("page", page);

        return "fruit/fruit-list";
    }

    /**
     * 添加水果(进货)
     * @param fruit
     * @return
     */
    @RequestMapping("addFruit.html")
    public String addFruit(Fruit fruit) {

        fruitService.addFruit(fruit);

        return "redirect:/api/fruit/fruitList.html";
    }

    /**
     * 编辑水果
     * @param fruit
     * @return
     */
    @RequestMapping("editFruit.html")
    public String editFruit(Fruit fruit) {

        fruitService.editFruit(fruit);

        return "redirect:/api/fruit/fruitList.html";
    }

    /**
     * 根据id删除水果
     * @param fruitId
     * @return
     */
    @RequestMapping("deleteFruit{fruitId}.html")
    public String deleteFruit(@PathVariable String fruitId, String[] ids) {

        if(StringUtils.isNotBlank(fruitId)) {
            fruitService.deleteFruitById(fruitId);
        }else {
            for (String id : ids) {
                fruitService.deleteFruitById(id);
            }
        }


        return "redirect:/api/fruit/fruitList.html";
    }

    /**
     * 跳转到编辑水果
     * @param fruitId
     * @param model
     * @return
     */
    @RequestMapping("toEdit{fruitId}.html")
    public String toEdit(@PathVariable String fruitId, Model model) {
        Fruit fruit = fruitService.getFruitById(fruitId);
        model.addAttribute("fruit", fruit);
        return "fruit/fruit-edit";
    }

    /**
     * 跳转到进货
     * @param fruitId
     * @param model
     * @return
     */
    @RequestMapping("toAddNum{fruitId}.html")
    public String toAddNum(@PathVariable String fruitId, Model model) {
        Fruit fruit = fruitService.getFruitById(fruitId);
        model.addAttribute("fruit", fruit);
        return "fruit/fruit-add-num";
    }

}
