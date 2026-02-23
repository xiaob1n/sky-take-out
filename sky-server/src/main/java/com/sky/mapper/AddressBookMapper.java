package com.sky.mapper;

import com.sky.entity.AddressBook;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AddressBookMapper {

    /**
     * 新增地址
     * @param addressBook
     */
    @Insert("insert into sky_take_out.address_book (user_id, consignee, sex, phone, province_code, province_name, city_code, city_name, district_code, district_name, detail, label) " +
            "values " + "(#{userId},#{consignee},#{sex},#{phone},#{provinceCode},#{provinceName},#{cityCode},#{cityName},#{districtCode},#{districtName},#{detail},#{label})")
    void insert(AddressBook addressBook);

    /**
     * 查询当前用户的地址信息
     * @param userId
     * @return
     */
    @Select("select * from sky_take_out.address_book where user_id = #{userId}")
    List<AddressBook> list(Long userId);

    /**
     * 查询默认地址
     * @param userId
     * @return
     */
    @Select("select * from sky_take_out.address_book where user_id = #{userId} and is_default = 1")
    AddressBook getDefaultAddress(Long userId);

    /**
     * 将所有地址重置为不默认
     * @param userId
     */
    @Update("update sky_take_out.address_book set is_default = 0 where user_id = #{userId}")
    void setAllUndefault(Long userId);

    /**
     * 设置默认地址
     * @param id
     */
    @Update("update sky_take_out.address_book set is_default = 1 where id = #{id}")
    void setDefault(Long id);

    /**
     * 根据id修改地址
     * @param addressBook
     */
    void update(AddressBook addressBook);

    /**
     * 根据id查询地址
     * @param id
     * @return
     */
    @Select("select * from sky_take_out.address_book where id = #{id}")
    AddressBook getById(Long id);

    /**
     * 根据id删除地址
     * @param id
     */
    @Delete("delete from sky_take_out.address_book where id =#{id}")
    void deleteById(Long id);
}
