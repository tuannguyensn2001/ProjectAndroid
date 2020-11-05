package com.example.deluxe.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.deluxe.Helper.Rules;
import com.example.deluxe.Interface.PresenterView.DepositInterface;
import com.example.deluxe.Presenter.DepositPresenter;
import com.example.deluxe.R;

public class DepositActivity extends AppCompatActivity implements DepositInterface.DepositView {
    EditText serial, cardCode;
    String serialInput, cardCodeInput;
    Button submitButton;
    DepositInterface.DepositPresenter deposit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposit);

        init();



        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                serialInput = serial.getText().toString();
                cardCodeInput = cardCode.getText().toString();

                if(Rules.stringLength(serialInput,10) && Rules.stringLength(cardCodeInput,14)) {
                    deposit.handleDeposit(serialInput, cardCodeInput);
                } else  setError("Nhập sai seri hoặc mã thẻ");
            }

        });
    }

    public void loadView(Class view) {
        Intent intent = new Intent(this, view);
        startActivity(intent);
    }
    private void init(){
        serial=(EditText) findViewById(R.id.serialInput);
        cardCode=(EditText) findViewById(R.id.cardCodeInput);
        submitButton = (Button) findViewById(R.id.submitButton);

        deposit = new DepositPresenter(this);
    }

    public void setError(String s) {
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }

    public void setMoney(double count)
    {
        TextView money = (TextView) findViewById(R.id.money);
        money.setText("Số tiền của bạn là " + count);
    }
}
