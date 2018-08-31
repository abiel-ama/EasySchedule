package com.aam.easyschedule.gui.language;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.preference.PreferenceManager;

import java.util.Locale;

public class LanguageManager {


    public static final String ROMANIA = "ro";
    public static final String ENGHISH = Locale.ENGLISH.getLanguage();

    private static final String[]  APP_LANGUAGES_SUPPORT = {ENGHISH, ROMANIA};
    private static final String SELECTED_LANGUAGE = "Locale.Helper.Selected.Language";

    /**
     * Load current application language
     * @param context environment data
     * @return context
     */
    public static Context loadCurrentApplicationLanguage(Context context){
        //get system language as default language
        String defaultLanguage = Locale.getDefault().getLanguage();
        if(!SELECTED_LANGUAGE.contains(defaultLanguage)){
            defaultLanguage = ENGHISH;
        }
        String lang = getPersistentLang(context, defaultLanguage);
        return setLocale(context, lang);
    }

    /**
     * Update application language
     * @param context environment data
     * @param language new language to set
     * @return context
     */
    public static Context updateApplicationLanguage(Context context, String language){
        return setLocale(context, language);
    }

    /**
     *
     * @param context environment data
     * @param language to set
     * @return context
     */
    private static Context setLocale(Context context, String language) {
        persistLang(context, language);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            return updateResources(context, language);
        }

        return updateResourcesLegacy(context, language);
    }

    @TargetApi(Build.VERSION_CODES.N)
    private static Context updateResources(Context context, String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);

        Configuration config = context.getResources().getConfiguration();
        config.setLocale(locale);
        config.setLayoutDirection(locale);

        return context.createConfigurationContext(config);
    }

    private static Context updateResourcesLegacy(Context context, String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);

        Resources resources = context.getResources();

        Configuration config = resources.getConfiguration();
        config.locale = locale;
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1){
            config.setLayoutDirection(locale);
        }
        resources.updateConfiguration(config, resources.getDisplayMetrics());

        return context;
    }

    /**
     * Save application language.
     * @param context environment data
     * @param language to be set.
     */
    private static void persistLang(Context context, String language) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putString(SELECTED_LANGUAGE, language);
        editor.apply();
    }

    /**
     * Get application language that has been set.
     * @param context environment data
     * @param defaultLanguage  Value to return if this preference does not exist.
     * @return saved language if exist, otherwise defaultLanguage.
     */
    private static String getPersistentLang(Context context, String defaultLanguage) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(SELECTED_LANGUAGE, defaultLanguage);
    }
}