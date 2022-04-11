package com.factory.demofactory.sale;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.factory.database.entity.ProducView;
import com.factory.demofactory.MainActivity;
import com.factory.demofactory.R;
import com.factory.demofactory.sale.adapter.OnChangeQuantityListener;
import com.factory.demofactory.sale.adapter.ProductAdapter;
import com.factory.demofactory.sale.dialogs.CreateProductListener;
import com.factory.demofactory.sale.dialogs.CustomDialogProduct;
import com.factory.demofactory.sale.presenter.CreateSalePresenter;
import com.factory.demofactory.sale.presenter.CreateSalePresenterImpl;
import com.factory.demofactory.sale.view.CreateSaleView;
import com.factory.demofactory.utilities.Util;

import java.util.ArrayList;
import java.util.List;

public class CreateSaleFragment extends Fragment implements View.OnClickListener, CreateProductListener, CreateSaleView, OnChangeQuantityListener {

    private NavController navController;
    private ProgressDialog progressDialog;
    private int clientId;
    private int transmitterId;
    private List<ProducView> products;
    private TextView total;
    private RecyclerView listProducts;
    private CreateSalePresenter presenter;
    private CustomDialogProduct customDialogProduct;

    public CreateSaleFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_create_sale, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);
        presenter = new CreateSalePresenterImpl(getActivity(), this);
        products = new ArrayList<>();
        customDialogProduct = new CustomDialogProduct(getActivity(), this);

        settingProgressDialog();

        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).hideActionBar();
        }

        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        TextView title = toolbar.findViewById(R.id.title_toolbar);
        title.setText(getString(R.string.create_sale));

        LinearLayout createProduct = (LinearLayout) toolbar.findViewById(R.id.add_product);
        createProduct.setOnClickListener(this);

        if(getArguments() != null && getArguments().getInt("clientId") > 0){
            clientId = getArguments().getInt("clientId");
        }

        if(getArguments() != null && getArguments().getInt("transmitterId") > 0){
            transmitterId = getArguments().getInt("transmitterId");
        }

        total = (TextView) view.findViewById(R.id.total);

        listProducts = (RecyclerView) view.findViewById(R.id.list_products);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        listProducts.setLayoutManager(layoutManager);

        LinearLayout printSale = (LinearLayout) view.findViewById(R.id.print_sale);
        printSale.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.add_product){
            showDialogProduct();
        }else if(id == R.id.print_sale){
            int total = (int) presenter.calculateTotal(products);
            if (total > 0){
                presenter.createSale(products, clientId, transmitterId);
            }else{
                showMessageCreateProductToast(getString(R.string.total_zero));
            }
        }
    }

    @Override
    public void addProduct(ProducView productList) {
        products.add(productList);
        hideDialogProduct();
        drawProductList(products);
    }

    @Override
    public void showMessageCreateProductToast(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
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
    public void drawProductList(List<ProducView> products) {
        ProductAdapter adapter = new ProductAdapter(getActivity(), products);
        adapter.setListener(this);
        listProducts.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void calculateTotal(List<ProducView> products) {
        double totalProducts = presenter.calculateTotal(products);
        total.setText("$"+ (int) totalProducts);
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
    public void goPrintSale(int saleId) {
        Bundle bundle = new Bundle();
        bundle.putInt("saleId", saleId);
        navController.navigate(R.id.next_action_print_sale, bundle);
    }

    @Override
    public void showDialogProduct() {
        customDialogProduct.dialogCreateProduct();
    }

    @Override
    public void hideDialogProduct() {
        Util.hideKeyboard(getActivity());
        customDialogProduct.hideDialog();
    }

    private void settingProgressDialog() {
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setCancelable(false);
        progressDialog.setTitle(getString(R.string.create_sale));
        progressDialog.setMessage(getString(R.string.message_wait_create_sale));
    }

    @Override
    public void updateProductsEdt() {
        calculateTotal(products);
    }
}