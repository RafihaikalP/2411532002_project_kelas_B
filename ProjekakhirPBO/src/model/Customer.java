package model;

public class Customer {
    private String id;
    private String name;
    private String phone;
    private String address;

    public Customer(String id, String name, String phone, String address) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.address = address;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getPhone() { return phone; }
    public String getAddress() { return address; }

    // ✅ ubah return type -> Customer (bukan void)
    public Customer setName(String name) {
        this.name = name;
        return this;
    }

    public Customer setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public Customer setAddress(String address) {
        this.address = address;
        return this;
    }

    @Override
    public String toString() {
        return "Customer{id='" + id + "', name='" + name + "', phone='" + phone + "', address='" + address + "'}";
    }
}
