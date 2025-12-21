package model;

public class CustomerBuilder extends Customer {

    public CustomerBuilder(String id) {
        super(id, "", "", "");
    }

    @Override
    public CustomerBuilder setName(String name) {
        super.setName(name);
        return this;
    }

    @Override
    public CustomerBuilder setPhone(String phone) {
        super.setPhone(phone);
        return this;
    }

    @Override
    public CustomerBuilder setAddress(String address) {
        super.setAddress(address);
        return this;
    }

    public Customer build() {
        return new Customer(getId(), getName(), getPhone(), getAddress());
    }
}
