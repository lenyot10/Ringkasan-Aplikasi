package com.IntentImplisit.tugas1;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    private EditText mWebsiteEdiText,mTelpEditText;
    private EditText OpenLocation;
    private EditText ShareTextEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTelpEditText = (EditText)findViewById(R.id.Telp);
        mWebsiteEdiText = (EditText) findViewById(R.id.website_edit);
        OpenLocation = (EditText) findViewById(R.id.location_edit);
        ShareTextEditText = (EditText) findViewById(R.id.share_edit);

    }

    public void openWebsite(View view) {
        String url = mWebsiteEdiText.getText().toString();
        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        //intent.setData(webpage);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Log.d("ImplicitIntents", "tidak bisa menangani ini!");
        }
    }

    public void openLocation(View view) {
        // Get the string indicating a location. Input is not validated; it is
        // passed to the location handler intact.
        String loc = OpenLocation.getText().toString();

        // Parse the location and create the intent.
        Uri addressUri = Uri.parse("geo:0,0?q=" + loc);
        Intent intent = new Intent(Intent.ACTION_VIEW, addressUri);

        // Find an activity to handle the intent, and start that activity.
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Log.d("ImplicitIntents", "Can't handle this intent!");
        }
    }

    public void shareText(View view) {
        String txt = ShareTextEditText.getText().toString();
        File outputFile = new File(Environment.getExternalStoragePublicDirectory
                (Environment.DIRECTORY_DOWNLOADS), "example.pdf");
        String mimeType = "text/plain";

        ShareCompat.IntentBuilder
                .from(this)
                .setType(mimeType)
                .setChooserTitle("Share this text with: ")
                .setText(txt)
                .startChooser();
    }
    public void call(View view){
        String phone = mTelpEditText.getText().toString();
        Intent e = new Intent(Intent.ACTION_DIAL);
        e.setData(Uri.parse("tel:"+phone));
        if(e.resolveActivity(getPackageManager()) !=null){
            startActivity(e);
        }
    }
}
/*File outputFile = new File(Environment.getExternalStoragePublicDirectory
        (Environment.DIRECTORY_DOWNLOADS), "example.pdf");
    Uri uri = Uri.fromFile(outputFile);

    Intent share = new Intent();
share.setAction(Intent.ACTION_SEND);
        share.setType("application/pdf");
        share.putExtra(Intent.EXTRA_STREAM, uri);
        share.setPackage("com.whatsapp");

        activity.startActivity(share);
        */
