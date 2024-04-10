package com.example.javabackend.api.repository;

import com.example.javabackend.JavaBackEndApplication;
import com.example.javabackend.entity.Accounts;
import com.example.javabackend.modules.account.repository.IAccountRepository;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AccountRepositoryTest {

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
    public void findByEmail() {
        // Arrange
        when(accountRepository.findByEmail(testAccount.getEmail())).thenReturn(testAccount);

        // Act
        Accounts foundAccount = accountRepository.findByEmail(testAccount.getEmail());

        // Assert
        Assertions.assertThat(foundAccount).isNotNull();
        Assertions.assertThat(foundAccount.getEmail()).isEqualTo(testAccount.getEmail());
    }


    @Test
    public void getUserIdByEmail() {
        // Arrange
        when(accountRepository.getUserIdByEmail(testAccount.getEmail())).thenReturn(testAccount.getAccountID());

        // Act
        Long userId = accountRepository.getUserIdByEmail(testAccount.getEmail());

        // Assert
        Assertions.assertThat(userId).isEqualTo(testAccount.getAccountID());
    }

    @Test
    public void findByAccountType() {
        // Arrange
        String accountType = "User";
        List<Accounts> accounts = Collections.singletonList(testAccount);
        when(accountRepository.findByAccountType(accountType)).thenReturn(accounts);

        // Act
        List<Accounts> foundAccounts = accountRepository.findByAccountType(accountType);

        // Assert
        Assertions.assertThat(foundAccounts).isNotNull();
        Assertions.assertThat(foundAccounts).containsExactly(testAccount);
    }

    @Test
    public void getRolesOfUser() {
        // Arrange
        String[] roles = {"ROLE_USER"};
        when(accountRepository.getRolesOfUser(testAccount.getAccountID())).thenReturn(roles);

        // Act
        String[] userRoles = accountRepository.getRolesOfUser(testAccount.getAccountID());

        // Assert
        Assertions.assertThat(userRoles).isNotNull();
        Assertions.assertThat(userRoles).isEqualTo(roles);
    }

}
