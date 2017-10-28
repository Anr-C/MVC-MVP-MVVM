package com.cniao5.mvvm.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.cniao5.mvvm.R;
import com.cniao5.mvvm.databinding.ItemRepoBinding;
import com.cniao5.mvvm.model.Repository;
import com.cniao5.mvvm.viewmodel.RepositoryItemViewModel;

import java.util.List;


/**
 * Created by xzhang
 */

public class RepositoryAdapter extends RecyclerView.Adapter<RepositoryAdapter.RepositoryViewHolder>{

    private Context mContext ;
    private List<Repository> repositoryList ;

    public RepositoryAdapter(Context context, List<Repository> repositoryList){
        this.mContext = context ;
        this.repositoryList = repositoryList ;
    }


    @Override
    public RepositoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemRepoBinding binding = DataBindingUtil.inflate(LayoutInflater.from(mContext),R.layout.item_repo,parent,false);

        return new RepositoryViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(RepositoryViewHolder holder, int position) {
        Repository repository = repositoryList.get(position);
        holder.bindData(repository);

    }

    @Override
    public int getItemCount() {
        return repositoryList.size();
    }

    public class RepositoryViewHolder extends RecyclerView.ViewHolder{

        ItemRepoBinding binding ;

        public RepositoryViewHolder(ItemRepoBinding binding) {
            super(binding.cardView);

            this.binding = binding ;

        }
        public void bindData(Repository repository){
            if(binding.getViewModel() == null){
                binding.setViewModel(new RepositoryItemViewModel(mContext,repository));
            }else{
                binding.getViewModel().setRepository(repository);
            }
        }


    }
}
