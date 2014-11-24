package com.shaktiprasadsen.gridimagesearch;

import java.util.ArrayList;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.gridimagesearch.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;


public class GridImageSearchActivity extends Activity {

    private static final String APIURL_1 = "https://ajax.googleapis.com/ajax/services/search/images?v=1.0&q=";
	private static final String APIURL_2 = "&rsz=8";
	private EditText etSearch;
	private GridView gvView;
	private ArrayList<ImageResults> imageResults;
	private ImageResultsAdapter imageResultsAdapter;
    
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_image_search);
        viewSetup();
        //Create the data source.
        imageResults = new ArrayList<ImageResults>();
        //Set the data source to an user adapter.
        imageResultsAdapter = new ImageResultsAdapter(this, imageResults);
        //Set the adapter to GridView.
        gvView.setAdapter(imageResultsAdapter);
        
    }


    private void viewSetup() {
		etSearch = (EditText) findViewById(R.id.etSearch);
		gvView = (GridView) findViewById(R.id.gvView);
		gvView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent(GridImageSearchActivity.this, FullImageActivity.class);
				ImageResults result = imageResults.get(position);
				intent.putExtra("result", result);
				startActivity(intent);
			}
		});
	}


	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.grid_image_search, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void searchImage(View v) {
    	String strSearch = etSearch.getEditableText().toString();
		Toast.makeText(this, "Searching : " + strSearch, Toast.LENGTH_SHORT).show();
		//ImageResults ir = new ImageResults("http://", "RA");
		//ImageResults ir1 = new ImageResults("http://rs.com", "RSRS");
		//imageResults.add(ir);
		//imageResults.add(ir1);
		
		AsyncHttpClient client = new AsyncHttpClient();
		String searchURL = APIURL_1 + strSearch + APIURL_2;
		client.get(searchURL, new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(int statusCode, Header[] headers,	JSONObject response) {
				Log.d("INFO", response.toString());
				JSONArray imgResultsJsonArray = null;
				try {
					imgResultsJsonArray = response.getJSONObject("responseData").getJSONArray("results");
					imageResults.clear();
					imageResults.addAll(ImageResults.getFromJSONArray(imgResultsJsonArray));
					imageResultsAdapter.notifyDataSetChanged();
				} catch (JSONException e) {
					e.printStackTrace();
				}
				
			}
		});
		
		//imageResultsAdapter.notifyDataSetChanged();
		
    }
}
