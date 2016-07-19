package br.com.alexcosta.labimagem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;

import br.com.alexcosta.labimagem.dominio.entidades.Evento;

/**
 * Created by acostas on 13/07/16.
 */
public class EventoArrayAdapter extends ArrayAdapter<Evento> {
    private int resource = 0;
    private LayoutInflater inflater;
    private Context context;

    public EventoArrayAdapter(Context context, int resource) {
        super(context, resource);
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.resource = resource;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            view = inflater.inflate(resource, parent, false);
            viewHolder.txtCor = (TextView) view.findViewById(R.id.txtCor);
            viewHolder.txtDia = (TextView) view.findViewById(R.id.txtDia);
            viewHolder.txtMes = (TextView) view.findViewById(R.id.txtMes);
            viewHolder.txtAno = (TextView) view.findViewById(R.id.txtAno);
            viewHolder.txtLaudo = (TextView) view.findViewById(R.id.txtLaudo);
            viewHolder.txtDescricao = (TextView) view.findViewById(R.id.txtDescricao);
            view.setTag(viewHolder);
            convertView = view;
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            view = convertView;
        }

        Evento evento = getItem(position);

        //Dia do item cadastrado
        String diac = new SimpleDateFormat("dd").format(evento.getData());
        String mesc = new SimpleDateFormat("MM").format(evento.getData());
        String anoc = new SimpleDateFormat("yyyy").format(evento.getData());

        if (mesc.equals("01")) {
            mesc = "Jan";
        }
        if (mesc.equals("02")) {
            mesc = "Fev";
        }
        if (mesc.equals("03")) {
            mesc = "Mar";
        }
        if (mesc.equals("04")) {
            mesc = "Abr";
        }
        if (mesc.equals("05")) {
            mesc = "Mai";
        }
        if (mesc.equals("06")) {
            mesc = "Jun";
        }
        if (mesc.equals("07")) {
            mesc = "Jul";
        }
        if (mesc.equals("08")) {
            mesc = "Ago";
        }
        if (mesc.equals("09")) {
            mesc = "Set";
        }
        if (mesc.equals("10")) {
            mesc = "Out";
        }
        if (mesc.equals("11")) {
            mesc = "Nov";
        }
        if (mesc.equals("12")) {
            mesc = "Dez";
        }

        viewHolder.txtCor.setBackgroundColor(context.getResources().getColor(R.color.colorAccent));
        viewHolder.txtDia.setText(diac);
        viewHolder.txtMes.setText(mesc);
        viewHolder.txtAno.setText(anoc);
        viewHolder.txtLaudo.setText(evento.getLaudo());
        viewHolder.txtDescricao.setText(evento.getDescricao());

        return view;
    }

    static class ViewHolder {
        TextView txtCor;
        TextView txtDia;
        TextView txtMes;
        TextView txtAno;
        TextView txtLaudo;
        TextView txtDescricao;

    }
}
