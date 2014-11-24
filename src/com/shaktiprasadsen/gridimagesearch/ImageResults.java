package com.shaktiprasadsen.gridimagesearch;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ImageResults implements Serializable {

	private static final long serialVersionUID = -4689186355691215264L;
	private String url;
	private String title;
	
	ImageResults(JSONObject json) {
		try {
			this.url = json.getString("url");
			this.title = json.getString("title");
			//this.caption = caption;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
	}
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getTitle() {
		return title;
	}
	public void setCaption(String title) {
		this.title = title;
	}

	public static ArrayList<ImageResults> getFromJSONArray(JSONArray jsonArray) {
		ArrayList<ImageResults> result = new ArrayList<ImageResults>();
		for (int i = 0; i < jsonArray.length(); i++) {
			try {
				result.add(new ImageResults(jsonArray.getJSONObject(i)));
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
		}
		return result;
	}
	
	

}
