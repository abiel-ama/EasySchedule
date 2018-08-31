package com.aam.easyschedule.model.constraint;

public class ConstraintFactory {

    public static StringConstraint createNotEmptyStringConstrait(){
        return new NotEmptyConstraint();
    }

    public static StringConstraint createMinimLenghtConstraint(){
        return new MinimLenghtConstraint();
    }
}
