package com.aam.easyschedule.model.constraint;

import android.content.Context;
import android.text.TextUtils;

import com.aam.easyschedule.R;
import com.aam.easyschedule.model.constraint.StringConstraint;

public class NotEmptyConstraint implements StringConstraint {

    @Override
    public boolean isValidString(String stringToCheck) {
        //check the string
        return !TextUtils.isEmpty(stringToCheck);
    }

    @Override
    public String getErrorMessage(Context context) {
        return context.getResources().getString(R.string.error_field_required);
    }
}
