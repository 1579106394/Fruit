package com.fruit.service.impl;

import com.fruit.mapper.AddressMapper;
import com.fruit.mapper.LogMapper;
import com.fruit.mapper.StaffMapper;
import com.fruit.pojo.Address;
import com.fruit.pojo.Log;
import com.fruit.pojo.Staff;
import com.fruit.service.AddressService;
import com.fruit.utils.*;
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
    private StaffMapper staffMapper;

    @Autowired
    private LogMapper logMapper;

    @Autowired
    private JedisClient jedisClient;

    @Override
    public Page<Address> getAddressList(Page<Address> p) {
        if (p.getCurrentPage() == null) {
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

        Integer totalPage = (int) Math.ceil(totalCount * 1.0 / currentCount);
        p.setTotalPage(totalPage);

        String staffName = JsonUtils.jsonToPojo(jedisClient.get("staff"), Staff.class).getStaffName();
        String time = DateUtils.newDate();
        String article = staffName + "在" + time + "访问了外送地址列表";
        Log log = LogUtils.newLog(time, article);
        logMapper.addLog(log);

        return p;
    }

    @Override
    public void addAddress(Address address) {
        // 补全属性
        address.setAddressId(UUID.randomUUID().toString());
        address.setAddressCreatedTime(DateUtils.newDate());
        address.setAddressFlag(1);
        addressMapper.addAddress(address);

        String staffName = JsonUtils.jsonToPojo(jedisClient.get("staff"), Staff.class).getStaffName();
        String time = DateUtils.newDate();
        String article = staffName + "在" + time + "添加了外送地址" + address.getAddressName();
        Log log = LogUtils.newLog(time, article);
        logMapper.addLog(log);
    }

    @Override
    public void editAddress(Address address) {
        Address a = addressMapper.getAddressById(address.getAddressId());
        addressMapper.editAddress(address);

        String staffName = JsonUtils.jsonToPojo(jedisClient.get("staff"), Staff.class).getStaffName();
        String time = DateUtils.newDate();
        String article = staffName + "在" + time + "编辑了了外送地址，将" + a.getAddressName() + "改为了" + address.getAddressName();
        Log log = LogUtils.newLog(time, article);
        logMapper.addLog(log);
    }

    @Override
    public void deleteAddressById(String addressId) {
        Address address = addressMapper.getAddressById(addressId);
        addressMapper.deleteAddressById(addressId);

        String staffName = JsonUtils.jsonToPojo(jedisClient.get("staff"), Staff.class).getStaffName();
        String time = DateUtils.newDate();
        String article = staffName + "在" + time + "删除了外送地址" + address.getAddressName();
        Log log = LogUtils.newLog(time, article);
        logMapper.addLog(log);
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
        Staff staff = staffMapper.getStaffById(staffId);
        for (String id : ids) {
            // 每次循环都查一下这个记录有没有，如果没有再新增
            Address address = addressMapper.getAddressByStaffAndAddress(staffId, id);
            Address a = addressMapper.getAddressById(id);
            map.put("staffAddId", UUID.randomUUID().toString());
            map.put("staffId", staffId);
            map.put("addressId", id);
            addressMapper.addStaffAddress(map);

            String staffName = JsonUtils.jsonToPojo(jedisClient.get("staff"), Staff.class).getStaffName();
            String time = DateUtils.newDate();
            String article = staffName + "在" + time + "给用户" + staff.getStaffName() + "分配了收货地址" + a.getAddressName();
            Log log = LogUtils.newLog(time, article);
            logMapper.addLog(log);
        }
    }

    @Override
    public List<Address> getAddressListByStaff(Staff staff) {
        return addressMapper.getAddressListByStaff(staff);
    }

}
