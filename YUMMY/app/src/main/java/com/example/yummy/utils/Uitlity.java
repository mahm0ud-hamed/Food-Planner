package com.example.yummy.utils;

import static androidx.core.content.ContextCompat.getSystemService;
import static androidx.navigation.ActivityNavigatorDestinationBuilderKt.activity;
import static java.security.AccessController.getContext;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.view.WindowManager;
import android.widget.Button;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.yummy.R;
import com.example.yummy.ui.Favourite.FavouriteFragment;

public class Uitlity {
//
//    public static void noNetworkDilaog(Context context , Activity activity){
//        Dialog dialog = new Dialog(context) ;
//        dialog.setContentView(R.layout.no_internet_dialog);
//        dialog.setCancelable(false);
//        dialog.getWindow().setLayout(WindowManager.LayoutParams.WRAP_CONTENT , WindowManager.LayoutParams.WRAP_CONTENT);
//        Button button = dialog.findViewById(R.id.bntRetry);
//        Button btnTofav = dialog.findViewById(R.id.btnAddtFav) ;
//        btnTofav.setOnClickListener((View)->{
//        });
//        button.setOnClickListener((View)->{
//            activity.recreate();
//        });
//        dialog.show();
//    }

    /*chehcking if the network in connecting or not */
    public static Boolean isInternetConnected(Context context) {
        Boolean internetStatus = false;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                NetworkCapabilities capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
                internetStatus = capabilities != null && capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET);
            } else {
                NetworkInfo activeNetWork = connectivityManager.getActiveNetworkInfo();
                internetStatus = activeNetWork != null && activeNetWork.isConnectedOrConnecting();
            }
        }
        return internetStatus;
    }
}
