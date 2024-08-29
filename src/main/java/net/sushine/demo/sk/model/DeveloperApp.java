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
public class DeveloperApp {

    private String appNo;

    private String tenantNo;

    private String orgNo;

    private String devloperNo;

    private String name;

    private String appRealm;

    private String status;

    private Date upTime;

    private String developerName;
}
