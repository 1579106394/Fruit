package com.bishe.fruit.controller;

import com.bishe.fruit.pojo.Address;
import com.bishe.fruit.service.AddressService;
import com.bishe.fruit.utils.Page;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("api/address/")
public class AddressController {

    @Autowired
    private AddressService addressService;

    /**
     * 获取送货地址列表
     * @param model
     * @param p
     * @return
     */
    @RequestMapping("addressList.html")
    public String addressList(Model model, Page<Address> p) {

        Page<Address> page = addressService.getAddressList(p);

        model.addAttribute("page", page);

        return "address/address-list";
    }


    /**
     * 添加收货地址
     */
    @RequestMapping("addAddress.html")
    public String addAddress(Address address) {

        addressService.addAddress(address);

        return "redirect:/api/address/addressList.html";
    }

    /**
     * 编辑收货地址
     */
    @RequestMapping("editAddress.html")
    public String editAddress(Address address) {

        addressService.editAddress(address);

        return "redirect:/api/address/addressList.html";
    }

    /**
     * 根据id删除收货地址
     * @return
     */
    @RequestMapping("deleteAddress{addressId}.html")
    public String deleteAddress(@PathVariable String addressId, String[] ids) {

        if(StringUtils.isNotBlank(addressId)) {
            addressService.deleteAddressById(addressId);
        }else {
            for (String id : ids) {
                addressService.deleteAddressById(id);
            }
        }


        return "redirect:/api/address/addressList.html";
    }

    /**
     * 跳转到编辑收货地址
     * @return
     */
    @RequestMapping("toEdit{addressId}.html")
    public String toEdit(@PathVariable String addressId, Model model) {
        Address address = addressService.getAddressById(addressId);
        model.addAttribute("address", address);
        return "address/address-edit";
    }

}
