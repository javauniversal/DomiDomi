package com.appgestor.domidomi.Activities;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.appgestor.domidomi.R;
import com.appgestor.domidomi.ViewPager.SlidingTabLayout;
import com.appgestor.domidomi.mockedFragments.FragMenu;

import java.util.HashMap;
import java.util.Map;

import static com.appgestor.domidomi.Entities.Sede.getSedeStaticNew;

public class ActMenu extends AppCompatActivity {

    private RequestQueue rq;
    public static final String TAG = "MyTag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        toolbar.setTitle(getSedeStaticNew().getDescripcion());
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        ViewPager mPager = (ViewPager) findViewById(R.id.pager);

        mPager.setAdapter(new MyClasPagerAdapter(getSupportFragmentManager()));
        SlidingTabLayout mTabs = (SlidingTabLayout) findViewById(R.id.tabs);
        mTabs.setBackgroundColor(getResources().getColor(R.color.color_1));
        mTabs.setSelectedIndicatorColors(getResources().getColor(R.color.colorAccent));
        mTabs.setDistributeEvenly(true);
        mTabs.setViewPager(mPager);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_menu, menu);
        return true;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case R.id.action_cart:
                Bundle bundle = new Bundle();
                bundle.putInt("compania", getSedeStaticNew().getIdempresa());
                startActivity(new Intent(ActMenu.this, ActCar.class).putExtras(bundle));
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                return true;

        }

        return super.onOptionsItemSelected(item);
    }

    public void setFavorito(){

        String url = String.format("%1$s%2$s", getString(R.string.url_base), "setFavoritos");
        rq = Volley.newRequestQueue(this);

        StringRequest jsonRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response) {
                        // response
                        Toast.makeText(ActMenu.this, response, Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        startActivity(new Intent(ActMenu.this, DetailsActivity.class).putExtra("STATE", "ERROR"));
                        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                TelephonyManager telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);

                //params.put("descripcion", getEmpresastatic().getDescripcion());
                params.put("idTelefono", telephonyManager.getDeviceId());
                params.put("idEmpresa", String.valueOf(getSedeStaticNew().getIdempresa()));

                return params;

            }

        };
        rq.add(jsonRequest);
    }

    @Override
    protected void onStop () {
        super.onStop();
        if (rq != null) {
            rq.cancelAll(TAG);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (rq != null) {
            rq.cancelAll(TAG);
        }
    }

    public class MyClasPagerAdapter extends FragmentPagerAdapter {

        String[] tabas;

        public MyClasPagerAdapter(FragmentManager fm) {
            super(fm);
            tabas = getResources().getStringArray(R.array.tabs);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabas[position];
        }

        @Override
        public Fragment getItem(int position) {
            Bundle arguments = new Bundle();
            arguments.putInt("position", position);
            FragMenu myFragment;
            myFragment = FragMenu.newInstance(arguments);

            return myFragment;
        }

        @Override
        public int getCount() {
            return tabas.length;
        }
    }

}
