package com.example.coffeeshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.BreakIterator;

public class MainActivity extends AppCompatActivity {
    Button plus,minus,orderbtn , orderDiplay;
    TextView numOfCoffe;
    CheckBox mandazi , chapati , ugali ,githeri;
    boolean mandaz = false, chapat= false,  ugal = false, gither = false;
    int coffe = 1;
    TextView order,subBill;
    LinearLayout bgcolor,items , toppings , layoutr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        orderDiplay = findViewById(R.id.orderDisplay);
        plus = findViewById(R.id.plus);
        minus = findViewById(R.id.minus);
        mandazi = findViewById(R.id.mandazi);
        chapati = findViewById(R.id.chapati);
        ugali = findViewById(R.id.ugali);
        githeri = findViewById(R.id.githeri);
        numOfCoffe = findViewById(R.id.numOfCoffe);
        order = findViewById(R.id.order);
        bgcolor = findViewById(R.id.bgcolor);
        toppings = findViewById(R.id.toppingsLayout);
        layoutr = findViewById(R.id.layoutr);
        orderbtn = findViewById(R.id.orderbtn);
        subBill = findViewById(R.id.subBill);

        orderbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                orderDiplay.setVisibility(View.VISIBLE);
                orderDiplay.setText("Confirm");
                order.setText("Your order : Ksh" + bill() );

            }
        });
        orderDiplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                orderDiplay.setVisibility(View.GONE);
                String message = "You order  is  Ksh" + bill() + " only";
                 emailling(message);
                order.setText("");
            }
        });


    }
   // menu method
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflator = getMenuInflater();
        inflator.inflate(R.menu.mainmenu , menu);
        return  true;
    }
    // menu item


    @Override
    public boolean onOptionsItemSelected( MenuItem item) {


        switch (item.getItemId()){
            case R.id.green:
                order.setBackgroundResource(R.color.green_700);
                bgcolor.setBackgroundResource(R.color.green_700);
                return true;
            case R.id.pink:
                order.setBackgroundResource(R.color.pink);
                bgcolor.setBackgroundResource(R.color.pink);


                return true;

            case R.id.blue:
                order.setBackgroundResource(R.color.purple_500);
                bgcolor.setBackgroundResource(R.color.purple_500);

                return true;
            case R.id.yellow:
                order.setBackgroundResource(R.color.yellow_200);
                bgcolor.setBackgroundResource(R.color.yellow_200);
                return true;
            case  R.id.hidetoppings:
                toppings.setVisibility(View.GONE);
                layoutr.setVisibility(View.GONE);
                 return  true;

            case R.id.showtoppings:
                toppings.setVisibility(View.VISIBLE);
                layoutr.setVisibility(View.VISIBLE);
                return true;

        }
        return  super.onOptionsItemSelected(item);
    }

    // plus button method
    public void plus(View view){
       // Toast.makeText(getApplicationContext(),"CLicked plus", Toast.LENGTH_SHORT).show();
           ++ coffe;
           display();
    }
    // plus button method
    public void minus(View view){
       // Toast.makeText(getApplicationContext(),"CLicked minus", Toast.LENGTH_SHORT).show();

            --coffe;
            display();
    }
    // Method to toast number of coffees

    public void display(){
        if(coffe<=10 && coffe>0){
            numOfCoffe.setText(String.valueOf(coffe));
            subBill.setText("Bill : Ksh " + bill());

        }
        else{
            if (coffe<=0){
                Toast.makeText(getApplicationContext(),"Orders cannot be less than 1", Toast.LENGTH_SHORT).show();
            }
            if (coffe>10){
                Toast.makeText(getApplicationContext(),"Orders cannot be greater than 10", Toast.LENGTH_SHORT).show();
            }

        }


    }
    public void check(){
        if(chapati.isChecked()&&mandazi.isChecked()&&ugali.isChecked()&&githeri.isChecked()){
            chapat = true;
            mandaz = true;
            ugal = true;
            gither = true;
        }
        else if (mandazi.isChecked()&&ugali.isChecked()&&githeri.isChecked()){

            mandaz = true;
            ugal = true;
            gither = true;

        }
        else if (ugali.isChecked()&&githeri.isChecked()){
            ugal = true;
            gither = true;
        }
        else if (githeri.isChecked()){
            gither = true;

        }
        else {
            chapat = false;
            mandaz = false;
            ugal = false;
            gither = false;
        }

    }
    // billing method
    public int bill(){
      int coffees =   Integer.parseInt(String.valueOf(numOfCoffe.getText()));
        return 15 * coffees;
    }
    // emailing
    public void emailling(String message){
        String[] emails = {"tonnyodhiambo49@gmail.com"};
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setData(Uri.parse("mailto:"));
        intent.setType("'/'");
        intent.putExtra(intent.EXTRA_EMAIL, emails);
        intent.putExtra(intent.EXTRA_SUBJECT, "COffee order");
        intent.putExtra(intent.EXTRA_TEXT, message);
        if (intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        }

    }

}