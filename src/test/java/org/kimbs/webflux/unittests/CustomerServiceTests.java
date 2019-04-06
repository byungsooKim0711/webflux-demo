package org.kimbs.webflux.unittests;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.kimbs.webflux.model.Customer;
import org.kimbs.webflux.repository.CustomerMapper;
import org.kimbs.webflux.service.CustomerService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class CustomerServiceTests {

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

        List<String> search = new ArrayList<>();

        when(customerMapper.selectCustomers(search)).thenReturn(customers);

        /* act */
        List<Customer> res = service.getAllCustomers(search).collectList().block();

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

        List<Customer> conditionalCustomers = new ArrayList<>();
        conditionalCustomers.add(c1);
        conditionalCustomers.add(c2);

        List<String> search = new ArrayList<>();
        search.add("i");
        when(customerMapper.selectCustomers(search)).thenReturn(conditionalCustomers);

        /* act */
        List<Customer> res = service.getAllCustomers(search).collectList().block();
        
        /* assert */
        assertEquals(2, res.size());
        assertEquals("admin", res.get(1).getFirstname());
        assertEquals("admin", res.get(1).getLastname());
        assertEquals(61, res.get(1).getAge());
    }
}