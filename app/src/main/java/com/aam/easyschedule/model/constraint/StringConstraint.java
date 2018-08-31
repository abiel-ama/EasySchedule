package com.aam.easyschedule.model.constraint;

import android.content.Context;

public interface StringConstraint {

    public boolean isValidString(String stringToCheck);

    public String getErrorMessage(Context context);
}
