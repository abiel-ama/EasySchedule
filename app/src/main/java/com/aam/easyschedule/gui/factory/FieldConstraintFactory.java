package com.aam.easyschedule.gui.factory;

import com.aam.easyschedule.model.constraint.ConstraintFactory;
import com.aam.easyschedule.model.constraint.StringConstraint;

import java.util.ArrayList;
import java.util.List;

public class FieldConstraintFactory {



    public static List<StringConstraint> createUsernameFieldConstraintList(){
        List<StringConstraint> usernameConstraints = new ArrayList<StringConstraint>();

        usernameConstraints.add(ConstraintFactory.createNotEmptyStringConstrait());

        return usernameConstraints;
    }

    public static List<StringConstraint> createPasswordFieldConstraintList(){
        List<StringConstraint> passwordConstraints = new ArrayList<StringConstraint>();

        passwordConstraints.add(ConstraintFactory.createNotEmptyStringConstrait());
        passwordConstraints.add(ConstraintFactory.createMinimLenghtConstraint());

        return passwordConstraints;
    }
}
