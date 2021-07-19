package com.uddesh.tiffinserviceappforadmin.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.*;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.uddesh.tiffinserviceappforadmin.Helpers.SharedPreferencesHelper;
import com.uddesh.tiffinserviceappforadmin.Helpers.ToastHelper;
import com.uddesh.tiffinserviceappforadmin.R;
import com.uddesh.tiffinserviceappforadmin.Repository.PicassoRepository;
import com.uddesh.tiffinserviceappforadmin.Repository.RetrofitViewModel;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import java.io.File;

public class PersonalDetailsActivity extends AppCompatActivity {
    private CheckBox dinner_checkbox,veg_checkbox,non_veg_checkbox,lunch_checkbox;
    private EditText service_provider_name_edittext,contact_number_edittext, upi_edittext,editTextTime,editTextTime2,editTextTime3,editTextTime4;
    private Button confirm_button,update_button;
    private ImageView service_provider_imageview;
    private boolean isNonVeg = false;
    private File file;
    private ToastHelper toast;
    private RetrofitViewModel viewModel;
    private SharedPreferencesHelper sharedPreferences;
    private PicassoRepository picasso;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_details);
        initializeComponent();
        clickListeners();

    }

    // Private functions

    private void initializeComponent()
    {
        dinner_checkbox = findViewById(R.id.dinner_checkBox);
        veg_checkbox = findViewById(R.id.veg_checkBox);
        non_veg_checkbox = findViewById(R.id.non_veg_checkbox);
        lunch_checkbox = findViewById(R.id.lunch_checkBox);
        service_provider_name_edittext = findViewById(R.id.service_provider_name_edittext);
        contact_number_edittext = findViewById(R.id.contact_number_edittext);
        service_provider_imageview = findViewById(R.id.service_provider_image_view);
        upi_edittext = findViewById(R.id.upi_edittext);
        editTextTime = findViewById(R.id.editTextTime);
        editTextTime2 = findViewById(R.id.editTextTime2);
        editTextTime3 = findViewById(R.id.editTextTime3);
        editTextTime4 = findViewById(R.id.editTextTime4);
        confirm_button = findViewById(R.id.confirm_button);
        update_button = findViewById(R.id.update_button);
        picasso = new PicassoRepository();
        toast = new ToastHelper(this);
        viewModel = new RetrofitViewModel(getApplication());
        sharedPreferences = new SharedPreferencesHelper(this);
        if(getIntent().getBooleanExtra("isUpdating" , false))
            updateFields();
    }

    private void updateFields() {
        viewModel.getPersonalDetails().observe(this , result->{
            if(result!=null){
                picasso.getLogoImage(result.getLogoimage() , service_provider_imageview);
                service_provider_name_edittext.setText(result.getProvidername());
                contact_number_edittext.setText(result.getContactno());
                upi_edittext.setText(result.getUpiid());
                if(result.isVegnonveg())
                    non_veg_checkbox.setChecked(true);
                veg_checkbox.setChecked(true);
                if(result.isLunch())
                    lunch_checkbox.setChecked(true);
                if(result.isDinner())
                    dinner_checkbox.setChecked(true);
                editTextTime.setText(result.getLunchtimefrom());
                editTextTime2.setText(result.getLunchtimeto());
                editTextTime3.setText(result.getDinnertimefrom());
                editTextTime4.setText(result.getDinnertimeto());
            }
        });
    }

    private void clickListeners()
    {
        confirm_button.setOnClickListener(v -> updatePersonalDetails());
        update_button.setOnClickListener(v -> {

        });
        service_provider_imageview.setOnClickListener(v-> getImage.launch("image/*"));
    }

    private void updatePersonalDetails() {
        String username = sharedPreferences.getSharedPreferences("username");
        String providerName = service_provider_name_edittext.getText().toString();
        String contactNo = contact_number_edittext.getText().toString();
        String upi = upi_edittext.getText().toString();
        String logoImage = username + "logo.png";
        if((veg_checkbox.isChecked() && non_veg_checkbox.isChecked()) || non_veg_checkbox.isChecked())
            isNonVeg = true;
        boolean lunch = lunch_checkbox.isChecked();
        boolean dinner = dinner_checkbox.isChecked();
        String lunchTimeTo;
        String lunchTimeFrom;
        if(lunch){
            lunchTimeFrom = editTextTime.getText().toString();
            lunchTimeTo = editTextTime2.getText().toString();
        }
        else{
            lunchTimeFrom = " ";
            lunchTimeTo = " ";
        }
        String dinnerTimeFrom;
        String dinnerTimeTo;
        if(dinner){
            dinnerTimeFrom = editTextTime3.getText().toString();
            dinnerTimeTo = editTextTime4.getText().toString();
        }
        else{
            dinnerTimeFrom = " ";
            dinnerTimeTo = " ";
        }

        if(providerName.equals("") || contactNo.equals("")|| upi.equals("")){
            toast.makeToast("Check entered data" , Toast.LENGTH_LONG);
        }
        else{
            if(((lunch) && (lunchTimeFrom.equals("") || lunchTimeTo.equals(""))) || ((dinner) && (dinnerTimeFrom.equals("") || dinnerTimeTo.equals("")))){
               toast.makeToast("Check entered data" , Toast.LENGTH_LONG);
            }
            else{
                String model = "{\n" +
                        "    \"providername\" :\""+ providerName +"\",\n" +
                        "    \"contactno\" :\"" + contactNo +"\",\n" +
                        "    \"upiid\" :\""+ upi +"\",\n" +
                        "    \"vegnonveg\" :"+ isNonVeg +",\n" +
                        "    \"lunch\" :" + lunch +",\n" +
                        "    \"dinner\" :"+ dinner +",\n" +
                        "    \"lunchtimefrom\" :\""+ lunchTimeFrom +"\",\n" +
                        "    \"lunchtimeto\" :\""+ lunchTimeTo +"\",\n" +
                        "    \"dinnertimefrom\" :\""+ dinnerTimeFrom +"\",\n" +
                        "    \"dinnertimeto\" : \""+ dinnerTimeTo +"\",\n" +
                        "    \"logoimage\" :\""+ logoImage +"\"\n" +
                        "}";
                MultipartBody.Part body = null;
                if(file!=null) {
                    RequestBody imageRequestBody = RequestBody.create(MediaType.parse("image/*"), file);
                    body = MultipartBody.Part.createFormData("logoImage", " ", imageRequestBody);
                    Log.i("data" , "yes");
                }
                RequestBody jsonData = RequestBody.create(MediaType.parse("multipart/form-data"), model);
                Log.i("data" , model);
                MultipartBody.Part data = MultipartBody.Part.create(jsonData);
                Log.i("data" , viewModel+"");
                viewModel.updatePersonalDetails(data , body).observe(this , result->{
                    if(result){
                        toast.makeToast("Success" , Toast.LENGTH_LONG);
                        Intent intent = new Intent(this , HomePageActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish();
                    }
                    else{
                        toast.makeToast("Something went wrong" , Toast.LENGTH_LONG);
                    }
                });
                Log.i("data" , "end");
            }
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
                String mediaPath = cursor.getString(columnIndex);
                file = new File(mediaPath);
                service_provider_imageview.setImageURI(result);
                cursor.close();
            }
        }
    });
}
