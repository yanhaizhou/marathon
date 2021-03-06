package com.sf.marathon.service.impl;

import com.sf.marathon.dao.CustomerDao;
import com.sf.marathon.dao.PackGroupDao;
import com.sf.marathon.domain.Customer;
import com.sf.marathon.domain.PackGroup;
import com.sf.marathon.domain.ProMarketBase;
import com.sf.marathon.dto.CustomerDto;
import com.sf.marathon.service.GroupTaskBiz;
import com.sf.marathon.service.IPackGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.UUID;

@Transactional
@Service
public class PackGroupService implements IPackGroupService {
    @Autowired
    private PackGroupDao packGroupDao;

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private GroupTaskBiz groupTaskBiz;

    @Override
    public void savePackGroup(CustomerDto customerDto) {
        PackGroup packGroup = packGroupDao.getOne(customerDto.getPackId());
        ProMarketBase proMarketBase = packGroup.getProMarketBase();
        Byte finish = 1;

        if (finish.equals(packGroup.getFinish()) || packGroup.getGroupNum() >= proMarketBase.getGroupLimit()) {
            packGroup = groupTaskBiz.createPackGroup(proMarketBase);
        }

        packGroup.setGroupNum(packGroup.getGroupNum() + 1);
        if (packGroup.getGroupNum().equals(proMarketBase.getGroupLimit())) {
            packGroup.setFinish(finish);
            packGroup.setFinishTime(new Date());
        }
        packGroupDao.save(packGroup);

        Customer customer = genCustomer(customerDto);
        customer.setPackGroup(packGroup);
        customerDao.save(customer);
    }

    private Customer genCustomer(CustomerDto customerDto) {
        Customer customer = new Customer();
        customer.setGroupTime(customerDto.getGroupTime());
        customer.setAddress(customerDto.getAddress());
        customer.setRegion(customerDto.getRegion());
        customer.setUserName(customerDto.getUserName());
        customer.setId(UUID.randomUUID().toString());
        return customer;
    }
}
