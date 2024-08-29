package net.sushine.demo.sk.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppOrder {

    private String orderNo;

    private String appNo;

    private Date openDtm;

    private String organizationNo;

    private String organizationName;

    private String tenantNo;

    private String hy;
}
