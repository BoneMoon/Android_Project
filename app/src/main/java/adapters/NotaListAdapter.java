package adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cm_project.Nota;
import com.example.cm_project.NotaViewModel;
import com.example.cm_project.R;

import java.util.List;

public class NotaListAdapter extends RecyclerView.Adapter<NotaListAdapter.NotaViewHolder> {
    private final LayoutInflater mInflater;
    private List<Nota> mNotas;

    public NotaListAdapter(Context context){
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public NotaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.row_list_item, parent, false);
        return new NotaViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(NotaViewHolder holder, int position) {
        if (mNotas != null) {
            Nota current = mNotas.get(position);
            holder.rowTitulo.setText(current.getTitulo());
            holder.rowDescricao.setText(current.getDescricao());
            holder.rowTipo.setText(current.getTipoDescricao());
        } else {
            // Covers the case of data not being ready yet.
            holder.rowTitulo.setText("Não há notas");
        }
    }

    public void setNotas(List<Nota> notas){
        mNotas = notas;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mNotas != null)
            return mNotas.size();
        else return 0;
    }

    class NotaViewHolder extends RecyclerView.ViewHolder {
        private final TextView rowTitulo;
        private final TextView rowDescricao;
        private final TextView rowTipo;


        private NotaViewHolder(View itemView) {
            super(itemView);
            rowTitulo = itemView.findViewById(R.id.titulo);
            rowDescricao = itemView.findViewById(R.id.descricao);
            rowTipo = itemView.findViewById(R.id.tipoDescricao);
        }
    }

    public Nota getNotaPosition(int position){
        return mNotas.get(position);
    }
}
