package com.example.javabackend.api.create_account_register.repository;

import com.example.javabackend.entity.Accounts;
import com.example.javabackend.modules.account.repository.IAccountRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.function.Executable;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.format.DateTimeParseException;
import java.util.Date;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CreateAccountRepositoryTest {
    @MockBean
    private IAccountRepository accountRepository;
    private Accounts testAccount;

    @Before
    public void setUp() {
        testAccount = new Accounts();
        testAccount.setPassword("password");
        testAccount.setFullName("John Doe");
        testAccount.setPhoneNumber("1234567890");
        testAccount.setEmail("huynhphuocdat2@gmail.com");
        testAccount.setGender("Male");
        testAccount.setImageUrl("https://example.com/avatar.png");
        testAccount.setBrithday(new Date());
        testAccount.setAddress("123 Main St");
    }

    @Test
    public void testCreateAccountSuccess() {
        // Arrange
        Accounts savedAccount = new Accounts();
        savedAccount.setAccountID(1L);
        savedAccount.setPassword("password");
        savedAccount.setFullName("John Doe");
        savedAccount.setPhoneNumber("1234567890");
        savedAccount.setEmail("huynhphuocdat2@gmail.com");
        savedAccount.setGender("Male");
        savedAccount.setImageUrl("https://example.com/avatar.png");
        savedAccount.setBrithday(new Date());
        savedAccount.setAddress("123 Main St");

        Mockito.when(accountRepository.save(testAccount)).thenReturn(savedAccount);

        // Act
        Accounts result = accountRepository.save(testAccount);

        // Assert
        Assertions.assertNotNull(result);
        Assertions.assertEquals(savedAccount.getAccountID(), result.getAccountID());
        Assertions.assertEquals(savedAccount.getPassword(), result.getPassword());
        Assertions.assertEquals(savedAccount.getFullName(), result.getFullName());
        Assertions.assertEquals(savedAccount.getPhoneNumber(), result.getPhoneNumber());
        Assertions.assertEquals(savedAccount.getEmail(), result.getEmail());
        Assertions.assertEquals(savedAccount.getGender(), result.getGender());
        Assertions.assertEquals(savedAccount.getImageUrl(), result.getImageUrl());
        Assertions.assertEquals(savedAccount.getBrithday(), result.getBrithday());
        Assertions.assertEquals(savedAccount.getAddress(), result.getAddress());
    }

    @Test
    public void testCreateAccountInvalidDateFormat() {
        // Arrange
        Accounts testAccount = new Accounts();
        testAccount.setPassword("password");
        testAccount.setFullName("John Doe");
        testAccount.setPhoneNumber("1234567890");
        testAccount.setEmail("john.doe@example.com");
        testAccount.setGender("Male");
        testAccount.setImageUrl("https://example.com/avatar.png");
        testAccount.setBrithday(new Date(0));
        testAccount.setAddress("123 Main St");

        // Act & Assert
        Mockito.when(accountRepository.save(testAccount))
                .thenThrow(new DateTimeParseException("Invalid date format for birthday",new Date(0).toString(), 0));

        Executable executable = () -> accountRepository.save(testAccount);
        Assertions.assertThrows(DateTimeParseException.class, executable);
    }
}
