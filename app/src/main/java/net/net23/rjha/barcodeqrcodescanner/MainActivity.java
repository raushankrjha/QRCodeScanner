package net.net23.rjha.barcodeqrcodescanner;

import android.*;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.NativeExpressAdView;
import com.google.android.gms.ads.VideoOptions;
import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;
import com.google.zxing.Result;

import java.io.IOException;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,ZXingScannerView.ResultHandler

{
   ImageButton b1;
    AdView mAdView,mAdView1;
    public InterstitialAd interstitialAd,interstitialAd2,interstitialAd3;
    public ZXingScannerView zXingScannerView;
    public TextView testresult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        testresult=(TextView)findViewById(R.id.testresult);

        MobileAds.initialize(this,"ca-app-pub-8928299189790970/6351858119");
        mAdView=(AdView)findViewById(R.id.adview);
        AdRequest adRequest=new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        MobileAds.initialize(this,"ca-app-pub-8928299189790970/1544262294");
        mAdView1=(AdView)findViewById(R.id.adview);
        AdRequest adRequest1=new AdRequest.Builder().build();
        mAdView1.loadAd(adRequest1);

        interstitialAd=new InterstitialAd(this);
        interstitialAd.setAdUnitId("ca-app-pub-8928299189790970/2692648856");
        interstitialAd.loadAd(new AdRequest.Builder().build());
        interstitialAd.setAdListener(new AdListener()
        {
            public void onAdClosed() {

                Intent i=new Intent(MainActivity.this,Main3Activity.class);
                startActivity(i);
                interstitialAd.loadAd(new AdRequest.Builder().build());
            }
    });
        interstitialAd2=new InterstitialAd(this);
        interstitialAd2.setAdUnitId("ca-app-pub-8928299189790970/2692648856");
        interstitialAd2.loadAd(new AdRequest.Builder().build());
        interstitialAd2.setAdListener(new AdListener()
        {
            public void onAdClosed() {


                interstitialAd2.loadAd(new AdRequest.Builder().build());
            }
        });
        interstitialAd3=new InterstitialAd(this);
        interstitialAd3.setAdUnitId("ca-app-pub-8928299189790970/1145506771");
        interstitialAd3.loadAd(new AdRequest.Builder().build());
        interstitialAd3.setAdListener(new AdListener()
        {
            public void onAdClosed() {

                finish();
                System.gc();
                System.exit(0);
                interstitialAd3.loadAd(new AdRequest.Builder().build());
            }
        });
b1=(ImageButton)findViewById(R.id.button2);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                zXingScannerView=new ZXingScannerView(getApplicationContext());
                setContentView(zXingScannerView);
                zXingScannerView.setResultHandler(MainActivity.this);
                zXingScannerView.startCamera();

                //finish();
                //Intent i=new Intent(MainActivity.this,Main2Activity.class);
                //startActivity(i);
            }
        });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {

                finish();
                System.gc();
                System.exit(0);

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();



        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        if (id == R.id.nav_share)
        {
            Intent i=new Intent(Intent.ACTION_SEND);
            i.setType("text/plain");
            String body="https://play.google.com/store/apps/details?id=com.rjha.barcodeqrcodescanner&hl=en";
            String sub="https://play.google.com/store/apps/details?id=com.rjha.barcodeqrcodescanner&hl=en";
            i.putExtra(i.EXTRA_SUBJECT,sub);
            i.putExtra(i.EXTRA_TEXT,body);
            startActivity(Intent.createChooser(i,"share via"));

        }
        else if (id == R.id.nav_create)
        {

            Intent i=new Intent(MainActivity.this,Main3Activity.class);
            startActivity(i);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void handleResult(final Result result)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);

        builder.setTitle("Scan Result");

        builder.setMessage(result.getText());
        builder.setCancelable(false);
        builder.setNegativeButton("ReScan", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                zXingScannerView=new ZXingScannerView(getApplicationContext());
                setContentView(zXingScannerView);
                zXingScannerView.setResultHandler(MainActivity.this);
                zXingScannerView.startCamera();
            }
        });
        builder.setPositiveButton("Share", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent i1=new Intent(Intent.ACTION_SEND);
                i1.setType("text/plain");
                String body="https://play.google.com/store/apps/details?id=net.net23.rjha.barcodeqrcodescanner&hl=en";
                String sub="https://play.google.com/store/apps/details?id=net.net23.rjha.barcodeqrcodescanner&hl=en";
                i1.putExtra(i1.EXTRA_SUBJECT,result.getText());
                i1.putExtra(i1.EXTRA_TEXT,result.getText());
                startActivity(Intent.createChooser(i1,"share via"));

            }
        });
builder.setNeutralButton("Home", new DialogInterface.OnClickListener() {
    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
        finish();
       Intent i3=new Intent(MainActivity.this,MainActivity.class);
        startActivity(i3);
    }
});
        AlertDialog alert1=builder.create();
        alert1.show();
        zXingScannerView.resumeCameraPreview(this);
        zXingScannerView.stopCamera();
        testresult.post(new Runnable() {
            @Override
            public void run() {
                Vibrator vibrator=(Vibrator)getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                vibrator.vibrate(100);



            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        zXingScannerView.stopCamera();
    }

}
