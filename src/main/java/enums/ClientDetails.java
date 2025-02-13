package enums;

import lombok.Getter;

@Getter
public enum ClientDetails {

    CLIENT1("Roxana", "Patru", "12234");


    private final String firstName;
    private final String lastName;
    private final String postalCode;

    ClientDetails(String firstName, String lastName, String postalCode) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.postalCode = postalCode;
    }

}
