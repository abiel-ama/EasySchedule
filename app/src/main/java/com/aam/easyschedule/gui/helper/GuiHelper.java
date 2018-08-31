package com.aam.easyschedule.gui.helper;


import android.content.Context;

import com.aam.easyschedule.gui.factory.FieldConstraintFactory;
import com.aam.easyschedule.model.constraint.StringConstraint;

import java.util.List;

public class GuiHelper {

    public static final String NO_ERROR_MESSAGE = "NO_ERROR_MESSAGE";

    private static List<StringConstraint> usernameConstraints = FieldConstraintFactory.createUsernameFieldConstraintList();
    private static List<StringConstraint> passwordConstraints = FieldConstraintFactory.createPasswordFieldConstraintList();

    public static String checkUsernameConstraints(Context context, String username){
        return checkConstraints(context,username,usernameConstraints);
    }

    public static String checkPasswordConstraints(Context context, String password){
      return checkConstraints(context,password, passwordConstraints);
    }

    private static String checkConstraints (Context context, String stringToBeCheck, List<StringConstraint> contraints){
        for(StringConstraint constraint : contraints){
            if(!constraint.isValidString(stringToBeCheck)){
                return constraint.getErrorMessage(context);
            }
        }
        return NO_ERROR_MESSAGE;
    }

}
