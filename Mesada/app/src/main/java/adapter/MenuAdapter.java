package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.matheus.mesada.R;

/**
 * Created by matheus on 03/11/16.
 */

public class MenuAdapter extends BaseAdapter {

    private Context mContext;
    private final String[] mItems;
    private final int[] mImage;

    public MenuAdapter(Context context, String[] items, int[] image) {
        this.mContext = context;
        this.mImage = image;
        this.mItems = items;

    }


    @Override
    public int getCount() {
        return mItems.length;
    }

    @Override
    public String getItem(int i) {
        return mItems[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }


    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        View v;
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {

            v = new View(mContext);
            v = inflater.inflate(R.layout.adapter_menu, null);
            TextView textView = (TextView) v.findViewById(R.id.adapter_text_titulo);
            ImageView imageView = (ImageView) v.findViewById(R.id.adapter_menu_imagem);

            textView.setText(mItems[i]);
            imageView.setImageResource(mImage[i]);

        } else {
            v = convertView;
        }

        return v;
    }


}
