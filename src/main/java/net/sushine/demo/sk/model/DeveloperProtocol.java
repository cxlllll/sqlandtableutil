package net.sushine.demo.sk.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeveloperProtocol {

    private String protocolNo;

    private String tenantNo;

    private String orgNo;

    private String devloperNo;

    private String name;

    private String description;

    private String protocolRealm;

    private String status;

    private Date upTime;

    private String developerName;
}
