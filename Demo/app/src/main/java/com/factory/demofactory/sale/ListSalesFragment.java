package com.factory.demofactory.sale;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.factory.demofactory.MainActivity;
import com.factory.demofactory.R;
import com.factory.demofactory.sale.adapter.OnSelectedItemSale;
import com.factory.demofactory.sale.adapter.SaleAdapter;
import com.factory.demofactory.sale.presenter.ListSalesPresenter;
import com.factory.demofactory.sale.presenter.ListSalesPresenterImpl;
import com.factory.demofactory.sale.view.ListSalesView;

import org.json.JSONObject;

import java.util.List;

public class ListSalesFragment extends Fragment implements ListSalesView, OnSelectedItemSale {

    private NavController navController;
    private ListSalesPresenter presenter;
    private ProgressDialog progressDialog;
    private RecyclerView listSale;

    public ListSalesFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list_sales, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(getActivity() instanceof MainActivity){
            ((MainActivity) getActivity()).showActionBar();
            ((MainActivity) getActivity()).changeNameActionBar(R.string.list_sale);
        }

        settingProgressDialog();

        navController = Navigation.findNavController(view);
        presenter = new ListSalesPresenterImpl(getActivity(), this);

        listSale = (RecyclerView) view.findViewById(R.id.list_sale);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        listSale.setLayoutManager(layoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(listSale.getContext(), layoutManager.getOrientation());
        listSale.addItemDecoration(dividerItemDecoration);

        presenter.getSales();
    }

    @Override
    public void showMessage(String tittle, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(tittle);
        builder.setMessage(message)
                .setCancelable(false)
                .setPositiveButton(getString(R.string.accept), (dialog, id) -> dialog.dismiss()).create();
        builder.show();
    }

    @Override
    public void showProgressDialog() {
        progressDialog.show();
    }

    @Override
    public void hideProgressDialog() {
        progressDialog.hide();
    }

    @Override
    public void getListSale(List<JSONObject> sales) {
        SaleAdapter adapter = new SaleAdapter(getActivity(), sales, this);
        listSale.setAdapter(adapter);
    }

    private void settingProgressDialog() {
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setCancelable(false);
        progressDialog.setTitle(getString(R.string.create_sale));
        progressDialog.setMessage(getString(R.string.message_wait_create_sale));
    }

    @Override
    public void selectItemSale(int saleId) {
        Bundle bundle = new Bundle();
        bundle.putInt("saleId", saleId);
        navController.navigate(R.id.action_print_sale, bundle);
    }
}