package com.factory.demofactory.sale;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.factory.database.entity.ProducView;
import com.factory.demofactory.MainActivity;
import com.factory.demofactory.R;
import com.factory.demofactory.sale.adapter.ProductSaleAdapter;
import com.factory.demofactory.sale.presenter.PrintSalePresenter;
import com.factory.demofactory.sale.presenter.PrintSalePresenterImpl;
import com.factory.demofactory.sale.view.PrintSaleView;

import java.util.List;
import java.util.Map;

public class PrintSaleFragment extends Fragment implements View.OnClickListener, PrintSaleView {

    private NavController navController;
    private ProgressDialog progressDialog;
    private TextView transmitterName, transmitterRif, clientName, clientCi, total;
    private RecyclerView listProducts;

    public PrintSaleFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_print_sale, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        settingProgressDialog();

        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).hideActionBar();
        }
        Button btnAccept = (Button) view.findViewById(R.id.btn_accept);
        btnAccept.setOnClickListener(this);

        listProducts = (RecyclerView) view.findViewById(R.id.list_products_print);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        listProducts.setLayoutManager(layoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(listProducts.getContext(), layoutManager.getOrientation());
        listProducts.addItemDecoration(dividerItemDecoration);

        transmitterName = (TextView) view.findViewById(R.id.transmitter_name);
        transmitterRif = (TextView) view.findViewById(R.id.transmitter_rif);
        clientName = (TextView) view.findViewById(R.id.client_name);
        clientCi = (TextView) view.findViewById(R.id.client_ci);
        total = (TextView) view.findViewById(R.id.total);

        PrintSalePresenter presenter = new PrintSalePresenterImpl(getActivity(), this);
        if (getArguments() != null && getArguments().getInt("saleId") > 0) {
            presenter.informationSale(getArguments().getInt("saleId"));
        } else {
            navController.navigate(R.id.back_to_home);
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_accept) {
            navController.navigate(R.id.back_to_home);
        }
    }

    @Override
    public void getSale(Map<String, String> dataSale, List<ProducView> products) {
        transmitterName.setText(dataSale.get("business"));
        transmitterRif.setText(dataSale.get("rif"));
        clientName.setText(dataSale.get("client"));
        clientCi.setText(dataSale.get("ci"));
        total.setText("$" + dataSale.get("total"));
        drawList(products);
    }

    @Override
    public void drawList(List<ProducView> products) {
        ProductSaleAdapter adapter = new ProductSaleAdapter(getActivity(), products);
        listProducts.setAdapter(adapter);
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
    public void message(String tittle, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(tittle);
        builder.setMessage(message)
                .setCancelable(false)
                .setPositiveButton(getString(R.string.accept), (dialog, id) -> dialog.dismiss()).create();
        builder.show();
    }

    private void settingProgressDialog() {
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setCancelable(false);
        progressDialog.setTitle(getString(R.string.create_sale));
        progressDialog.setMessage(getString(R.string.message_wait_create_sale));
    }
}