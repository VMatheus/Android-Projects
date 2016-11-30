package utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.Toast;

/**
 * Created by matheus on 03/11/16.
 */

public class Mensagem {


    public static void Msg(Activity activity, String msg) {
        Toast.makeText(activity, msg, Toast.LENGTH_LONG).show();
    }

    public static void addMsgOk(Activity activity, String titulo, String mensagem, int icone) {
        AlertDialog.Builder alert = new AlertDialog.Builder(activity);
        alert.setTitle(titulo);
        alert.setMessage(mensagem);
        alert.setIcon(icone);
        alert.setNeutralButton("OK", null);
        alert.show();
    }

    public static AlertDialog dialogOpcaoImagem(Activity activity) {
        final CharSequence[] items = {
                "Galeria",
                "Camera"
        };
        AlertDialog.Builder alert = new AlertDialog.Builder(activity);
        alert.setTitle("Selecionar uma Foto");
        alert.setItems(items, (DialogInterface.OnClickListener)activity);
        return alert.create();


    }
    public  static  AlertDialog criarAlertDialogRegras(Activity activity){

        final CharSequence[] items = {
               "Alterar",
                "Excluir"
        };
        AlertDialog.Builder builder =new AlertDialog.Builder(activity);
        builder.setTitle("Opções");
        builder.setItems(items,(DialogInterface.OnClickListener )activity);

        return builder.create();
    }


    public static AlertDialog criarAlertDialog(Activity activity) {
        final CharSequence[] items = {
                "Admiministrar",
                "Excluir",
                "+Bonus"

        };
        AlertDialog.Builder alert = new AlertDialog.Builder(activity);
        alert.setTitle("Opções");
        alert.setItems(items, (DialogInterface.OnClickListener) activity);
        return alert.create();
    }

    public static AlertDialog criarDialogConfirmacao(Activity activity) {
        AlertDialog.Builder alert = new AlertDialog.Builder(activity);
        alert.setMessage("Deseja realmente Excluir?");
        alert.setPositiveButton("SIM", (DialogInterface.OnClickListener) activity);
        alert.setNegativeButton("NÂO", (DialogInterface.OnClickListener) activity);
        return alert.create();
    }
}
