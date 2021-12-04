package ca.mcgill.ecse321.library_android;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import ca.mcgill.ecse321.library_android.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    private String error = null;
    private String userName= null;
    private String userType = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void print(View view){
        System.out.println("function called");
    }

    public void signIn(View view){

        System.out.println("function called");

        final EditText username = findViewById(R.id.Username);
        final EditText password = findViewById(R.id.Password);
        RequestParams rp = new RequestParams();
        rp.put("username", username.getText());
        rp.put("password", password.getText());

        HttpUtils.post("sign_in", rp, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, JSONObject response) {
                try {

                    JSONObject serverResp = new JSONObject(response.toString());
                    userName = serverResp.getString("username");
                    userType = serverResp.getString("userType");
                    if(!userType.equals("member")){
                        //search a pop up to say access denied
                        new SweetAlertDialog(MainActivity.this).setTitleText("Access Denied").show();
                        userName = null;
                        userType = null;
                    }
                    else{
                        //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Home()).commit();
                        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
                        bottomNav.setVisibility(View.VISIBLE);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            public void signup(View view){

                final EditText username =  findViewById(R.id.signup_username);
                final EditText password =  findViewById(R.id.signup_password);
                final EditText address =  findViewById(R.id.signup_address);

                RequestParams rp = new RequestParams();

                rp.put("address", address.getText());
                rp.put("username", username.getText());
                rp.put("password", password.getText());
                rp.put("member_type", "Local");
                rp.put("member_status", "Active");


                if (address.getText().toString().equals("") ||password.getText().toString().equals("")||
                        username.getText().toString().equals("")){
                    new SweetAlertDialog(MainActivity.this)
                            .setTitleText("Missing sign up information")
                            .show();
                }

                else {
                    HttpUtils.post("member_sign_up/", rp, new JsonHttpResponseHandler() {

                        @Override
                        public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, JSONObject response) {
                            try {
                                new SweetAlertDialog(MainActivity.this)
                                        .setTitleText("Registration Successful, Login please!")
                                        .show();
//                                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
//                                        new Login()).commit();

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, Throwable throwable, JSONObject errorResponse) {
                            try {
                                new SweetAlertDialog(MainActivity.this)
                                        .setTitleText(errorResponse.get("message").toString())
                                        .show();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        @Override
                        public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, String errorMessage, Throwable throwable) {
                            try {
                                new SweetAlertDialog(MainActivity.this)
                                        .setTitleText(errorMessage)
                                        .show();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }



            @Override
            public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, Throwable throwable, JSONObject errorResponse) {
                try {
                    new SweetAlertDialog(MainActivity.this)
                            .setTitleText(errorResponse.get("message").toString())
                            .show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, String errorMessage, Throwable throwable) {
                try {
                    new SweetAlertDialog(MainActivity.this)
                            .setTitleText(errorMessage)
                            .show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public void goToSignUp(View view){
        setContentView(R.layout.sign_up);
    }



}