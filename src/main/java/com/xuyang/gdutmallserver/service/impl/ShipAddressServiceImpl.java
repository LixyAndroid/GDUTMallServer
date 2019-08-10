package com.xuyang.gdutmallserver.service.impl;

import com.xuyang.gdutmallserver.mapper.ShipAddressMapper;
import com.xuyang.gdutmallserver.model.ShipAddress;
import com.xuyang.gdutmallserver.service.ShipAddressService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("shipAddressService")
public class ShipAddressServiceImpl
        implements ShipAddressService {

    @Autowired
    private ShipAddressMapper shipAddressMapper;

    public int addShipAddress(ShipAddress model) {
        return this.shipAddressMapper.insert(model);
    }

    public List<ShipAddress> getShipAddress(Integer userId) {
        return this.shipAddressMapper.selectShipAddressByUserId(userId);
    }

    public int modifyShipAddress(ShipAddress model) {
        return this.shipAddressMapper.updateByPrimaryKeySelective(model);
    }

    public int deleteShipAddress(Integer id) {
        return this.shipAddressMapper.deleteByPrimaryKey(id);
    }

    public ShipAddress getShipAddressById(Integer id) {
        return this.shipAddressMapper.selectByPrimaryKey(id);
    }
}
