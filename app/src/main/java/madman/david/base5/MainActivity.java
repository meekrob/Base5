package madman.david.base5;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity implements View.OnClickListener{
    private EditText entry;
    private TextView outputView;
    private TextView digits[];
    private String base5result;
    private void Compute() {
        String stringArg = entry.getText().toString();
        try {
            int arg = new Integer(stringArg);
            base5result = base5(arg);
            Log.d("Compute().base5", base5result);
            base5result = zeroPad(base5result,4);
            Log.d("Compute().zeroPad(4)", base5result);
            outputView.setText(base5result);
            char arr[] = base5result.toCharArray();
            for (int i = 0; i < 4; i++)
            {
                digits[i].setText(arr, i, 1);
            }
        }
        catch (NumberFormatException e) {
            Toast.makeText(this, stringArg + " is not a number", Toast.LENGTH_LONG);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View ComputeButton = findViewById(R.id.button);
        ComputeButton.setOnClickListener(this);
        entry = (EditText)findViewById(R.id.base_ten_entry);
        outputView = (TextView)findViewById(R.id.outtext);
        digits = new TextView[]{
                (TextView) findViewById(R.id.outtext_125),
                (TextView) findViewById(R.id.outtext_25),
                (TextView) findViewById(R.id.outtext_5),
                (TextView) findViewById(R.id.outtext_1)
        };
    }
    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.button:
                Compute();
        }
    }
    protected String repeatChar(char c, int n) {
        char charray[] = new char[n];
        for (int i = 0; i < n; i++) charray[i] = c;
        return new String(charray);
    }
    protected String zeroPad(String res, int width) {
        if (res.length() < 4)
            res = repeatChar('0',width - res.length()) + res;
        return res;
    }
    protected String base5(int digit) {
        return baseN(digit, 5);
    }
    protected String baseN(int digit, int base) {
        if (digit == 0)
            return "0";

        String answer = "";
        while (digit > 0) {
            int remainder = digit % base;
            answer += remainder;
            digit /= base;
        }
        return StringReverse(answer);
    }
    protected String StringReverse(String s)
    {
        return new StringBuilder(s).reverse().toString();
    }
}
