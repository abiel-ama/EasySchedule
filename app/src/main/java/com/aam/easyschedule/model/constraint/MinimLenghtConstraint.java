package com.aam.easyschedule.model.constraint;

import android.content.Context;

import com.aam.easyschedule.R;
import com.aam.easyschedule.model.constraint.StringConstraint;

public class MinimLenghtConstraint implements StringConstraint {

    @Override
    public boolean isValidString(String stringToCheck) {
        //check the string
        return stringToCheck.length() > 3;
    }

    @Override
    public String getErrorMessage(Context context) {
        return context.getResources().getString(R.string.error_minimum_lenght);
    }
}
