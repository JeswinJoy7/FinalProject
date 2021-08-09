package algonquin.cst2335.finalproject.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.algonquin.cst2335.finalproject.R;

import java.util.ArrayList;
import java.util.List;

import algonquin.cst2335.finalproject.Common.AdapterInterface;
import algonquin.cst2335.finalproject.Model.AddressInfo;

public class ChargingPointAdapter extends RecyclerView.Adapter<ChargingPointAdapter.ViewHoldervh> {

    List<AddressInfo> getAddressList;
    private Context context;
    AdapterInterface adapterInterface;
    List<AddressInfo> searchList;

    public ChargingPointAdapter(List<AddressInfo> getAddressList, Context context, AdapterInterface adapterInterface) {
        this.getAddressList = getAddressList;
        this.context = context;
        this.adapterInterface = adapterInterface;
        this.searchList = getAddressList;
    }

    public void filterList(ArrayList<AddressInfo> filterllist) {
        // below line is to add our filtered
        // list in our course array list.
        getAddressList = filterllist;
        // below line is to notify our adapter
        // as change in recycler view data.
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHoldervh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHoldervh(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false));


    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHoldervh holder, int position) {
        AddressInfo addressInfo = getAddressList.get(position);

        Log.d("TAG", "onBindViewHolder: " + addressInfo);
        holder.tvTitle.setText("Title :" + addressInfo.getTitle());
        holder.tvTown.setText("Town :" + addressInfo.getTown());
        holder.AddressTv.setText("Address :" + addressInfo.getAddressLine2());
        holder.tvPostCode.setText("Postal Code : " + addressInfo.getPostcode());


        holder.constraintlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapterInterface.onItemClicked(addressInfo);
            }
        });


    }


    @Override
    public int getItemCount() {
        return getAddressList.size();
    }

    @NonNull
    public Filter getFilter() {
        return new Filter() {
            @NonNull
            @Override
            protected FilterResults performFiltering(@NonNull CharSequence charSequence) {
                String character = charSequence.toString();
                if (character.isEmpty()) {
                    getAddressList = searchList;
                } else {
                    List<AddressInfo> filteredList = new ArrayList<>();
                    for (AddressInfo item : searchList) {
                        if (item.getAddressLine1().toLowerCase().contains(character.toLowerCase())) {
                            filteredList.add(item);
                        }
                    }
                    getAddressList = filteredList;
                }
                FilterResults results = new FilterResults();
                results.values = getAddressList;
                return results;

            }

            @Override
            protected void publishResults(CharSequence charSequence, @NonNull FilterResults filterResults) {
                getAddressList = (ArrayList<AddressInfo>) filterResults.values;
                notifyDataSetChanged();
            }
        };

    }

    public class ViewHoldervh extends RecyclerView.ViewHolder {

        TextView tvTitle, tvPostCode, tvTown, AddressTv;
        ConstraintLayout constraintlayout;

        public ViewHoldervh(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvPostCode = itemView.findViewById(R.id.tvPostCode);
            tvTown = itemView.findViewById(R.id.tvTown);
            AddressTv = itemView.findViewById(R.id.AddressTv);
            constraintlayout = itemView.findViewById(R.id.constraintlayout);

        }
    }
}
