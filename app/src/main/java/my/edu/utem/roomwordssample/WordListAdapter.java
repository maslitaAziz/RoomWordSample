package my.edu.utem.roomwordssample;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordViewHolder> {

    private final LayoutInflater minflater;
    private List<Word> mWords;

    WordListAdapter(Context context) {
        minflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = minflater.inflate(R.layout.recyclerview_item, parent, false);
        return new WordViewHolder(itemView);
    }

    //yg ni ajek yg beza, yg method lain kebayakan sama
    @Override
    public void onBindViewHolder(@NonNull WordViewHolder holder, int position) {
        if (mWords != null) {
            holder.wordTextView.setText(mWords.get(position).getWord());
        }
        else {
            holder.wordTextView.setText("No Word");
        }

    }

    @Override
    public int getItemCount() {
        if (mWords != null)
            return mWords.size();
        else
            return 0;
    }

    //ni sama dgn e wan addweather
    void setWords(List<Word> words) {
        mWords = words;
        notifyDataSetChanged();
    }

    class WordViewHolder extends RecyclerView.ViewHolder {
        TextView wordTextView;
        public WordViewHolder(View itemView) {
            super(itemView);
            wordTextView = itemView.findViewById(R.id.textView);
        }
    }
}
