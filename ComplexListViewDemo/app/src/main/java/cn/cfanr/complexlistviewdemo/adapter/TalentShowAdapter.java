package cn.cfanr.complexlistviewdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import cn.cfanr.complexlistviewdemo.R;
import cn.cfanr.complexlistviewdemo.model.TalentShow;
import cn.cfanr.complexlistviewdemo.view.CircleImageView;

public class TalentShowAdapter extends RecyclerView.Adapter<TalentShowAdapter.TalentShowViewHolder> {
    private List<TalentShow> talentShowList;
    private Context context;

    public TalentShowAdapter(Context context, List<TalentShow> talentShowList) {
        this.context = context;
        this.talentShowList=talentShowList;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    @Override
    public TalentShowViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TalentShowViewHolder holder = new TalentShowViewHolder(LayoutInflater.from(context).inflate(R.layout.view_home_talent_item, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(final TalentShowViewHolder holder, final int position) {
        TalentShow talentShow=talentShowList.get(position);

        holder.tvUserName.setText(talentShow.getNickName());
        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickListener.onItemClick(holder.itemView, pos);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return talentShowList.size();
    }

    class TalentShowViewHolder extends RecyclerView.ViewHolder {
        CircleImageView circleAvatar;
        TextView tvUserName;
        public TalentShowViewHolder(View view) {
            super(view);
            circleAvatar=(CircleImageView)view.findViewById(R.id.circle_iv_talent_avatar);
            tvUserName=(TextView)view.findViewById(R.id.tv_talent_username);
        }
    }
}
