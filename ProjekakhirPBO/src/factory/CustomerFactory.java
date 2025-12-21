package factory;

import helper.IdGenerator;
import model.Customer;
import model.CustomerBuilder;

public final class CustomerFactory {
    private CustomerFactory() {}

    public static Customer create(String name, String phone, String address) {
        if (name == null || name.trim().isEmpty())
            throw new IllegalArgumentException("Nama customer tidak boleh kosong!");
        if (phone == null || phone.trim().isEmpty())
            throw new IllegalArgumentException("Nomor HP tidak boleh kosong!");

        String id = IdGenerator.newId("CUST");

        // Builder dipakai di sini (nilai tambah: builder dipakai betulan)
        return new CustomerBuilder(id)
                .setName(name.trim())
                .setPhone(phone.trim())
                .setAddress(address == null ? "" : address.trim())
                .build();
    }
}
