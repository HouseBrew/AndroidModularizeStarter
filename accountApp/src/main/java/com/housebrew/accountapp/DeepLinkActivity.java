package com.housebrew.accountapp;

import android.app.Activity;
import android.os.Bundle;
import com.airbnb.deeplinkdispatch.DeepLinkHandler;
import com.housebrew.account.AccountDeepLinkModule;
import com.housebrew.account.AccountDeepLinkModuleLoader;

@DeepLinkHandler({ AccountDeepLinkModule.class })
public class DeepLinkActivity extends Activity {
  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    DeepLinkDelegate deepLinkDelegate = 
        new DeepLinkDelegate(new AccountDeepLinkModuleLoader());
    deepLinkDelegate.dispatchFrom(this);
    finish();
  }
}