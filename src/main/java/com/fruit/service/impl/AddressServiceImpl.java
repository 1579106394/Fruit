package com.fruit.service.impl;

import com.fruit.mapper.AddressMapper;
import com.fruit.mapper.LogMapper;
import com.fruit.pojo.Address;
import com.fruit.pojo.Staff;
import com.fruit.service.AddressService;
import com.fruit.utils.DateUtils;
import com.fruit.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@Transactional
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressMapper addressMapper;

    @Autowired
    private LogMapper logMapper;


    @Override
    public Page<Address> getAddressList(Page<Address> p) {
        if(p.getCurrentPage() == null) {
            p.setCurrentPage(1);
        }

        if (p.getCurrentCount() == null) {
            p.setCurrentCount(10);
        }

        Integer currentPage = p.getCurrentPage();
        Integer currentCount = p.getCurrentCount();

        Integer index = (currentPage - 1) * currentCount;
        p.setIndex(index);

        List<Address> addressList = addressMapper.getAddressList(p);
        p.setList(addressList);

        Integer totalCount = addressMapper.getAddressCount(p);
        p.setTotalCount(totalCount);

        Integer totalPage = (int)Math.ceil(totalCount * 1.0 / currentCount);
        p.setTotalPage(totalPage);



        return p;
    }

    @Override
    public void addAddress(Address address) {
        // 补全属性
        address.setAddressId(UUID.randomUUID().toString());
        address.setAddressCreatedTime(DateUtils.newDate());
        address.setAddressFlag(1);
        addressMapper.addAddress(address);
    }

    @Override
    public void editAddress(Address address) {
        addressMapper.editAddress(address);
    }

    @Override
    public void deleteAddressById(String addressId) {
        addressMapper.deleteAddressById(addressId);
    }

    @Override
    public Address getAddressById(String addressId) {
        return addressMapper.getAddressById(addressId);
    }

    @Override
    public List<Address> getAllAddressList() {
        return addressMapper.getAllAddressList();
    }

    @Override
    public void addAddressForStaff(String staffId, String[] ids) {
        Map<String, Object> map = new HashMap<>();
        for (String id : ids) {
            // 每次循环都查一下这个记录有没有，如果没有再新增
            Address address = addressMapper.getAddressByStaffAndAddress(staffId, id);
            map.put("staffAddId", UUID.randomUUID().toString());
            map.put("staffId", staffId);
            map.put("addressId", id);
            addressMapper.addStaffAddress(map);
        }
    }

    @Override
    public List<Address> getAddressListByStaff(Staff staff) {
        return addressMapper.getAddressListByStaff(staff);
    }

}
