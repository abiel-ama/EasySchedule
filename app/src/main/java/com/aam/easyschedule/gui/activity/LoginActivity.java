package com.aam.easyschedule.gui.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.aam.easyschedule.R;
import com.aam.easyschedule.gui.wachers.FormTextWachers;
import com.aam.easyschedule.gui.language.LanguageManager;
import com.aam.easyschedule.gui.helper.GuiHelper;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity{

    // UI references.
    private TextInputLayout usernameTextLayout;
    private TextInputLayout passwordTextLayout;
    private EditText usernameView;
    private EditText passwordView;
    private Button signInButton;
    private TextView singUpTextView;
    private View progressView;
    private View loginFormView;


//    @Override
//    protected void attachBaseContext(Context newBase) {
//        super.attachBaseContext(LanguageManager.loadCurrentApplicationLanguage(newBase));
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //set language
        LanguageManager.loadCurrentApplicationLanguage(this);
        setContentView(R.layout.activity_login);

        // Set up the login form.
        usernameTextLayout = findViewById(R.id.text_layout_username);
        passwordTextLayout = findViewById(R.id.text_layout_password);
        usernameView = findViewById(R.id.text_username);
        passwordView = findViewById(R.id.text_password);
        loginFormView = findViewById(R.id.login_form);
        progressView = findViewById(R.id.login_progress);
        signInButton = findViewById(R.id.button_sign_in);
        singUpTextView = findViewById(R.id.text_view_sing_up);

        createFieldsWatcher();
        createButtonListner();


        //change action bar title, if you dont change it will be accourding to your system default language
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) actionBar.setTitle(getResources().getString(R.string.app_name));

        validatePassword();
        validateUsername();
    }

    private void createFieldsWatcher(){
        usernameView.addTextChangedListener(createUsernameWatcher());
        passwordView.addTextChangedListener(createPasswordWatcher());
    }

    private TextWatcher createUsernameWatcher() {
        return new FormTextWachers(){
            @Override
            public void afterTextChanged(Editable editable) {
                // Check for a valid username.
                validateUsername();
            }
        };
    }

    private TextWatcher createPasswordWatcher() {
        return new FormTextWachers(){
            @Override
            public void afterTextChanged(Editable editable) {
                //Check for a valid password
                validatePassword();
            }
        };
    }

    private void validateUsername(){
        String username = usernameView.getText().toString();
        String usernameError = GuiHelper.checkUsernameConstraints(LoginActivity.this.getApplicationContext(),username);
        if (GuiHelper.NO_ERROR_MESSAGE.equals(usernameError)) {
            usernameError = null;
        }
        signInButton.setEnabled(usernameError == null && passwordTextLayout.getError() == null);
        usernameTextLayout.setError(usernameError);
        usernameView.requestFocus();
    }

    private void validatePassword(){
        String password = passwordView.getText().toString();
        String passwordError = GuiHelper.checkPasswordConstraints(LoginActivity.this.getApplicationContext(),password);
        if (GuiHelper.NO_ERROR_MESSAGE.equals(passwordError)) {
            passwordError = null;
        }
        signInButton.setEnabled(passwordError == null && usernameTextLayout.getError()== null);
        passwordTextLayout.setError(passwordError);
        passwordView.requestFocus();
    }

    private void createButtonListner(){
        signInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        singUpTextView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LoginActivity.this, "Sorry, Username or Password is incorrect.", Toast.LENGTH_LONG).show();
            }
        });
    }

    /**
     * Attempts to sign in the account specified by the login form.
     * If there are form errors (invalid username, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {
        //TODO - check user in data base on server first
        String username = usernameView.getText().toString();
        String password = passwordView.getText().toString();

        //go to next activity
        Intent mainIntent = new Intent(LoginActivity.this, UserActivity.class);
        startActivity(mainIntent);
        Toast.makeText(LoginActivity.this, "You are Sing in Successfuly.", Toast.LENGTH_LONG).show();
        //Toast.makeText(LoginActivity.this, "Sorry, Username or Password is incorrect.", Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_login_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.language_en){
            updateLocale(LanguageManager.ENGHISH);
        }
        else if (item.getItemId() == R.id.language_ro){
            updateLocale(LanguageManager.ROMANIA);
        }

        return true;
    }

    private void updateLocale(String lang){
        LanguageManager.updateApplicationLanguage(this, lang);
//        LoginActivity.this.recreate();

        passwordView.refreshDrawableState();
    }
}

