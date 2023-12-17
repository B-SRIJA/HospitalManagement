package myexceptions;

import java.io.Serializable;

public class PatientNumberNotFoundException extends Exception implements Serializable {

    // Adding the serialVersionUID field
    private static final long serialVersionUID = 1L;

    public PatientNumberNotFoundException(String message) {
        super(message);
    }
}


