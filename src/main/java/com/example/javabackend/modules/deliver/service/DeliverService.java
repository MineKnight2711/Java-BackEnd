package com.example.javabackend.modules.deliver.service;

import com.example.javabackend.entity.AccountType;
import com.example.javabackend.entity.Deliver;
import com.example.javabackend.entity.ResponseModel;
import com.example.javabackend.modules.account.repository.IAccountTypeRepository;
import com.example.javabackend.modules.deliver.dto.DeliverDTO;
import com.example.javabackend.modules.deliver.repository.DeliverRepository;
import com.example.javabackend.modules.encrypt_decrypt.RSAEncryptDecrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeliverService {
    @Autowired
    private DeliverRepository deliverRepository;
    @Autowired
    private IAccountTypeRepository accountTypeRepository;
    private RSAEncryptDecrypt rsa;
    public ResponseModel getDeliverById(Long deliverId)
    {
        Deliver deliver=deliverRepository.findById(deliverId).orElse(null);
        if(deliver==null)
        {
            return new ResponseModel("NotFound","Không tìm thấy!",null);
        }
        return new ResponseModel("Success","Đã tìm thấy nhân viên",deliver);
    }
    public ResponseModel createNewDeliver(DeliverDTO dto)
    {
        try
        {

            Deliver newDeliver=dto.toEntity();
            AccountType accType = accountTypeRepository.findByAccountTypeId(dto.getAccountTypeId());
            if(accType==null)
            {
                return new ResponseModel("AccountTypeNotFound","Không tìm thấy loại tài khoản",null);
            }
            Deliver saveDeliver=deliverRepository.save(newDeliver);
            return new ResponseModel("Success","Thêm nhân viên thành công",saveDeliver);
        }
        catch (Exception ex)
        {
            return new ResponseModel("Fail","Có lỗi xảy ra"+ex.toString(),null);
        }

    }
    public ResponseModel updateLocation(Long deliverId,Double latitude,Double longitude)
    {
        try
        {

            Deliver del=deliverRepository.findById(deliverId).orElse(null);
            if(del==null)
            {
                return new ResponseModel("DeliverNotFound","Không tìm thấy tài khoản",null);
            }
            del.setLatitude(latitude);
            del.setLongitude(longitude);
            Deliver saveDeliver=deliverRepository.save(del);
            return new ResponseModel("Success","Cập nhật địa chỉ thành công",saveDeliver);
        }
        catch (Exception ex)
        {
            return new ResponseModel("Fail","Có lỗi xảy ra"+ex.toString(),null);
        }

    }
    public ResponseModel requestPublicKey(){
        try {
            rsa=new RSAEncryptDecrypt();
            String publicKeyEncoded=rsa.encodePublicKey(rsa.getPublicKey());
            System.out.println(rsa.encodePublicKey(rsa.getPublicKey()));
            return new ResponseModel("Success","Yêu cầu publickey thành công",publicKeyEncoded);
        } catch (Exception ex)
        {
            return new ResponseModel("Failure","KCó lỗi xảy ra",null);
        }
    }
    public ResponseModel login(String phoneReceived)
    {
        try
        {
            String phoneNumber= rsa.decrypt(phoneReceived);
            Deliver deliver=deliverRepository.findByPhoneNumber(phoneNumber);
            if(deliver==null)
            {
                return new ResponseModel("Failure","Không tìm thấy tài khoản",null);
            }
            else
            {
                return new ResponseModel("Success","Thông tin tài khoản",deliver);
            }
        }
        catch (Exception ex)
        {
            return new ResponseModel("Failure","Có lỗi xảy ra"+ex.getMessage(),null);
        }
    }
}
