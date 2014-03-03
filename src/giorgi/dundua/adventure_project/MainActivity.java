package giorgi.dundua.adventure_project;


import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends FragmentActivity {
	private GoogleMap map;
	private final LatLng LOCATION_PUB = new LatLng(57.698700, 11.944566);
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		map = ((SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
		map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
			
		
		map.setMyLocationEnabled(true);
		LocationManager locMan = (LocationManager)getSystemService(LOCATION_SERVICE);
		
		Criteria criteria = new Criteria();
		String provider = locMan.getBestProvider(criteria, true);
		Location myCurrLocation = locMan.getLastKnownLocation(provider);
		
		double lat = myCurrLocation.getLatitude();
		double longt = myCurrLocation.getLongitude();
		LatLng latLong = new LatLng(lat, longt);
		
		map.moveCamera(CameraUpdateFactory.newLatLng(latLong)); 
		map.animateCamera(CameraUpdateFactory.zoomTo(12));
		map.addMarker(new MarkerOptions().position(latLong).title("You Are Here").icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_finn)));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	public void onClickMap (View v) {
		map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
	}
	public void onClickSatellite (View v) {
		map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
	}
	public void onClickHybrid (View v) {
		map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
	}
	public void onClickPubs(View v) {
		CameraUpdate update = CameraUpdateFactory.newLatLngZoom(LOCATION_PUB, 16);
		map.animateCamera(update);
		map.addMarker(new MarkerOptions().position(LOCATION_PUB).title("You Are Here").icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_beer_active)));
	    
	}
	
	

}
