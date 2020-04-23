package com.example.asynchttp;

        import androidx.appcompat.app.AppCompatActivity;

        import android.os.AsyncTask;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.TextView;

        import java.io.BufferedInputStream;
        import java.io.InputStream;
        import java.net.HttpURLConnection;
        import java.net.URL;

public class MainActivity extends AppCompatActivity {
        EditText editText;
        String urlString;
        Button button;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);
                button = (Button) findViewById(R.id.button);
                editText = (EditText) findViewById(R.id.editText);

                button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                urlString = editText.getText().toString();
                                new httpRead().execute();
                        }
                });

        }

        private class httpRead extends AsyncTask<String, String, Void> {
                @Override
                protected Void doInBackground(String... strings) {
                        try {
                                URL url = new URL(urlString);
                                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                                InputStream in = new BufferedInputStream(connection.getInputStream());
                                final String htmlText = Utilities.fromStream(in);
                                runOnUiThread(new Runnable() {

                                        @Override
                                        public void run() {
                                                TextView textView = findViewById(R.id.httpTextView);
                                                textView.setText(htmlText);
                                        }
                                });
                        } catch (Exception e) {
                                e.printStackTrace();
                        }
                        return null;

                }



        }

}


