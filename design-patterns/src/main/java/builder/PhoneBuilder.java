package builder;

public class PhoneBuilder {
    private String os;
    private int ram;
    private String processor;
    private int battery;

    public PhoneBuilder setOs(String os) {
        this.os = os;
        return this;
    }

    public PhoneBuilder setRam(int ram) {
        this.ram = ram;
        return this;
    }

    public PhoneBuilder setProcessor(String processor) {
        this.processor = processor;
        return this;
    }

    public PhoneBuilder setBattery(int battery) {
        this.battery = battery;
        return this;
    }

    @Override
    public String toString(){
        return os + " : " + ram + " : " + processor  + " : " + battery;
    }

    public Phone getPhone(){
        return new Phone(os,ram,processor,battery);
    }
}
