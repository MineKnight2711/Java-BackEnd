package com.example.javabackend.modules.deliver.service;

import com.example.javabackend.entity.AccountType;
import com.example.javabackend.entity.Deliver;
import com.example.javabackend.entity.ResponseModel;
import com.example.javabackend.modules.account.repository.IAccountTypeRepository;
import com.example.javabackend.modules.deliver.dto.DeliverDTO;
import com.example.javabackend.modules.deliver.repository.DeliverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeliverService {
    @Autowired
    private DeliverRepository deliverRepository;
    @Autowired
    private IAccountTypeRepository accountTypeRepository;
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
            newDeliver.setAccountTypes(accType);
            Deliver saveDeliver=deliverRepository.save(newDeliver);
            return new ResponseModel("Success","Thêm nhân viên thành công",saveDeliver);
        }
        catch (Exception ex)
        {
            return new ResponseModel("Fail","Có lỗi xảy ra"+ex.toString(),null);
        }

    }
}
