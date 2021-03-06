package adapter;

/**
 * Created by matheus on 03/11/16.
 */

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.matheus.mesada.R;

import java.io.File;
import java.io.IOException;
import java.util.List;

import model.Filho;

/**
 * Created by matheus on 21/10/16.
 */
public class FilhoAdapter extends BaseAdapter {
    private Context mContext;
    private List<Filho> mListaFilho;

    public FilhoAdapter(Context context, List<Filho> filhoList) {
        this.mContext = context;
        this.mListaFilho = filhoList;
    }


    @Override
    public int getCount() {
        return mListaFilho.size();
    }

    @Override
    public Object getItem(int position) {
        return mListaFilho.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        Filho filho = mListaFilho.get(position);
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.adapter_filho, null);


        }
        //preencher
        ImageView imageView = (ImageView) view.findViewById(R.id.imagem_adapter_filho);
        TextView txtNome = (TextView) view.findViewById(R.id.nome_adapter_filho);
        txtNome.setText(filho.getNome());

        TextView txtSaldo = (TextView) view.findViewById(R.id.saldo_adapter_filho);
        txtSaldo.setText("R$ " + filho.getSaldo());
        File ifile = new File(Environment.getExternalStorageDirectory() + "/imgsMesada/", filho.getNome() + ".JPEG");
        //parse Bitmap
        Uri imageUri = Uri.fromFile(ifile);

        try {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(mContext.getContentResolver(), imageUri);
            //Circle
            Bitmap circleBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
            BitmapShader shader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
            Paint paint = new Paint();
            paint.setShader(shader);
            Canvas canvas = new Canvas(circleBitmap);
            canvas.drawCircle(bitmap.getWidth() / 2, bitmap.getHeight() / 2, bitmap.getWidth() / 2, paint);
            imageView.setImageBitmap(circleBitmap);

        } catch (IOException e) {
            e.printStackTrace();
        }

        // imageView.setImageURI(Uri.fromFile(ifile));
        //Picasso.with(imageView.getContext()).load(ifile).into(imageView);


        return view;
    }


}
