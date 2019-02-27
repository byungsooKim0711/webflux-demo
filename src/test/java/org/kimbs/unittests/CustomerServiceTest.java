package org.kimbs.unittests;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.Test;
import org.kimbs.webflux.model.Customer;
import org.kimbs.webflux.repository.CustomerMapper;
import org.kimbs.webflux.service.CustomerService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class CustomerServiceTest {

    @Mock
    CustomerMapper customerMapper;

    @InjectMocks
    CustomerService service;

    @Test
    public void testFindAllInService() throws Exception {
        MockitoAnnotations.initMocks(this);

        /* arrange */
        List<Customer> customers = new ArrayList<>();
        Customer c = new Customer(1234L, "Byungsoo", "Kim", 26);
        customers.add(c);

        Map<String, String> tmp = new HashMap<>();
        when(customerMapper.selectCustomers(tmp)).thenReturn(customers);

        /* act */
        List<Customer> res = service.getAllCustomers(tmp).collectList().block();

        /* assert */
        assertEquals(1, res.size());
        assertEquals("Byungsoo", res.get(0).getFirstname());
        assertEquals("Kim", res.get(0).getLastname());
        assertEquals(26, res.get(0).getAge());
    }

    @Test
    public void testFindByIdInService() throws Exception {
        MockitoAnnotations.initMocks(this);

        /* arrange */
        Customer customer = new Customer(1234L, "Byungsoo", "Kim", 26);
        when(customerMapper.selectCustomerById(1234L)).thenReturn(Optional.of(customer));

        /* act */
        Customer res = service.getCustomerById(1234L).block();

        /* assert */
        assertEquals(customer, res);
    }
}