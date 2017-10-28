package com.cniao5.mvvm.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.databinding.BindingAdapter;
import android.databinding.ObservableInt;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.cniao5.mvvm.R;
import com.cniao5.mvvm.model.Repo;
import com.cniao5.mvvm.util.FavoReposHelper;
import com.cniao5.mvvm.view.UserRepoActivity;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * <p>Description:
 *
 * @author xzhang
 */

public class ItemRepoViewModel {

    private static final String TAG = "ItemRepoViewModel" ;

    public ObservableInt favStarImage = new ObservableInt();

    private Repo repo ;
    private static Context context ;

    public ItemRepoViewModel(Context context ,Repo repo){
        this.repo = repo ;
        this.context = context ;

        if(FavoReposHelper.getInstance().contains(repo)){
            favStarImage.set(R.drawable.ic_star_checked);
        }else{
            favStarImage.set(R.drawable.ic_star_unchecked);
        }

    }

    public void onItemClick(View view){
        Log.i(TAG,repo.getOwner()) ;
        openUserRepoActivity(repo.getOwner());
    }
    public void onImageClick1(View view){
        Log.i(TAG,repo.getContributors().get(0).getName()) ;
        openUserRepoActivity(repo.getContributors().get(0).getName());
    }
    public void onImageClick2(View view){
        Log.i(TAG,repo.getContributors().get(1).getName()) ;
        openUserRepoActivity(repo.getContributors().get(1).getName());
    }
    public void onImageClick3(View view){
        Log.i(TAG,repo.getContributors().get(2).getName()) ;
        openUserRepoActivity(repo.getContributors().get(2).getName());
    }
    public void onImageClick4(View view){
        Log.i(TAG,repo.getContributors().get(3).getName()) ;
        openUserRepoActivity(repo.getContributors().get(3).getName());
    }
    public void onImageClick5(View view){
        Log.i(TAG,repo.getContributors().get(4).getName()) ;
        openUserRepoActivity(repo.getContributors().get(4).getName());
    }

    public void onFavClick(View view){
        if(FavoReposHelper.getInstance().contains(repo)){
            favStarImage.set(R.drawable.ic_star_unchecked);
            FavoReposHelper.getInstance().removeFavo(repo);
        }else{
            favStarImage.set(R.drawable.ic_star_checked);
            FavoReposHelper.getInstance().addFavo(repo);
        }
    }


    @BindingAdapter({"avatarUrl"})
    public static void setAvatarImage(CircleImageView image,String url){
        if(!TextUtils.isEmpty(url)){
            Picasso.with(context).load(url).into(image);
        }
    }

    @BindingAdapter({"favStar"})
    public static void setFavImageRes(ImageView image,int id){
        image.setImageResource(id);
    }

    public int getFavImageId(){
        return favStarImage.get();
    }


    public String getTitle(){
        return repo.getOwner() + " / " + repo.getName() ;
    }
    public String getDes(){
        return repo.getDes();
    }
    public String getMeta(){
        return repo.getMeta() ;
    }

    public String getAvatar1(){
        if(repo.getContributors().size() > 0)
            return repo.getContributors().get(0).getAvatar();
        return null ;
    }
    public String getAvatar2(){
        if(repo.getContributors().size() > 1)
            return repo.getContributors().get(1).getAvatar();
        return null ;
    }
    public String getAvatar3(){
        if(repo.getContributors().size() > 2)
            return repo.getContributors().get(2).getAvatar();
        return null ;
    }
    public String getAvatar4(){
        if(repo.getContributors().size() > 3)
            return repo.getContributors().get(3).getAvatar();
        return null ;
    }
    public String getAvatar5(){
        if(repo.getContributors().size() > 4)
            return repo.getContributors().get(4).getAvatar();
        return null ;
    }

    public void setRepo(Repo repo){
        this.repo = repo ;
    }

    public void openUserRepoActivity(String name){
        Intent intent = new Intent(context,UserRepoActivity.class);
        intent.putExtra("username",name);
        context.startActivity(intent);
    }
}
