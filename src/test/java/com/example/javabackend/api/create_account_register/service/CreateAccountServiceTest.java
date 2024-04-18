package com.example.javabackend.api.create_account_register.service;

import com.example.javabackend.entity.AccountType;
import com.example.javabackend.entity.Accounts;
import com.example.javabackend.modules.account.DTO.AccountResponseDto;
import com.example.javabackend.modules.account.DTO.AccountsDTO;
import com.example.javabackend.modules.account.repository.IAccountRepository;
import com.example.javabackend.modules.account.service.AccountService;
import com.example.javabackend.utils.UploadImageService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import static com.google.api.client.util.SecurityUtils.verify;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CreateAccountServiceTest
{
    @Autowired
    private AccountService accountService;

    @MockBean
    private UploadImageService uploadImageService;

    @MockBean
    private IAccountRepository accountsRepository;

    private AccountsDTO accountsDTO;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    private AccountsDTO createValidAccountDTO() {
        AccountsDTO validAccountDTO = new AccountsDTO();
        validAccountDTO.setPassword("password");
        validAccountDTO.setFullName("John Doe");
        validAccountDTO.setPhoneNumber("1234567890");
        validAccountDTO.setEmail("test@example.com");
        validAccountDTO.setGender("Male");
        validAccountDTO.setImageUrl("https://example.com/avatar.png");
        validAccountDTO.setBirthday(new Date(90, 0, 1));
        validAccountDTO.setAddress("123 Main St");
        validAccountDTO.setAccountTypeId(1L);
        return validAccountDTO;
    }

    private AccountsDTO createNullAccountDTO() {
        AccountsDTO nullAccountDTO = new AccountsDTO();
        nullAccountDTO.setPassword(null);
        nullAccountDTO.setFullName(null);
        nullAccountDTO.setPhoneNumber(null);
        nullAccountDTO.setEmail(null);
        nullAccountDTO.setGender(null);
        nullAccountDTO.setImageUrl(null);
        nullAccountDTO.setBirthday(null);
        nullAccountDTO.setAddress(null);
        nullAccountDTO.setAccountTypeId(null);
        return nullAccountDTO;
    }

    private AccountsDTO createInvalidPasswordAccountDTO() {
        AccountsDTO invalidPasswordDTO = new AccountsDTO();
        invalidPasswordDTO.setPassword("short");
        invalidPasswordDTO.setFullName("John Doe");
        invalidPasswordDTO.setPhoneNumber("1234567890");
        invalidPasswordDTO.setEmail("test2@example.com");
        invalidPasswordDTO.setGender("Male");
        invalidPasswordDTO.setImageUrl("https://example.com/avatar.png");
        invalidPasswordDTO.setBirthday(new Date(90, 0, 1));
        invalidPasswordDTO.setAddress("123 Main St");
        invalidPasswordDTO.setAccountTypeId(1L);
        return invalidPasswordDTO;
    }

    private Accounts createValidAccount() {
        Accounts validAccount = new Accounts();
        validAccount.setPassword("hashedPassword");
        validAccount.setFullName("John Doe");
        validAccount.setPhoneNumber("1234567890");
        validAccount.setEmail("test@example.com");
        validAccount.setGender("Male");
        validAccount.setImageUrl("https://example.com/avatar.png");
        validAccount.setBrithday(new Date(90, 0, 1));
        validAccount.setAddress("123 Main St");
        AccountType accountType = new AccountType();
        accountType.setAccountTypeID(1L);
        validAccount.setAccountTypes(accountType);
        return validAccount;
    }

    private void assertResponseDtoEquals(AccountsDTO expectedDTO, AccountResponseDto actualDTO) {
        Assertions.assertEquals(expectedDTO.getFullName(), actualDTO.getFullName());
        Assertions.assertEquals(expectedDTO.getPhoneNumber(), actualDTO.getPhoneNumber());
        Assertions.assertEquals(expectedDTO.getEmail(), actualDTO.getEmail());
        Assertions.assertEquals(expectedDTO.getGender(), actualDTO.getGender());
        Assertions.assertEquals(expectedDTO.getImageUrl(), actualDTO.getImageUrl());
        Assertions.assertEquals(expectedDTO.getBirthday(), actualDTO.getBirthday());
        Assertions.assertEquals(expectedDTO.getAddress(), actualDTO.getAddress());
        Assertions.assertEquals(expectedDTO.getAccountTypeId(), actualDTO.getAccountTypeId());
    }
    @Test
    public void testCreateAccountSuccess() throws IOException {
        AccountsDTO validAccountDTO = createValidAccountDTO();
        Accounts savedAccount = createValidAccount();

        when(accountsRepository.save(any(Accounts.class))).thenReturn(savedAccount);

        AccountResponseDto responseDto = accountService.createAccount(validAccountDTO);

        verify(accountsRepository, times(1)).save(any(Accounts.class));
        assertResponseDtoEquals(validAccountDTO, responseDto);
    }

    @Test(expected = NullPointerException.class)
    public void testCreateAccountWithNullFields() throws IOException {
        AccountsDTO nullFieldsDTO = createNullAccountDTO();
        accountService.createAccount(nullFieldsDTO);
    }

    @Test
    public void testCreateAccountWithNotNullBirthDay() throws IOException {
        AccountsDTO validAccountDTO = createValidAccountDTO();
        Accounts savedAccount = createValidAccount();

        when(accountsRepository.save(any(Accounts.class))).thenReturn(savedAccount);

        // Gọi phương thức createAccount() với đối tượng AccountsDTO này
        AccountResponseDto responseDto = accountService.createAccount(validAccountDTO);

        // Xác minh rằng birthDay đã được đặt trong đối tượng Accounts và phản hồi là không null
        Assertions.assertNotNull(responseDto);
        Assertions.assertNotNull(responseDto.getBirthday()); // Xác minh rằng birthDay không null
        verify(accountsRepository, times(1)).save(any(Accounts.class));
        assertResponseDtoEquals(validAccountDTO, responseDto);
    }

    @Test
    public void testCreateAccountWithNullBirthDay() throws IOException {

        AccountsDTO validAccountDTO = createValidAccountDTO();
        validAccountDTO.setBirthday(null);
        Accounts savedAccount = createValidAccount();

        when(accountsRepository.save(any(Accounts.class))).thenReturn(savedAccount);

        // Gọi phương thức createAccount() với đối tượng AccountsDTO này
        AccountResponseDto responseDto = accountService.createAccount(validAccountDTO);

        // Xác minh rằng birthDay đã được đặt trong đối tượng Accounts và phản hồi là không null
        Assertions.assertNotNull(responseDto);
        Assertions.assertEquals(null, responseDto.getBirthday()); // Xác minh rằng birthDay là null
        verify(accountsRepository, times(1)).save(any(Accounts.class));
        assertResponseDtoEquals(validAccountDTO, responseDto);

    }
    @Test
    public void testCreateAccountWithNullFile() throws IOException {
        AccountsDTO validAccountDTO = createValidAccountDTO();
        validAccountDTO.setFile(null);

        Accounts savedAccount = createValidAccount();
        savedAccount.setImageUrl(validAccountDTO.getImageUrl());

        when(accountsRepository.save(any(Accounts.class))).thenReturn(savedAccount);

        AccountResponseDto responseDto = accountService.createAccount(validAccountDTO);

        verify(accountsRepository, times(1)).save(any(Accounts.class));
        Assertions.assertEquals(validAccountDTO.getImageUrl(), responseDto.getImageUrl());
    }

}
