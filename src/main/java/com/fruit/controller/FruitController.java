package com.fruit.controller;

import com.fruit.pojo.Fruit;
import com.fruit.pojo.Staff;
import com.fruit.service.FruitService;
import com.fruit.utils.Page;
import com.fruit.utils.Result;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("api/fruit/")
public class FruitController {

    @Autowired
    private FruitService fruitService;

    /**
     * 水果列表
     *
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
     *
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
     *
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
     *
     * @param fruitId
     * @return
     */
    @RequestMapping("deleteFruit{fruitId}.html")
    public String deleteFruit(@PathVariable String fruitId, String[] ids) {

        if (StringUtils.isNotBlank(fruitId)) {
            fruitService.deleteFruitById(fruitId);
        } else {
            for (String id : ids) {
                fruitService.deleteFruitById(id);
            }
        }


        return "redirect:/api/fruit/fruitList.html";
    }

    /**
     * 跳转到编辑水果
     *
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
     *
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

    @RequestMapping("getFruitNum.action")
    @ResponseBody
    public Result getFruitNum(HttpSession session) {
        Staff staff = (Staff) session.getAttribute("staff");

        List<Fruit> fruitList = fruitService.getAllFruitById();
        String msg = "";
        if (staff.getStaffRole() != 2) {

            for (Fruit fruit : fruitList) {
                Integer fruitNum = fruit.getFruitNum();
                if (fruitNum < 30) {
                    msg += fruit.getFruitName() + "的库存量少于30！\r\n";
                }
            }
            if (StringUtils.isNotBlank(msg)) {
                msg += "请尽快进货！";
            }

        }
        return Result.build(200, msg);
    }

}
