package com.example.javabackend.modules.deliver.dto;

import com.example.javabackend.entity.Deliver;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class DeliverDTO
{
    private String deliverID;
    private String deliverName;
    private Double latitude;
    private Double longitude;
    private String phoneNumber;
    private boolean workState;
    private Long accountTypeId;

    public Deliver toEntity(){
        Deliver deliver = new Deliver();
        deliver.setDeliverName(this.deliverName);
        deliver.setLatitude(this.latitude);
        deliver.setLongitude(this.longitude);
        deliver.setPhoneNumber(this.phoneNumber);
        deliver.setWorkState(this.workState);
        return deliver;
    }
}
