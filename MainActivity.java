package com.example.nplfinal;
import android.content.Intent;
import android.util.Log;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.lifecycle.ViewModelProvider;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText ipAddressEditText;
    private TextView resultTextView;
    private MyViewModel viewModel;  // Assume you have a MyViewModel instance

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.setProperty("java.net.preferIPv4Stack", "true");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = new ViewModelProvider(this).get(MyViewModel.class);  // Instantiate MyViewModel

        ipAddressEditText = findViewById(R.id.ipAddressEditText);
        resultTextView = findViewById(R.id.resultTextView);
        Button syncButton = findViewById(R.id.syncButton);

        syncButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ipAddress = ipAddressEditText.getText().toString();
                new NtpSyncTask(viewModel, resultTextView).execute(ipAddress);
            }
        });


        // Observe LiveData in your activity
        viewModel.getResultLiveData().observe(this, result -> {
            Log.d("MainActivity", "Result observed: " + result);
            resultTextView.setText(result);
        });
    }

    private static class NtpSyncTask extends AsyncTask<String, Void, String> {
        private MyViewModel viewModel;
        private TextView resultTextView;  // Add this field

        public NtpSyncTask(MyViewModel viewModel, TextView resultTextView) {
            this.viewModel = viewModel;
            this.resultTextView = resultTextView;
        }

        @Override
        protected String doInBackground(String... params) {
            String ipAddress = params[0];
            return NtpSyncHelper.syncNtp(ipAddress);
        }

        @Override
        protected void onPostExecute(String result) {
            Log.d("AsyncTask", "Result: " + result);

            if (result != null) {
                viewModel.setResult(result);
                resultTextView.post(() -> resultTextView.setText("The time fetched is: " + result));
            } else {
                viewModel.setResult("Error occurred");
                resultTextView.post(() -> resultTextView.setText("Error occurred"));

            }
        }

    }

}


