package com.example.demo.mapper;

import com.example.demo.entity.TAccount;
import org.apache.ibatis.annotations.Param;

/**
 *  Mapper 接口
 * @author
 * @since 2019-01-13
 */
public interface TAccountMapper {
    int decreaseAccount(@Param("userId") String userId, @Param("amount") Double amount);
    int addAccount(TAccount tAccount);
}
