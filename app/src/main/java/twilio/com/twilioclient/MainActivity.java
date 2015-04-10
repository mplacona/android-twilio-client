package twilio.com.twilioclient;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;


public class MainActivity extends Activity implements View.OnClickListener {

    private MonkeyPhone phone;
    private EditText numberField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        phone = new MonkeyPhone(this);

        ImageButton dialButton = (ImageButton)findViewById(R.id.dialButton);
        dialButton.setOnClickListener(this);

        ImageButton hangupButton = (ImageButton)findViewById(R.id.hangupButton);
        hangupButton.setOnClickListener(this);

        numberField = (EditText)findViewById(R.id.numberField);
    }

    @Override
    public void onClick(View view)
    {
        if (view.getId() == R.id.dialButton) {
            phone.connect(numberField.getText().toString());
        } else if (view.getId() == R.id.hangupButton) {
            phone.disconnect();
        }
    }
}
