package View.HomeFragment.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myproject.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;

import AllListForder.Object.AvatarURL;
import AllListForder.Object.ItemSell;

public class AdapterRCVItemYourMayLike extends RecyclerView.Adapter<AdapterRCVItemYourMayLike.ViewHolder> {
    private List<ItemSell> itemSellList;

    public void setData(List<ItemSell> itemSellList){
        this.itemSellList = itemSellList;
        notifyDataSetChanged();
    }
    private OnItemRCVClickListener onItemRCVClickListener;

    public void setItemClickListener(OnItemRCVClickListener onItemRCVClickListener) {
        this.onItemRCVClickListener = onItemRCVClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_you_may_like, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final ItemSell itemSell = itemSellList.get(position);
        final DecimalFormatSymbols syms = new DecimalFormatSymbols();
        syms.setGroupingSeparator(',');
        DecimalFormat myFormatter = new DecimalFormat("###,###,###,###,###,###", syms);

        List<AvatarURL> UrlImgList = itemSell.getAvatarItemSell();
        Picasso.get().load(UrlImgList.get(0).getUrlImg())
                .placeholder(R.drawable.dont_loading_img)
                .error(R.drawable.dont_loading_img)
                .into(holder.imageView);
        holder.priceItem.setText(myFormatter.format(itemSell.getPriceSale()) + " đ");
        if (itemSell.getSale().equals("yes")) {
            holder.salePercent.setVisibility(View.VISIBLE);
            holder.salePercent.setText("-" + itemSell.getSalePercent() + "%");
        } else if (itemSell.getSale().equals("no")) {
            holder.salePercent.setVisibility(View.GONE);
        }
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemRCVClickListener.onItemClick(itemSell);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (itemSellList.isEmpty() || itemSellList == null) {
            return 0;
        } else
            return itemSellList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView salePercent, priceItem;
        LinearLayout linearLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            linearLayout =itemView.findViewById(R.id.layout_item_your_may_like);
            imageView = itemView.findViewById(R.id.img_item_may_like);
            salePercent = itemView.findViewById(R.id.tv_sale_percen_in_may_like);
            priceItem = itemView.findViewById(R.id.tv_price_item_may_like);
        }
    }
}
