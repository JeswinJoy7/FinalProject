package algonquin.cst2335.finalproject;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.algonquin.cst2335.finalproject.R;
import com.bumptech.glide.Glide;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class sv_SavedMoviesAdapter extends RecyclerView.Adapter<sv_SavedMoviesAdapter.ViewHolderVh> {

    Context context;
    List<sv_ModelMovieInformation> getAllSavedMovies;
    sv_AdapterInterface adapterInterface;

    public sv_SavedMoviesAdapter(Context context, List<sv_ModelMovieInformation> getAllSavedMovies, sv_AdapterInterface adapterInterface) {
        this.context = context;
        this.getAllSavedMovies = getAllSavedMovies;
        this.adapterInterface = adapterInterface;
    }


    @NonNull
    @Override
    public ViewHolderVh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new sv_SavedMoviesAdapter.ViewHolderVh(LayoutInflater.from(parent.getContext()).inflate(R.layout.sv_save_movie_view, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull sv_SavedMoviesAdapter.ViewHolderVh holder, int position) {
        final sv_ModelMovieInformation modelMovieInformation = getAllSavedMovies.get(position);

        Log.d("TAG", "onBindViewHolder: " + modelMovieInformation);

        holder.tvTitle.setText(modelMovieInformation.getTitle());
        holder.tvPlot.setText(modelMovieInformation.getPlot());
        Glide.with(context).load(String.valueOf(modelMovieInformation.getPoster())).error(R.drawable.ic_launcher_background).into(holder.movieposter);


        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapterInterface.onItemClicked(modelMovieInformation);
                getAllSavedMovies.remove(position);
                notifyItemRemoved(position);
            }
        });

        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapterInterface.onMvieInformation(modelMovieInformation);
            }
        });
    }

    @Override
    public int getItemCount() {
        return getAllSavedMovies.size();
    }

    public class ViewHolderVh extends RecyclerView.ViewHolder {
        TextView tvTitle, tvPlot;
        ImageView movieposter, delete;
        ConstraintLayout constraintLayout;

        public ViewHolderVh(@NonNull View itemView) {
            super(itemView);
            movieposter = itemView.findViewById(R.id.imageView);
            tvTitle = itemView.findViewById(R.id.textView);
            tvPlot = itemView.findViewById(R.id.textView2);
            delete = itemView.findViewById(R.id.imageView2);
            constraintLayout = itemView.findViewById(R.id.cl);
        }
    }
}
