package com.cniao5.mvvm.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.cniao5.mvvm.R;
import com.cniao5.mvvm.databinding.RepoListItemBinding;
import com.cniao5.mvvm.model.Repo;
import com.cniao5.mvvm.viewmodel.ItemRepoViewModel;

import java.util.List;

/**
 * Created by xzhang .
 */

public class RepoListAdapter extends RecyclerView.Adapter<RepoListAdapter.ViewHolder> {

    private Context mContext ;

    private List<Repo> repoList ;

    public RepoListAdapter(Context context,List<Repo> repoList) {
        this.mContext = context ;
        this.repoList = repoList ;
    }

    public void setRepoList(List<Repo> repoList){
        this.repoList = repoList ;
        notifyDataSetChanged();
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RepoListItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(mContext),R.layout.repo_list_item,parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Repo repo = repoList.get(position);
        holder.bindData(repo);

    }

    @Override
    public int getItemCount() {
        return repoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        RepoListItemBinding binding ;

        public ViewHolder(RepoListItemBinding binding) {
            super(binding.repoCard);
            this.binding = binding ;

        }
        public void bindData(Repo repo){
            if(binding.getViewModel() == null){
                binding.setViewModel(new ItemRepoViewModel(mContext,repo));
            }else{
                binding.getViewModel().setRepo(repo);
            }
        }

        private void openUserRepoActivity(String name){
            /*Intent intent = new Intent(mContext, UserRepoActivity.class) ;
            intent.putExtra("username",name);
            mContext.startActivity(intent);*/
        }
    }
}
