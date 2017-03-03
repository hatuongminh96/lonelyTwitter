package ca.ualberta.cs.lonelytwitter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class EditTweetActivity extends Activity {

    TextView textBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_tweet);
        textBox = (TextView) findViewById(R.id.textView);
        Intent rI = getIntent();
        String message = rI.getStringExtra("message");
        textBox.setText(message);
    }
}
