package com.guigu.springcloud.dao;

import com.guigu.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PaymentDao {

    int create(Payment payment);

    Payment queryPaymentById(@Param("id") Long id);
}
