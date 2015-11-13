package com.appgestor.domidomi.Activities;

import android.content.Intent;
import android.os.Bundle;

import com.appgestor.domidomi.Entities.ConfigSplash;
import com.appgestor.domidomi.R;
import com.appgestor.domidomi.cnst.Flags;
import com.appgestor.domidomi.dark.Accounts;
import com.daimajia.androidanimations.library.Techniques;


//extends AwesomeSplash!
public class YourActivity extends AwesomeSplash {

    //DO NOT OVERRIDE onCreate()!
    //if you need to start some services do it in initSplash()!


    @Override
    public void initSplash(ConfigSplash configSplash) {

        /* you don't have to override every property */

        //Customize Circular Reveal
        configSplash.setBackgroundColor(R.color.color_1); //any color you want form colors.xml
        configSplash.setAnimCircularRevealDuration(2000); //int ms
        configSplash.setRevealFlagX(Flags.REVEAL_RIGHT);  //or Flags.REVEAL_LEFT
        configSplash.setRevealFlagY(Flags.REVEAL_BOTTOM); //or Flags.REVEAL_TOP

        //Choose LOGO OR PATH; if you don't provide String value for path it's logo by default

        //Customize Logo
        configSplash.setLogoSplash(R.drawable.logo_movil_zonaapp); //or any other drawable
        configSplash.setAnimLogoSplashDuration(2000); //int ms
        configSplash.setAnimLogoSplashTechnique(Techniques.RollIn); //choose one form Techniques (ref: https://github.com/daimajia/AndroidViewAnimations)

        //Customize Path
        /*configSplash.setPathSplash(Constants.DROID_LOGO); //set path String
        configSplash.setOriginalHeight(400); //in relation to your svg (path) resource
        configSplash.setOriginalWidth(400); //in relation to your svg (path) resource
        configSplash.setAnimPathStrokeDrawingDuration(3000);
        configSplash.setPathSplashStrokeSize(3); //I advise value be <5
        configSplash.setPathSplashStrokeColor(R.color.accent); //any color you want form colors.xml
        configSplash.setAnimPathFillingDuration(3000);
        configSplash.setPathSplashFillColor(R.color.Wheat); //path object filling color*/

        //Customize Title
        configSplash.setTitleSplash("Domi Domi");
        configSplash.setTitleTextColor(R.color.actionBarColorText);
        configSplash.setTitleTextSize(30f); //float value
        configSplash.setAnimTitleDuration(3000);
        configSplash.setAnimTitleTechnique(Techniques.FlipInX);
        configSplash.setTitleFont("fonts/zona.ttf"); //provide string to your font located in assets/fonts/
    }

    @Override
    public void animationsFinished() {
        //transit to another activity the activity here
        Bundle bundle = new Bundle();
        startActivity(new Intent(YourActivity.this, Accounts.class).putExtras(bundle));
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        finish();
    }
}