package com.factory.demofactory.home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.factory.demofactory.MainActivity;
import com.factory.demofactory.R;
import com.factory.demofactory.home.presenter.HomePresenter;
import com.factory.demofactory.home.presenter.HomePresenterImpl;
import com.factory.demofactory.home.view.HomeView;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

public class HomeFragment extends Fragment implements HomeView, View.OnClickListener {

    private NavController navController;
    private FloatingActionButton listTransmitter;
    private FloatingActionButton listClients;
    private FloatingActionButton listSales;

    public HomeFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(getActivity() instanceof MainActivity){
            ((MainActivity) getActivity()).showActionBar();
            ((MainActivity) getActivity()).changeNameActionBar(R.string.home);
        }

        navController = Navigation.findNavController(view);
        HomePresenter presenter = new HomePresenterImpl(getActivity(), this);
        FloatingActionMenu floatMenu  = (FloatingActionMenu) view.findViewById(R.id.float_menu);

        FloatingActionButton createSale = (FloatingActionButton) view.findViewById(R.id.create_sale);
        createSale.setOnClickListener(this);

        listTransmitter = (FloatingActionButton) view.findViewById(R.id.list_transmitter);
        listTransmitter.setOnClickListener(this);

        listClients = (FloatingActionButton) view.findViewById(R.id.list_clients);
        listClients.setOnClickListener(this);

        listSales = (FloatingActionButton) view.findViewById(R.id.list_sale);
        listSales.setOnClickListener(this);

        hideItemClients();
        hideItemSales();
        hideItemTransmitter();
        presenter.hasListTransmitter();
        presenter.hasListClients();
        presenter.hasListSales();
    }

    @Override
    public void showItemTransmitter() {
        listTransmitter.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideItemTransmitter() {
        listTransmitter.setVisibility(View.GONE);
    }

    @Override
    public void showItemSales() {
        listSales.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideItemSales() {
        listSales.setVisibility(View.GONE);
    }

    @Override
    public void showItemClients() {
        listClients.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideItemClients() {
        listClients.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.create_sale:
                navController.navigate(R.id.next_action_create_client);
                break;
            case R.id.list_transmitter:
                navController.navigate(R.id.next_action_transmitter_list);
                break;
            case R.id.list_clients:
                navController.navigate(R.id.next_action_list_clients);
                break;
            case R.id.list_sale:
                navController.navigate(R.id.next_action_list_sales);
                break;
        }
    }
}