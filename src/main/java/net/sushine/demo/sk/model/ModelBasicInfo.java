package net.sushine.demo.sk.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModelBasicInfo {

    /**
     * 模型名称
     */
    private String modelNam;

    /**
     * 创建人
     */
    private String user;

    /**
     * 模型状态
     */
    private String status;

    /**
     * 上线时间
     */
    private Date upTime;

    /**
     * 模型领域
     */
    private String modelArea;
}
