package com.alan.todo;

import android.os.Bundle;

import com.alan.alansdk.AlanCallback;
import com.alan.alansdk.AlanConfig;
import com.alan.alansdk.AlanState;
import com.alan.alansdk.events.EventCommand;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;

import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.alan.todo.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private FragmentManager fragmentManger;
    private NavHostFragment navHostFragment;
    private NavController navController;
    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    private static final String SDK_KEY = "/stage";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        fragmentManger = getSupportFragmentManager();
        navHostFragment = (NavHostFragment) fragmentManger.findFragmentById(R.id.nav_host_fragment_content_main);

        /* this type of initialisation requires the component fragment
        navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
         */
        assert navHostFragment != null;
        navController = navHostFragment.getNavController();
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        AlanConfig config = AlanConfig.builder().setProjectId(SDK_KEY).build();
        binding.alanButton.initWithConfig(config);

        AlanCallback alanCallback = new AlanCallback() {
            /// Handle commands from Alan Studio
            @Override
            public void onCommand(final EventCommand eventCommand) {
                try {
                    JSONObject command = eventCommand.getData();
                    //data object is the JSON Object returned in p.play() method
                    JSONObject data = command.getJSONObject("data");
                    String commandName = data.getString("command");
                    //based on commandName we can perform different tasks
                    executeCommand(commandName, data);
                } catch (JSONException e) {
                    Log.e("AlanButton", e.getMessage());
                }
            }

            @Override
            public void onButtonState(@NonNull AlanState alanState) {
                super.onButtonState(alanState); //comment out if logic is added
                Log.d("AlanButton", "onButtonState: " + alanState);
                if (alanState == AlanState.CONNECTING)
                    Log.d("AlanButton", "Button is connecting");
            }

            @Override
            public void onEvent(String payload) {
                super.onEvent(payload); //comment out if logic is added
                try {
                    JSONObject eventDict = new JSONObject(payload);
                    Log.d("AlanButton", "onEvent: event: " + eventDict);
                } catch (JSONException e) {
                    Log.e("AlanButton", e.getMessage());
                }
            }
        };

        binding.alanButton.registerCallback(alanCallback);
    }

    private void executeCommand(String commandName, JSONObject data) {
        if (commandName.equals("go_back")) {
            onBackPressed(); //android lifecycle method
        }

        if (commandName.equals("log_out")) {
            logOut();
        }

        if (commandName.equals("guestWifi")) {
            binding.alanButton.playText("ready to setup guest wifi");

        }

        if (commandName.equals("add_todo")) {
            try {
                String title = data.getString("title");
                addTodoItem(title);
            } catch (JSONException e) {
                Log.e("AlanButton", e.getMessage());
                binding.alanButton.playText("I'm sorry I'm unable to do this at the moment");
            }
        }
    }

    private void logOut() {
        //code to log out
    }

    private void addTodoItem(String title) {
        //code to add an Item
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
        navHostFragment = (NavHostFragment) fragmentManger.findFragmentById(R.id.nav_host_fragment_content_main);
        assert navHostFragment != null;
        navController = navHostFragment.getNavController();
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}