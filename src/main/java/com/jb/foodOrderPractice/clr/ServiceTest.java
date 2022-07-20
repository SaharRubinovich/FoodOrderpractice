package com.jb.foodOrderPractice.clr;

import com.jb.foodOrderPractice.beans.Category;
import com.jb.foodOrderPractice.beans.Company;
import com.jb.foodOrderPractice.beans.Customer;
import com.jb.foodOrderPractice.beans.Item;
import com.jb.foodOrderPractice.services.*;
import com.jb.foodOrderPractice.util.TablePrinter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Order(1)
@RequiredArgsConstructor
public class ServiceTest implements CommandLineRunner {
    private final AdminService ADMIN_SERVICE;
    private final CompanyService COMPANY_SERVICE;
    private final CustomerService CUSTOMER_SERVICE;
    private final LoginService LOGIN_SERVICE;

    @Override
    public void run(String... args) throws Exception {
        Company company1 = Company.builder()
                .email("Company1@test.com")
                .name("Company1")
                .password("1234")
                .build();
        Company company2 = Company.builder()
                .email("Company2@test.com")
                .name("Company2")
                .password("1234")
                .build();
        Customer customer1 = Customer.builder()
                .email("customer1@test.com")
                .name("customer1")
                .password("1234")
                .phoneNumber("0555555555")
                .build();
        Customer customer2 = Customer.builder()
                .email("customer2@test.com")
                .name("customer2")
                .password("1234")
                .phoneNumber("0555557777")
                .build();
        Item item1 = Item.builder()
                .title("Test 1")
                .category(Category.BREAKFAST)
                .description("A breakfast test")
                .price(45.5)
                .build();
        Item item2 = Item.builder()
                .title("Test 2")
                .category(Category.FISH)
                .description("A fish test")
                .price(73)
                .build();

        ClientType clientType = LOGIN_SERVICE.login("admin@admin.com", "admin", "Admin");
        System.out.println(clientType.getClass());
        ADMIN_SERVICE.addCompany(company1);
        ADMIN_SERVICE.addCompany(company2);
        ADMIN_SERVICE.addCustomer(customer1);
        ADMIN_SERVICE.addCustomer(customer2);
        TablePrinter.print(ADMIN_SERVICE.getAllCompanies());
        TablePrinter.print(ADMIN_SERVICE.getAllCustomers());
        company2.setPassword("123456");
        ADMIN_SERVICE.updateCompany(company2);
        TablePrinter.print(ADMIN_SERVICE.getSingleCompany(company2.getId()));
        customer2.setPhoneNumber("052777777");
        ADMIN_SERVICE.updateCustomer(customer2);
        TablePrinter.print(ADMIN_SERVICE.getSingleCustomer(customer2.getId()));

        clientType = LOGIN_SERVICE.login(company1.getEmail(), company1.getPassword(), "Company");
        System.out.println(clientType.getClass());
        COMPANY_SERVICE.addItem(item1);
        TablePrinter.print(COMPANY_SERVICE.getCompanyDetails());
        clientType = LOGIN_SERVICE.login(company2.getEmail(), company2.getPassword(), "Company");
        COMPANY_SERVICE.addItem(item2);
        TablePrinter.print(COMPANY_SERVICE.getCompanyDetails());
        TablePrinter.print(COMPANY_SERVICE.getItemsByCategory(Category.FISH.toString()));

        clientType = LOGIN_SERVICE.login(customer1.getEmail(), customer1.getPassword(), "Customer");
        System.out.println(clientType.getClass());
        CUSTOMER_SERVICE.purchaseItem(List.of(item1,item2));
        TablePrinter.print(CUSTOMER_SERVICE.getCustomerDetails());
        TablePrinter.print(CUSTOMER_SERVICE.getItemsByPrice(50));

        ADMIN_SERVICE.deleteCompany(company1.getId());
        ADMIN_SERVICE.deleteCompany(company2.getId());
        ADMIN_SERVICE.deleteCustomer(customer1.getId());
        ADMIN_SERVICE.deleteCustomer(customer2.getId());
    }
}
