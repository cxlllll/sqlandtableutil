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
public class DeveloperModel {

    private String modelNo;

    private String tenantNo;

    private String orgNo;

    private String devloperNo;

    private String name;

    /**
     * 模型领域
     */
    private String modelRealm;

    private String status;

    private Date upTime;

    private String developerName;
}
