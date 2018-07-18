package com.housebrew.modularizestarter;

import android.app.Activity;
import android.os.Bundle;
import com.airbnb.deeplinkdispatch.DeepLinkHandler;
import com.housebrew.account.AccountDeepLinkModule;
import com.housebrew.account.AccountDeepLinkModuleLoader;
import com.housebrew.modularizestarter.AppDeepLinkModule;
import com.housebrew.modularizestarter.AppDeepLinkModuleLoader;
import com.housebrew.modularizestarter.DeepLinkDelegate;

@DeepLinkHandler({AppDeepLinkModule.class, AccountDeepLinkModule.class})
public class DeepLinkActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // DeepLinkDelegate, LibraryDeepLinkModuleLoader and AppDeepLinkModuleLoader
        // are generated at compile-time.
        DeepLinkDelegate deepLinkDelegate =
                new DeepLinkDelegate(new AppDeepLinkModuleLoader(), new AccountDeepLinkModuleLoader());
        // Delegate the deep link handling to DeepLinkDispatch.
        // It will start the correct Activity based on the incoming Intent URI
        deepLinkDelegate.dispatchFrom(this);
        // Finish this Activity since the correct one has been just started
        finish();
    }
}