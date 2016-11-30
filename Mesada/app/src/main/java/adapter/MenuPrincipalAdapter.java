package adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.matheus.mesada.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by matheus on 10/11/16.
 */

public class MenuPrincipalAdapter extends RecyclerView.Adapter<MenuPrincipalAdapter.MenuViewHolder> {

    private Context mContext;
    private List<String> mItem;
    private List<Integer> mImage;

    public MenuPrincipalAdapter(Context context, String[] items, Integer[] image) {
        this.mContext = context;
        this.mImage = Arrays.asList(image);
        this.mItem = Arrays.asList(items);

    }

    @Override
    public MenuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_menu, null);
        MenuViewHolder viewHolder = new MenuViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MenuViewHolder holder, int position) {
        String item = mItem.get(position);
        holder.textView.setText(item);
    }

    @Override
    public int getItemCount() {
        return mItem.size();
    }

    public class MenuViewHolder extends RecyclerView.ViewHolder{

        TextView textView;

        public MenuViewHolder(View itemView) {
            super(itemView);

            textView = (TextView) itemView.findViewById(R.id.adapter_text_titulo);
        }
    }
}
