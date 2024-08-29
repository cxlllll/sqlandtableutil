package net.sushine.demo.sk.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeveloperInfo {

    /**
     * 开发者表主键
     */
    private String devNo;

    /**
     * 开发者名称
     */
    private String name;
}
