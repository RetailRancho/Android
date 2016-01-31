package io.rancho.retail.beaconstac.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

import io.rancho.retail.beaconstac.R;

public class LayoutActivity extends AppCompatActivity {

    ImageView imageView,product;
    TextView product_name,product_info,product_price;
    public Map<String, String> map = new HashMap<String, String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#03A9F4")));
        actionBar.setTitle("Demo Layout");

        imageView = (ImageView) findViewById(R.id.layout_room);



        product = (ImageView)findViewById(R.id.product_photo);
        product.setImageResource(R.drawable.tshirth);
        product_name = (TextView)findViewById(R.id.product_name);
        product_info = (TextView)findViewById(R.id.product_info);
        product_price = (TextView)findViewById(R.id.product_price);

        product_name.setText("\nT-shirt");
        product_price.setText("\nPrice:₹799");
        product_info.setText("Fabric:Cotton \nType:Polo \nFit:Regular");

        map.put("ff00", "T-Shirts");
        map.put("000", "Jeans");
        map.put("00ff", "Shoes");
        map.put("ffff0", "Sunglasses");

        if(imageView!=null)
        {
            imageView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    int action = event.getAction();
                    int x = (int)event.getX();
                    int y = (int)event.getY();
                    if(action == MotionEvent.ACTION_UP)
                    {
                        ImageView img = (ImageView)findViewById (R.id.layout_beacons);
                        img.setDrawingCacheEnabled(true);
                        Bitmap hotspots = Bitmap.createBitmap(img.getDrawingCache());
                        if (hotspots == null) {
                            Log.d ("ABC", "Hot spot bitmap was not created");
                        } else {
                            img.setDrawingCacheEnabled(false);
                            int touchedColor = hotspots.getPixel(x,y);
                            String color = Integer.toHexString(Color.red(touchedColor))+Integer.toHexString(Color.green(touchedColor))+Integer.toHexString(Color.blue(touchedColor));
                            Log.d("ABC","The touched color is: "+map.get(color));
                            if(map.get(color)!=null)
                            {
                                String key = map.get(color);
                                if(key == "T-Shirts")
                                {
                                    product.setImageResource(R.drawable.tshirth);
                                    product_name.setText("\nT-shirt");
                                    product_price.setText("\nPrice:₹799");
                                    product_info.setText("Fabric:Cotton \nType:Polo \nFit:Regular");
                                    imageView.setImageResource(R.drawable.layout1);
                                }

                                else if(key == "Jeans")
                                {
                                    product.setImageResource(R.drawable.jeansh);
                                    product_name.setText("\nJeans");
                                    product_price.setText("\nPrice:₹1399");
                                    product_info.setText("Fabric:Denim \nType:Slim-fit \nFit:Regular");
                                    imageView.setImageResource(R.drawable.layout2);
                                }
                                else if(key == "Shoes")
                                {
                                    product.setImageResource(R.drawable.shoesh);
                                    product_name.setText("\nShoes");
                                    product_price.setText("\nPrice:₹1699");
                                    product_info.setText("Occassion:Sports \nWeight: 300gm \nColor:White nad Red");
                                    imageView.setImageResource(R.drawable.layout3);
                                }
                                else if(key == "Sunglasses")
                                {
                                    product.setImageResource(R.drawable.glassesh);
                                    product_name.setText("\nSunglasses");
                                    product_price.setText("\nPrice:₹899");
                                    product_info.setText("Type:Aviator \nIdeal for:Men,Women \nLens color:Black");
                                    imageView.setImageResource(R.drawable.layout4);
                                }
                            }
                        }
                    }
                    return true;
                }
            });
        }
}
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; go home
                Intent intent = new Intent(this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    protected void onStart()
    {
        super.onStart();
        Log.d("ABC","LayoutActivityStarted");
    }
    protected void onStop()
    {
        super.onStop();
        Log.d("ABC","LayoutActivityStopped");
    }
}
