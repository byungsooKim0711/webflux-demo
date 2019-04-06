package org.kimbs.webflux.repository;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.kimbs.webflux.model.Customer;

@Mapper
public interface CustomerMapper {
    public Optional<Customer> selectCustomerById(Long id);

    public List<Customer> selectCustomers(List<String> list);
    
    public Integer inserCustomer(Customer customer);

    public Integer updateCustomer(Customer customer);

    public void deleteCustomer(Long id);
}