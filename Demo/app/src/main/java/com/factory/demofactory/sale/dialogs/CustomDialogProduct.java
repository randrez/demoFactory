package com.factory.demofactory.sale.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.factory.database.entity.ProducView;
import com.factory.demofactory.MainActivity;
import com.factory.demofactory.R;
import com.factory.demofactory.utilities.Util;

import java.util.Date;

public class CustomDialogProduct {

    private final Context context;
    private Dialog dialog;
    private final CreateProductListener listener;

    public CustomDialogProduct(Context context, CreateProductListener listener) {
        this.context = context;
        this.listener = listener;
    }

    public void dialogCreateProduct() {
        dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_create_product);

        EditText edtCode = dialog.findViewById(R.id.edt_code_product);
        EditText edtDescription = dialog.findViewById(R.id.edt_description);
        EditText edtPrice = dialog.findViewById(R.id.edt_price);

        Button btnClose = dialog.findViewById(R.id.close_dialog);
        btnClose.setOnClickListener(v -> {
            dialog.dismiss();
            Util.hideKeyboard(((MainActivity)context));
        });
        Button btnSave = dialog.findViewById(R.id.save_product);
        btnSave.setOnClickListener(v -> {
            String message = "";
            if (edtCode != null && edtDescription != null && edtPrice != null) {
                boolean continuos = false;
                if(!edtCode.getText().toString().isEmpty()){
                    continuos = true;
                }else{
                    message = context.getString(R.string.no_enter_code);
                }
                if(!edtDescription.getText().toString().isEmpty()){
                    continuos = true;
                }else{
                    message = context.getString(R.string.no_enter_description);
                }
                if(!edtPrice.getText().toString().isEmpty() && Double.parseDouble(edtPrice.getText().toString()) > 0){
                    continuos = true;
                }else{
                    if(Double.parseDouble(edtPrice.getText().toString()) == 0){
                        message = context.getString(R.string.value_no_zero);
                    }else{
                        message = context.getString(R.string.no_enter_price);
                    }
                }
                if(continuos){
                    ProducView product = new ProducView();
                    product.id =  (int) (new Date().getTime()+1);
                    product.code = edtCode.getText().toString();
                    product.description = edtDescription.getText().toString();
                    product.price = Double.parseDouble(edtPrice.getText().toString());
                    product.quantity = 0;
                    listener.addProduct(product);
                }else{
                    listener.showMessageCreateProductToast(message);
                }
            }else{
                listener.showMessageCreateProductToast(context.getString(R.string.no_enter_information));
            }
        });
        dialog.show();
    }

    public void hideDialog() {
        dialog.dismiss();
    }
}
