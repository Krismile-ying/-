package web.dao;

import web.model.Device;

import java.util.List;

public interface DeviceDao {
    //获得所有设备对象的列表
    List<Device> findAllDevice();
    //获得指定设备分类编号的所有设备对象的列表
    List<Device>findDeviceByDeviceClassId(int deviceClassId);
    //查找设备对象
    Device findDeviceById(int deviceId);
    //获得指定设备名称的所有设备对象的列表
    List<Device>findDeviceByName(String deviceName);
    //获得指定设备价格范围的所有设备对象的列表
    List<Device>findDeviceByPrice(String low,String high);
    //获得指定设备分类名、设备名称和价格范围的所有设备对象的列表
    List<Device>findDeviceByFuzzy(String deviceClassName, String deviceName, String low, String high);
    //修改设备对象
    void updateDeviceById(int deviceId,String deviceName,String devicePrice);
    //增加设备对象
    void addDevice(Device device);
    //删除设备对象
    void deleteDevice(Device device);
}
