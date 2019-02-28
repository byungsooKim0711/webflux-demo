package org.kimbs.webflux.repository;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MapperForTestCode {
    public void deleteAllCustomers();
}