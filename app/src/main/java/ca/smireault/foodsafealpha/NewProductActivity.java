package ca.smireault.foodsafealpha;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewProductActivity extends AppCompatActivity {

    private EditText etNewProduct;

    //public static final String EXTRA_REPLY = "com.example.android.wordlistsql.REPLY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_product);

        etNewProduct = findViewById(R.id.etNewProduct);

        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(etNewProduct.getText())) {
                    setResult(RESULT_CANCELED, replyIntent);
                } else {
                    String product_name = etNewProduct.getText().toString();
                    replyIntent.putExtra("product_name", product_name);
                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });
    }
}
