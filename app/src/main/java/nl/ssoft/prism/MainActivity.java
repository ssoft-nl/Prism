package nl.ssoft.prism;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;


public class MainActivity extends Activity {
    private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    private final static String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (checkGooglePlayServices()) {

            LayoutInflater inflater = getLayoutInflater();

            Message[] data = {
                new Message("Sickbeard", "Sickbeard fetched a new episode!", "Suits S03E10"),
                new Message("SABnzbd", "SABnzbd finished downloading.", "Suits S03E10"),
            };
            MessageAdapter adapter = new MessageAdapter(this, data);
            ListView listView = (ListView) findViewById(R.id.list_notifications);
            listView.setAdapter(adapter);
            TextView header = (TextView) inflater.inflate(R.layout.header, listView, false);
            header.setText(R.string.title_recent_notifications);
            listView.addHeaderView(header);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        checkGooglePlayServices();
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

        if (id == R.id.action_providers) {
            Intent intent = new Intent (this, ProviderActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private boolean checkGooglePlayServices() {
        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        Log.i(TAG, "Checking support for Google Play Services...");
        if (resultCode != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                GooglePlayServicesUtil.getErrorDialog(resultCode, this,
                        PLAY_SERVICES_RESOLUTION_REQUEST).show();
            } else {
                Log.i(TAG, "This device is not supported.");
                finish();
            }
            return false;
        }
        Log.i(TAG, "This device is supported!");
        return true;
    }

    public class MessageAdapter extends ArrayAdapter<Message> {
        public MessageAdapter(Context context, Message[] data) {
            super(context, R.layout.widget_message, data);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater layoutInflater =
                    (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View widget = layoutInflater.inflate(R.layout.widget_message, parent, false);
            TextView provider = (TextView) widget.findViewById(R.id.widget_message_provider);
            TextView shortText = (TextView) widget.findViewById(R.id.widget_message_short_text);

            Message item = getItem(position);

            provider.setText(item.getProvider());
            shortText.setText(item.getShortText());

            return widget;
        }
    }
}
