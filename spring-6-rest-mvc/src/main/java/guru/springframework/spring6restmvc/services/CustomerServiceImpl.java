package guru.springframework.spring6restmvc.services;

import guru.springframework.spring6restmvc.model.CustomerDTO;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class CustomerServiceImpl implements CustomerService {
    private Map<UUID, CustomerDTO> customerMap;
    public CustomerServiceImpl() {
        CustomerDTO customerDTO1 = CustomerDTO.builder()
                .id(UUID.randomUUID())
                .name("Customer 1")
                .version(1)
                .createdDate(LocalDateTime.now())
                .build();

        CustomerDTO customerDTO2 = CustomerDTO.builder()
                .id(UUID.randomUUID())
                .name("Customer 2")
                .version(1)
                .createdDate(LocalDateTime.now())
                .build();

        CustomerDTO customerDTO3 = CustomerDTO.builder()
                .id(UUID.randomUUID())
                .name("Customer 3")
                .version(1)
                .createdDate(LocalDateTime.now())
                .build();

        customerMap = new HashMap<>();
        customerMap.put(customerDTO1.getId(), customerDTO1);
        customerMap.put(customerDTO2.getId(), customerDTO2);
        customerMap.put(customerDTO3.getId(), customerDTO3);
    }

    @Override
    public CustomerDTO saveNewCustomer(CustomerDTO customerDTO) {
        CustomerDTO savedCustomerDTO = CustomerDTO.builder()
                .id(UUID.randomUUID())
                .version(1)
                .updateDate((LocalDateTime.now()))
                .createdDate(LocalDateTime.now())
                .name(customerDTO.getName())
                .build();

        customerMap.put(customerDTO.getId(), savedCustomerDTO);
        return savedCustomerDTO;
    }

    @Override
    public void updateCustomerById(UUID customerId, CustomerDTO customerDTO) {
        CustomerDTO existing = customerMap.get(customerId);
        existing.setName(customerDTO.getName());
//        customerMap.put(customerId, customer);
        customerMap.put(customerId, existing);
    }

    @Override
    public void deleteCustomerById(UUID customerId) {
        customerMap.remove(customerId);
    }

    @Override
    public void patchCustomerById(UUID customerId, CustomerDTO customerDTO) {
        CustomerDTO existing = customerMap.get(customerId);

        if (StringUtils.hasText(existing.getName())) {
            existing.setName(customerDTO.getName());
        }
    }

    @Override
    public CustomerDTO getCustomerById(UUID uuid) {
        return customerMap.get(uuid);
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return new ArrayList<>(customerMap.values());
    }
}
