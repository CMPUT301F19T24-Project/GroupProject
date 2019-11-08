package com.example.groupproject;

import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.robotium.solo.Solo;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import org.junit.Test;

public class AccountCreationTest {
    private Solo solo;
    String mockUsername = "testUser";
    String mockPassword = "password";
    String domainToAppend = "@cmput301-c6741.web.app";

    FirebaseAuth.AuthStateListener mAuth;

    @Rule
    public ActivityTestRule<Login> rule =
            new ActivityTestRule<>(Login.class, true, true);

    @Before
    public void setUp() throws Exception {
        solo = new Solo(InstrumentationRegistry.getInstrumentation(), rule.getActivity());

        // initialize Login as the test environment
        solo.assertCurrentActivity("Wrong Activity", Login.class);

        FirebaseAuth.getInstance().signInWithEmailAndPassword(mockUsername + domainToAppend, mockPassword)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseAuth.getInstance().getCurrentUser().delete();
                        }
                    }
                });
        FirebaseAuth.getInstance().signOut();
    }

    @Test
    public void AccountCreationTest() {
        assertEquals(null, FirebaseAuth.getInstance().getCurrentUser());
        solo.scrollDown();
        solo.clickOnText("Create one");
        solo.enterText((EditText) solo.getView(R.id.new_username), mockUsername);
        solo.enterText((EditText) solo.getView(R.id.new_password), mockPassword);
        solo.enterText((EditText) solo.getView(R.id.confirm_password), mockPassword);
        solo.clickOnButton("REGISTER");
        solo.waitForDialogToClose();
        solo.enterText((EditText) solo.getView(R.id.username_field), mockUsername);
        solo.enterText((EditText) solo.getView(R.id.password_field), mockPassword);
        solo.scrollUp();
        solo.clickOnButton("SIGN IN");
        solo.assertCurrentActivity("Not in main screen", Login.class);
    }
}
