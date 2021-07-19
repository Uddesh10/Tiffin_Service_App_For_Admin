package com.uddesh.tiffinserviceappforadmin.Repository;

import android.widget.ImageView;
import com.github.siyamed.shapeimageview.mask.PorterShapeImageView;
import com.squareup.picasso.Picasso;

public class PicassoRepository {

    public void getLogoImage(String fileName , ImageView imageView)
    {
        Picasso.get().load("http://192.168.42.97:8080/getlogo?filename="+fileName).into(imageView);
    }

    public void getFoodImage(String fileName , PorterShapeImageView imageView)
    {
        Picasso.get().load("http://192.168.42.97:8080/getfoodimage?filename="+fileName).into(imageView);

    }
}
