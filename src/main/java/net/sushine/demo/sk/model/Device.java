package net.sushine.demo.sk.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Device {

    private String deviceNo;

    private String deviceEncoding;

    private String deviceName;

    private String deviceType;

    private String deviceLocation;

    private String company;
}
