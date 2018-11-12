package com.bishe.fruit.controller;

import com.bishe.fruit.pojo.Address;
import com.bishe.fruit.service.AddressService;
import com.bishe.fruit.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("api/address/")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @RequestMapping("addressList.html")
    public String addressList(Model model, Page<Address> p) {

        return "address/address-list";
    }


}
