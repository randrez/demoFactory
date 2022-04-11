package com.factory.demofactory.client;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.factory.demofactory.MainActivity;
import com.factory.demofactory.R;
import com.factory.demofactory.client.presenter.ClientPresenter;
import com.factory.demofactory.client.presenter.ClientPresenterImpl;
import com.factory.demofactory.client.view.ClientView;
import com.factory.demofactory.utilities.Util;
import com.github.clans.fab.FloatingActionButton;

import java.util.HashMap;
import java.util.Map;

public class ClientFragment extends Fragment implements ClientView, View.OnClickListener {

    private NavController navController;
    private ProgressDialog progressDialog;
    private EditText edtFirstName, edtLastName, edtIdentification;
    private ClientPresenter presenter;
    private String title;
    private String message;

    public ClientFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_client, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(getActivity() instanceof MainActivity){
            ((MainActivity) getActivity()).hideActionBar();
        }
        navController = Navigation.findNavController(view);
        presenter = new ClientPresenterImpl(getActivity(), this);
        settingProgressDialog();
        edtFirstName = (EditText) view.findViewById(R.id.edt_first_name);
        edtLastName = (EditText) view.findViewById(R.id.edt_last_name);
        edtIdentification = (EditText) view.findViewById(R.id.edt_identification);

        Button btnSaveClient = (Button) view.findViewById(R.id.btn_save_client);
        btnSaveClient.setOnClickListener(this);


        View header = (View) view.findViewById(R.id.header);
        TextView tittleHeader = (TextView) header.findViewById(R.id.txt_header);
        tittleHeader.setText(getString(R.string.creating_client));
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
    public void goCreateTransmitter(int clientId) {
        Bundle bundle = new Bundle();
        bundle.putInt("clientId", clientId);
        navController.navigate(R.id.next_action_create_transmitter, bundle);
    }

    private void settingProgressDialog() {
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setCancelable(false);
        progressDialog.setTitle(getString(R.string.creating_client));
        progressDialog.setMessage(getString(R.string.message_wait_create_client));
    }

    @Override
    public void onClick(View v) {
        Util.hideKeyboard(getActivity());
        if(v.getId() == R.id.btn_save_client){
            if(validateInputs()){
                Map<String, String> parameters = new HashMap<>();
                parameters.put("firstName", edtFirstName.getText().toString());
                parameters.put("lastName", edtLastName.getText().toString());
                parameters.put("identification", edtIdentification.getText().toString());
                presenter.createClient(parameters);
            }else{
                showMessage(title, message);
            }
        }
    }

    private boolean validateInputs(){
        this.title = getString(R.string.creating_client);
        boolean hasValidation = true;

        if(edtIdentification == null || "".equals(edtIdentification.getText().toString())){
            this.message = getString(R.string.enter_identification);
            hasValidation = false;
        }

        if(edtLastName == null || "".equals(edtLastName.getText().toString())){
            this.message = getString(R.string.enter_last_name);
            hasValidation = false;
        }

        if(edtFirstName == null || "".equals(edtFirstName.getText().toString())){
            this.message = getString(R.string.enter_first_name);
            hasValidation = false;
        }

        return  hasValidation;
    }

}