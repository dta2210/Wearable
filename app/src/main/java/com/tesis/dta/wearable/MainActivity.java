package com.tesis.dta.wearable;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends ActionBarActivity {
    // Cambio de Alerta
    //Button btAlerta;
    int x;
    //Termino
    private ImageSwitcher imageSwitcher;

    private int[] gallery = {R.drawable.publicidad1, R.drawable.publicidad2, R.drawable.publicidad3,
            R.drawable.publicidad4, R.drawable.publicidad5, R.drawable.publicidad6, R.drawable.publicidad7};
    private int position;

    private static final Integer DURATION = 2500;
    private Timer timer = null;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageSwitcher = (ImageSwitcher)

                findViewById(R.id.imageSwitcher);

        imageSwitcher.setFactory(new ViewSwitcher.ViewFactory()

                                 {
                                     @Override
                                     public View makeView() {
                                         return new ImageView(MainActivity.this);
                                     }
                                 }

        );

        Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.abc_fade_in);
        Animation fadeOut = AnimationUtils.loadAnimation(this, R.anim.abc_fade_out);
        imageSwitcher.setAnimation(fadeIn);
        imageSwitcher.setAnimation(fadeOut);
        findViewById(R.id.btConfiguracion).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Configuracion.class));
            }
        });

    }
        // Otro cambio SMS
        public void alerta(View v){
        final Button btsms = (Button) findViewById(R.id.btAlerta);
        if (x == 0){
            x = 1;
            btsms.setText("Enviar Alerta");
            Toast.makeText(getBaseContext(), "Enviando mensaje de Alerta", Toast.LENGTH_LONG).show();
            sendsms();
            return;
        }
    }
        //Termina Toast.makeText(getBaseContext(), "Calculando la Presion Arterial", Toast.LENGTH_LONG).show();

        //cambio anterior
  //  btAlerta = (Button) findViewById(R.id.btAlerta);

   // btAlerta.setOnClickListener(new View.OnClickListener(){
     //   public void onClick(View v) {
       //     sendSMS("+51991301583", "Enviando Mensaje de Alerta");
    //    }
  //  });

        //termino anterior
//}
    //cambio
    public void sendsms(){
        sendSMS("981678044", "Necesito atención, vengan a ayudarme");
    }

    private void sendSMS(String phoneNumber, String message) {
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneNumber, null, message, null, null);
    }
    //termino
    public void start(View button) {
        if (timer != null) {
            timer.cancel();
        }
        position = 0;
        startSlider();
    }

    public void startSlider() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        imageSwitcher.setImageResource(gallery[position]);
                        position++;
                        if (position == gallery.length) {
                            position = 0;
                        }
                    }
                });
            }
        }, 0, DURATION);
    }

    protected void onPause() {
        super.onPause();
        if (timer != null) {
            timer.cancel();
        }
    }

    protected void onResume() {
        super.onResume();
        if (timer != null) {
            startSlider();
        }
    }

    public void clickRitmo(View view) {
        Toast.makeText(getBaseContext(), "Calculando el Ritmo Cardiaco", Toast.LENGTH_LONG).show();
    }

    public void clickPresion(View view) {
        Toast.makeText(getBaseContext(), "Calculando la Presion Arterial", Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}