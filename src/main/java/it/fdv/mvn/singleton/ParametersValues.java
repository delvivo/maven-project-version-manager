package it.fdv.mvn.singleton;

import it.fdv.mvn.exception.NoBooleanValueException;

public class ParametersValues {

    private static ParametersValues parametersValues;

    private boolean force;

    private ParametersValues() {}

    public static ParametersValues instance() {

        if (parametersValues == null) {
            parametersValues = new ParametersValues();
        }

        return parametersValues;
    }

    public boolean isForce() {
        return force;
    }

    public void setForce(String force) throws NoBooleanValueException {

        switch (force.toUpperCase()) {
            case "TRUE":
                this.force = true;
                break;
            case "FALSE":
                this.force = false;
                break;
            default:
                throw new NoBooleanValueException();
        }
    }
}
