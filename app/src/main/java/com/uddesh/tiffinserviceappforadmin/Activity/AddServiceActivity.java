package com.uddesh.tiffinserviceappforadmin.Activity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.View;
import android.widget.*;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.github.siyamed.shapeimageview.mask.PorterShapeImageView;
import com.uddesh.tiffinserviceappforadmin.Helpers.SharedPreferencesHelper;
import com.uddesh.tiffinserviceappforadmin.Helpers.ToastHelper;
import com.uddesh.tiffinserviceappforadmin.R;
import com.uddesh.tiffinserviceappforadmin.Repository.PicassoRepository;
import com.uddesh.tiffinserviceappforadmin.Repository.RetrofitViewModel;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import java.io.File;

public class AddServiceActivity extends AppCompatActivity {
    private TextView addServiceTabTextView;
    private ImageView addServiceOptionsMenu;
    private EditText addServiceNameEditText,addServiceDescriptionEditText,addServicePriceEditText;
    private Button addServiceConfirmButton,addServiceUpdateButton;
    private PorterShapeImageView addServiceImageView;
    private String serviceName , description , username , mediaPath , foodImage;
    private int price , id;
    private File file;
    private ToastHelper toast;
    private RetrofitViewModel viewModel;
    private PicassoRepository picasso;
    private boolean active;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_service);
        initialiseComponents();
        clickListeners();

    }

    // private functions

    private void initialiseComponents()
    {
        addServiceTabTextView = findViewById(R.id.addServiceTabTextView);
        addServiceOptionsMenu = findViewById(R.id.addServiceOptionsMenu);
        addServiceNameEditText = findViewById(R.id.addServiceNameEditText);
        addServiceDescriptionEditText = findViewById(R.id.addServiceDescriptionEditText);
        addServicePriceEditText = findViewById(R.id.addServicePriceEditText);
        addServiceConfirmButton = findViewById(R.id.addServiceConfirmButton);
        addServiceUpdateButton = findViewById(R.id.addServiceUpdateButton);
        addServiceImageView = findViewById(R.id.addServiceImageView);
        toast = new ToastHelper(this);
        viewModel = new RetrofitViewModel(getApplication());
        SharedPreferencesHelper sharedPreferences = new SharedPreferencesHelper(this);
        picasso = new PicassoRepository();
        addServiceTabTextView.setText(R.string.add_service);
        username = sharedPreferences.getSharedPreferences("username");
        id = getIntent().getIntExtra("id" , 0);
        isUpdating();
    }



    private void clickListeners()
    {
        addServiceConfirmButton.setOnClickListener(v -> addService());

        addServiceUpdateButton.setOnClickListener(v -> updateService());

        addServiceImageView.setOnClickListener(v-> getImage.launch("image/*"));

        addServiceOptionsMenu.setOnClickListener(v-> openMenu());
    }

    private void addService() {
        serviceName = addServiceNameEditText.getText().toString();
        description = addServiceDescriptionEditText.getText().toString();
        file = new File(mediaPath);
        try{
            price = Integer.parseInt(addServicePriceEditText.getText().toString());
        }catch (Exception e){
            price = 0;
        }

        if(serviceName.equals("") || description.equals("") || price == 0 || file == null){
            toast.makeToast("Check entered data" , Toast.LENGTH_LONG);
        }
        else{
            String json = "{\n" +
                    "    \"foodimage\" : \" \",\n" +
                    "    \"servicename\" :\"" + serviceName +"\",\n" +
                    "    \"price\" :" +price+",\n" +
                    "    \"description\" :\"" +description+"\",\n" +
                    "    \"username\" :\"" + username +"\",\n" +
                    "    \"active\" : true\n" +
                    "}";
            RequestBody imageRequestBody = RequestBody.create(MediaType.parse("image/*") , file);
            MultipartBody.Part body = MultipartBody.Part.createFormData("foodImage", " ", imageRequestBody);
            RequestBody jsonData = RequestBody.create(MediaType.parse("multipart/form-data"), json);
            MultipartBody.Part data = MultipartBody.Part.create(jsonData);
            viewModel.addService(data , body).observe(this , result->{
                if(result){
                    toast.makeToast("success" , Toast.LENGTH_LONG);
                    finish();
                }
                else{
                    toast.makeToast("Something went wrong" , Toast.LENGTH_LONG);
                }
            });
        }
    }

    private void updateService(){
        serviceName = addServiceNameEditText.getText().toString();
        description = addServiceDescriptionEditText.getText().toString();
        try {
            file = new File(mediaPath);
        }catch (Exception ignore){}
        try{
            price = Integer.parseInt(addServicePriceEditText.getText().toString());
        }catch (Exception e){
            price = 0;
        }

        if(serviceName.equals("") || description.equals("") || price == 0){
            toast.makeToast("Check entered data" , Toast.LENGTH_LONG);
        }
        else {
            String json = "{\n" +
                    "    \"foodimage\" :\"" + foodImage +"\",\n" +
                    "    \"servicename\" :\"" + serviceName +"\",\n" +
                    "    \"price\" :\"" +price+"\",\n" +
                    "    \"description\" :\"" +description+"\",\n" +
                    "    \"username\" :\"" + username +"\",\n" +
                    "    \"active\" : true\n" +
                    "}";
            MultipartBody.Part body = null;
            if(file!=null) {
                RequestBody imageRequestBody = RequestBody.create(MediaType.parse("image/*"), file);
                body = MultipartBody.Part.createFormData("foodImage", " ", imageRequestBody);
            }
            RequestBody jsonData = RequestBody.create(MediaType.parse("multipart/form-data"), json);
            MultipartBody.Part data = MultipartBody.Part.create(jsonData);
            viewModel.updateService(id , data , body).observe(this , result->{
                if(result){
                    toast.makeToast("Service updated" , Toast.LENGTH_LONG);
                    finish();
                }
                else{
                    toast.makeToast("Something went wrong" , Toast.LENGTH_LONG);
                }
            });
        }
    }

    private void isUpdating() {
        if(id != 0){
            viewModel.getServiceDetails(id).observe(this , result->{
                if(result!=null){
                    String price = result.getPrice()+"";
                    addServiceNameEditText.setText(result.getServicename());
                    addServiceDescriptionEditText.setText(result.getDescription());
                    addServicePriceEditText.setText(price);
                    active = result.isActive();
                    foodImage = result.getFoodimage();
                    picasso.getFoodImage(foodImage , addServiceImageView);
                }
            });
            addServiceTabTextView.setText(R.string.update_service);
            addServiceUpdateButton.setVisibility(View.VISIBLE);
            addServiceConfirmButton.setVisibility(View.GONE);
            addServiceOptionsMenu.setVisibility(View.VISIBLE);
        }
    }

    ActivityResultLauncher<String> getImage = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
        @Override
        public void onActivityResult(Uri result) {
            if(result!=null){
                String[] filePathColumn = {MediaStore.Images.Media.DATA};
                Cursor cursor = getContentResolver().query(result , filePathColumn , null , null , null);
                assert cursor != null;
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                mediaPath = cursor.getString(columnIndex);
                addServiceImageView.setImageBitmap(BitmapFactory.decodeFile(mediaPath));
                cursor.close();
            }
        }
    });

    @SuppressLint("NonConstantResourceId")
    private void openMenu() {
        PopupMenu popupMenu = new PopupMenu(this , addServiceOptionsMenu);
        popupMenu.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()){
                case R.id.deleteService:
                    viewModel.deleteService(id).observe(this , result->{
                        if(result){
                            toast.makeToast("Service deleted successfully" , Toast.LENGTH_LONG);
                            finish();
                        }
                        else{
                            AlertDialog.Builder builder = new AlertDialog.Builder(this);
                            builder.setMessage("There are still active users. Service cannot be deleted, deactivate instead")
                                    .setCancelable(true)
                                    .setPositiveButton("OK" , null);
                            AlertDialog alertDialog = builder.create();
                            alertDialog.show();
                        }
                    });
                    break;
                case R.id.deactivateService:
                    viewModel.updateServiceStatus(id , false).observe(this , result->{
                        if(result){
                            toast.makeToast("Service deactivated" , Toast.LENGTH_LONG);
                            finish();
                        }
                    });
                    break;

                case  R.id.activateService:
                    viewModel.updateServiceStatus(id , true).observe(this , result->{
                        if(result){
                            toast.makeToast("Service activated" , Toast.LENGTH_LONG);
                            finish();
                        }
                    });
                    break;
            }
            return false;
        });
        popupMenu.inflate(R.menu.service_menu);
        if(active){
            popupMenu.getMenu().getItem(0).setVisible(true);
            popupMenu.getMenu().getItem(1).setVisible(false);
        }
        popupMenu.show();
    }
}
