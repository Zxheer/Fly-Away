package za.co.dvt.spillay.flyaway.ViewModel.AddFlight.presentation.flight.list;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import za.co.dvt.spillay.flyaway.R;
import za.co.dvt.spillay.flyaway.ViewModel.AddFlight.data.Flight;
import za.co.dvt.spillay.flyaway.ViewModel.AddFlight.presentation.flight.add.AddFlight;

/**
 * Created by SPillay on 2018/02/06.
 */

public class FlightListActivity extends AppCompatActivity
{
    RecyclerView recyclerViewFlights;
    FlightListAdapter flightsAdapter;
    FlightViewModel flightViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_list);
        recyclerViewFlights = findViewById(R.id.recycler_view_flights);
        recyclerViewFlights.setLayoutManager(new LinearLayoutManager(this));
        flightsAdapter = new FlightListAdapter(null);
        recyclerViewFlights.setAdapter(flightsAdapter);
        setupViewModels();
        setupButtons();
    }

    private void setupButtons()
    {
        FloatingActionButton floatingActionButton = findViewById(R.id.fab_add_flight);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(FlightListActivity.this, AddFlight.class));
            }
        });
    }

    private void setupViewModels()
    {
        flightViewModel = ViewModelProviders.of(this).get(FlightViewModel.class);

        flightViewModel.getFlights().observe(this, new Observer<List<Flight>>()
        {
            @Override
            public void onChanged(@Nullable List<Flight> flights)
            {
                flightsAdapter.setFlights(flights);
            }
        });
    }
}