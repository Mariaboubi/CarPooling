package gr.aueb.carpooling.model.contact;

import androidx.annotation.NonNull;

import java.util.Objects;
import java.util.regex.Pattern;

public class EmailAddress implements EmailAddressInterface {

    private final String value;
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");

    public EmailAddress(String email) {
        this.value = Objects.requireNonNull(email, "Email cannot be null");
    }


    public String getAddress() {
        return value;
    }


    public boolean isValid() {
        return EMAIL_PATTERN.matcher(value).matches();
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }

        if (this == other) {
            return true;
        }

        if (!(other instanceof EmailAddress)) {
            return false;
        }

        EmailAddress theEmail = (EmailAddress) other;
        return value.equals(theEmail.getAddress());
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
