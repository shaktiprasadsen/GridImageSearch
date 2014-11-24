package com.shaktiprasadsen.gridimagesearch;

import java.util.ArrayList;
import java.util.List;

import com.example.gridimagesearch.R;
import com.squareup.picasso.Picasso;


import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ImageResultsAdapter extends ArrayAdapter<ImageResults> {

	public ImageResultsAdapter(Context context, List<ImageResults> images) {
		super(context, R.layout.item_grid_image_search, images);
		
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ImageResults imageInfo = getItem(position);
		if (convertView == null) {
				convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_grid_image_search, parent, false);
		}
		TextView tv = (TextView) convertView.findViewById(R.id.tvView);
		ImageView iv = (ImageView) convertView.findViewById(R.id.ivImage);
		iv.setImageResource(0);
		tv.setText(Html.fromHtml(imageInfo.getTitle()));
		Picasso.with(getContext()).load(imageInfo.getUrl()).into(iv);
		
		
		return convertView;
	}



}
