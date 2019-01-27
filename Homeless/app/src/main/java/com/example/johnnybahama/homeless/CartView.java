package com.example.johnnybahama.homeless;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class CartView extends AppCompatActivity {

    TextView beans;
    TextView pasta;
    TextView blankets;
    TextView bananas;
    TextView brushes;
    TextView fund;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_view);

        TextView sumView = (TextView) findViewById(R.id.sumView);

        TextView beans = (TextView) findViewById(R.id.beanText);
        TextView pasta = (TextView) findViewById(R.id.pastaText);
        TextView blankets = (TextView) findViewById(R.id.blanketText);
        TextView bananas = (TextView) findViewById(R.id.bananaText);;
        TextView brushes = (TextView) findViewById(R.id.brushText);
        TextView fund = (TextView) findViewById(R.id.fundText);

        int sum1 = Integer.parseInt(beans.getText().toString());
        int sum2 = Integer.parseInt(pasta.getText().toString());
        int sum3 = Integer.parseInt(blankets.getText().toString());
        int sum4 = Integer.parseInt(bananas.getText().toString());
        int sum5 = Integer.parseInt(brushes.getText().toString());
        int sum6 = Integer.parseInt(fund.getText().toString());

        int sum = sum1 +sum2 + sum3 + sum4 + sum5 + sum6;
        String sumString = Integer.toString(sum);

        sumView.setText(sumString);


    }
}
