package com.factory.demofactory.transmitter;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.factory.demofactory.MainActivity;
import com.factory.demofactory.R;
import com.factory.demofactory.transmitter.presenter.TransmitterPresenter;
import com.factory.demofactory.transmitter.presenter.TransmitterPresenterImpl;
import com.factory.demofactory.transmitter.view.TransmitterView;
import com.factory.demofactory.utilities.Rif;
import com.factory.demofactory.utilities.Util;
import com.github.clans.fab.FloatingActionButton;

import java.util.HashMap;
import java.util.Map;


public class TransmitterFragment extends Fragment implements TransmitterView, View.OnClickListener {

    private NavController navController;
    private ProgressDialog progressDialog;
    private Spinner spnRif;
    private EditText edtBusinessName, edtNumberBusiness;
    private int clientId = 0;
    private String tittle;
    private String message;
    private TransmitterPresenter presenter;

    public TransmitterFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_transmitter, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(getActivity() instanceof MainActivity){
            ((MainActivity) getActivity()).hideActionBar();
        }

        presenter = new TransmitterPresenterImpl(getActivity(), this);

        navController = Navigation.findNavController(view);

        settingProgressDialog();

        edtBusinessName = (EditText) view.findViewById(R.id.edt_business_name);
        edtNumberBusiness = (EditText) view.findViewById(R.id.edt_number_business);

        spnRif = (Spinner) view.findViewById(R.id.spn_rif);
        spnRif.setAdapter(new ArrayAdapter<Rif>(getActivity(), android.R.layout.simple_spinner_item, Rif.values()));

        Button btnSaveTransmitter = (Button) view.findViewById(R.id.btn_save_transmitter);
        btnSaveTransmitter.setOnClickListener(this);

        View header = (View) view.findViewById(R.id.header);
        TextView tittle = (TextView) header.findViewById(R.id.txt_header);
        tittle.setText(getString(R.string.create_transmitter));

        if(getArguments() != null && getArguments().getInt("clientId") > 0){
            clientId = getArguments().getInt("clientId");
        }
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
    public void goCreateSale(int transmitterId) {
        if (clientId > 0){
            Bundle bundle = new Bundle();
            bundle.putInt("clientId", clientId);
            bundle.putInt("transmitterId", transmitterId);
            navController.navigate(R.id.next_action_create_sale, bundle);
        }
    }

    @Override
    public void onClick(View v) {
        Util.hideKeyboard(getActivity());
        if (v.getId() == R.id.btn_save_transmitter) {
            if(validateInputs()){
                Map<String, String> parameters = new HashMap<>();
                parameters.put("businessName", edtBusinessName.getText().toString());
                parameters.put("rif", spnRif.getSelectedItem().toString()+"-"+edtNumberBusiness.getText().toString());
                presenter.insertTransmitter(parameters);
            }else{
                showMessage(tittle, message);
            }
        }
    }

    private void settingProgressDialog() {
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setCancelable(false);
        progressDialog.setTitle(getString(R.string.create_transmitter));
        progressDialog.setMessage(getString(R.string.message_wait_create_transmitter));
    }

    private boolean validateInputs(){
        this.tittle = getString(R.string.create_transmitter);
        boolean hasValidation = true;

        if(edtBusinessName == null || "".equals(edtBusinessName.getText().toString())){
            this.message = getString(R.string.enter_business_name);
            hasValidation = false;
        }

        if(edtNumberBusiness == null || "".equals(edtNumberBusiness.getText().toString())){
            this.message = getString(R.string.enter_number_business);
            hasValidation = false;
        }

        return  hasValidation;
    }
}