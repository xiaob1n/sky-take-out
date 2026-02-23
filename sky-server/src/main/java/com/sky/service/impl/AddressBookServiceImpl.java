package com.sky.service.impl;

import com.sky.context.BaseContext;
import com.sky.entity.AddressBook;
import com.sky.mapper.AddressBookMapper;
import com.sky.service.AddressBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressBookServiceImpl implements AddressBookService {

    @Autowired
    private AddressBookMapper addressBookMapper;

    /**
     *
     * @param addressBook
     */
    @Override
    public void addAdress(AddressBook addressBook) {
        Long userId = BaseContext.getCurrentId();
        addressBook.setUserId(userId);
        addressBookMapper.insert(addressBook);
    }

    /**
     * 查询当前用户的地址信息
     * @return
     */
    @Override
    public List<AddressBook> list() {
        Long userId = BaseContext.getCurrentId();
        List<AddressBook> list = addressBookMapper.list(userId);
        return list;
    }

    /**
     * 设置默认地址
     */
    @Override
    public void setDefault(Long id) {
        Long userId = BaseContext.getCurrentId();
        addressBookMapper.setAllUndefault(userId);
        addressBookMapper.setDefault(id);
    }

    /**
     * 查询默认地址
     * @return
     */
    @Override
    public AddressBook getDefault() {
        Long userId = BaseContext.getCurrentId();
        AddressBook addressBook = addressBookMapper.getDefaultAddress(userId);
        return addressBook;
    }

    /**
     * 根据id修改地址
     * @param addressBook
     */
    @Override
    public void update(AddressBook addressBook) {
        addressBookMapper.update(addressBook);
    }

    /**
     * 根据id查询地址
     * @param id
     * @return
     */
    @Override
    public AddressBook getById(Long id) {
        AddressBook addressBook = addressBookMapper.getById(id);
        return addressBook;
    }

    /**
     * 根据id删除地址
     * @param id
     */
    @Override
    public void deleteById(Long id) {
        addressBookMapper.deleteById(id);
    }
}
