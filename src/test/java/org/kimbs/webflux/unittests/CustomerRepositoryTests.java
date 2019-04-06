package org.kimbs.webflux.unittests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.kimbs.webflux.model.Customer;
import org.kimbs.webflux.repository.CustomerMapper;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@MybatisTest
@ActiveProfiles("test")
public class CustomerRepositoryTests {

    @Autowired
    CustomerMapper customerMapper;

    @Test
    public void shouldFindNoCustomersIfrepositoryIsEmpty() throws Exception {
        /* arrange */
        List<String> search = new ArrayList<>();

        /* act */
        List<Customer> customers = customerMapper.selectCustomers(search);

        /* assert */
        assertThat(customers).isEmpty();
    }

    @Test
    public void shouldStoreACustomer() throws Exception {
        /* arrange */
        Customer customer = new Customer(1L, "Byungsoo", "kim", 26);

        /* act */
        int actual = customerMapper.inserCustomer(customer);

        /* assert */
        assertEquals(1, actual);
    }

    @Test
    public void shouldFindCustomerById() throws Exception {
        /* arrange */
        Customer c1 = new Customer(1000L, "TEST01", "TEST01", 99);
        Customer c2 = new Customer(1001L, "TEST02", "TEST02", 77);

        customerMapper.inserCustomer(c1);
        customerMapper.inserCustomer(c2);

        /* act */
        Customer found = customerMapper.selectCustomerById(1000L).get();

        /* assert */
        assertEquals(c1, found);
    }

    @Test
    public void shouldFindAllCustomers() throws Exception {
        /* arrange */
        Customer c1 = new Customer(100L, "TEST01", "TEST01", 99);
        Customer c2 = new Customer(101L, "TEST02", "TEST02", 77);

        customerMapper.inserCustomer(c1);
        customerMapper.inserCustomer(c2);

        /* act */
        List<Customer> customers = customerMapper.selectCustomers(null);

        /* assert */
        assertThat(customers).hasSize(2).contains(c1, c2);
    }

    @Test
    public void shouldChangedCustomerInfomation() throws Exception {
        /* arrange */
        Customer customer1 = new Customer(1000L, "TEST01", "TEST01", 99);
        Customer customer2 = new Customer(1001L, "TEST02", "TEST02", 99);
        customerMapper.inserCustomer(customer1);
        customerMapper.inserCustomer(customer2);

        customer1.setAge(26);
        customer1.setFirstname("Byungsoo");
        customer1.setLastname("Kim");

        /* act */
        int actual = customerMapper.updateCustomer(customer1);

        /* assert */
        assertEquals(1, actual);

        Customer found = customerMapper.selectCustomerById(1000L).get();
        assertEquals(customer1, found);
    }
}