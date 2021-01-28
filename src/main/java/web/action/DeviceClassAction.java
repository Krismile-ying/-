package web.action;

import java.io.IOException;

public interface DeviceClassAction {
    //获得所有设备分类对象的列表
    void findAllDeviceClass() throws IOException;
    void findDeviceClass() throws IOException;
    public String addDeviceClass() throws IOException;
    public String deleteDeviceClass() throws IOException;
}
