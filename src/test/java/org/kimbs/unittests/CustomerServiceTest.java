package org.kimbs.unittests;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.Before;
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

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindAllInService() throws Exception {

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
        
        /* arrange */
        Customer customer = new Customer(1234L, "Byungsoo", "Kim", 26);
        when(customerMapper.selectCustomerById(1234L)).thenReturn(Optional.of(customer));

        /* act */
        Customer res = service.getCustomerById(1234L).block();

        /* assert */
        assertEquals(customer, res);
    }

    @Test
    public void testConditionalFindAllInService() throws Exception {
        
        /* arrange */
        List<Customer> customers = new ArrayList<>();
        Customer c1 = new Customer(1000L, "Byungsoo", "Kim", 26);
        Customer c2 = new Customer(1001L, "admin", "admin", 61);
        Customer c3 = new Customer(1001L, "test", "test", 99);
        customers.add(c1);
        customers.add(c2);
        customers.add(c3);

        Map<String, String> tmp = new HashMap<>();
        tmp.put("search", "i");

        List<Customer> conditionalCustomers = new ArrayList<>();
        conditionalCustomers.add(c1);
        conditionalCustomers.add(c2);

        when(customerMapper.selectCustomers(tmp)).thenReturn(conditionalCustomers);

        /* act */
        List<Customer> res = service.getAllCustomers(tmp).collectList().block();

        /* assert */
        assertEquals(2, res.size());
        assertEquals("admin", res.get(1).getFirstname());
        assertEquals("admin", res.get(1).getLastname());
        assertEquals(61, res.get(1).getAge());
    }
}