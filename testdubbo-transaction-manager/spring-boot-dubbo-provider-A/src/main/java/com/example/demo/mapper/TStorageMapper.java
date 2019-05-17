package com.example.demo.mapper;

import com.example.demo.entity.TStorage;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author
 * @since 2019-01-13
 */
public interface TStorageMapper   {

    /**
     * 扣减商品库存
     * @Param: commodityCode 商品code  count扣减数量
     * @Return:
     */
    int addStorage(TStorage tStorage);

    int decreaseStorage(@Param("commodityCode") String commodityCode, @Param("count") Integer count);
}
