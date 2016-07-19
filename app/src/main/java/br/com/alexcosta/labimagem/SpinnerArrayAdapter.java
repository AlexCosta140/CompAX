package br.com.alexcosta.labimagem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import br.com.alexcosta.labimagem.dominio.entidades.Evento;

/**
 * Created by acostas on 15/07/16.
 */
public class SpinnerArrayAdapter extends ArrayAdapter<Evento> {
    private int resource = 0;
    private LayoutInflater inflater;
    private Context context;

    public SpinnerArrayAdapter(Context context, int resource) {
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
            viewHolder.txtPaciente = (TextView) view.findViewById(R.id.txtPaciente);
            view.setTag(viewHolder);
            convertView = view;
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            view = convertView;
        }

        Evento evento = getItem(position);



        viewHolder.txtPaciente.setText(evento.getPaciente());

        return view;
    }

    static class ViewHolder {
        TextView txtPaciente;

    }
}
