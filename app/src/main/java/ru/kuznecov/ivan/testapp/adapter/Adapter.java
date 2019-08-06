package ru.kuznecov.ivan.testapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import ru.kuznecov.ivan.testapp.R;
import ru.kuznecov.ivan.testapp.pojo.PhotoData;
import ru.kuznecov.ivan.testapp.pojo.Urls;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private List<PhotoData> photoDataList;
    private Context context;
    private Listener listener;

    public Adapter(Context context) {
        photoDataList = new ArrayList<>();
        this.context = context;
    }

    public void setListener(Listener listener){
        this.listener = listener;
    }

    public void changeList(List<PhotoData> newElem){
        photoDataList.addAll(newElem);
        notifyItemInserted(photoDataList.size());
        /*notifyDataSetChanged();*/
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_recycler_view, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        viewHolder.bind(photoDataList.get(i));
        if (viewHolder.getAdapterPosition() == photoDataList.size() - 1 && listener != null)
            listener.loadMore();

    }

    @Override
    public int getItemCount() {
        return photoDataList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = (ImageView)itemView.findViewById(R.id.card_image);
        }

        public void bind(PhotoData photoData) {

            Urls urls = photoData.getUrls();
            Glide
                    .with(context)
                    .load(urls.getImage())
                    .into(imageView);
        }
    }

    public interface Listener {
        void loadMore();
    }
}
