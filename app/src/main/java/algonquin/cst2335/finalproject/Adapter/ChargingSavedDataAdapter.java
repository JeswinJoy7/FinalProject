package algonquin.cst2335.finalproject.Adapter;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import algonquin.cst2335.finalproject.Common.AdapterInterface;
import algonquin.cst2335.finalproject.Model.AddressInfo;
import algonquin.cst2335.finalproject.R;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ChargingSavedDataAdapter extends RecyclerView.Adapter<ChargingSavedDataAdapter.VH> {
    List<AddressInfo> getAddressList;
    AdapterInterface adapterInterface;

    public ChargingSavedDataAdapter(List<AddressInfo> getAddressList, AdapterInterface adapterInterface) {
        this.getAddressList = getAddressList;
        this.adapterInterface = adapterInterface;

    }

    @NonNull
    @Override
    public ChargingSavedDataAdapter.VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ChargingSavedDataAdapter.VH(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull ChargingSavedDataAdapter.VH holder, @SuppressLint("RecyclerView") int position) {

        AddressInfo addressInfo = getAddressList.get(position);

        Log.d("TAG", "onBindViewHolder: " + addressInfo);
        holder.tvTitle.setText("Title :" + addressInfo.getTitle());
        holder.tvTown.setText("Town :" + addressInfo.getTown());
        holder.AddressTv.setText("Address :" + addressInfo.getAddressLine2());
        holder.tvPostCode.setText("Postal Code : " + addressInfo.getPostcode());


        holder.Delete.setOnClickListener(v -> {
            adapterInterface.onItemClicked(addressInfo);
            getAddressList.remove(position);
            notifyItemRemoved(position);
        });

    }

    @Override
    public int getItemCount() {
        return getAddressList.size();
    }

    public class VH extends RecyclerView.ViewHolder {
        TextView tvTitle, tvPostCode, tvTown, AddressTv, Delete;

        public VH(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvPostCode = itemView.findViewById(R.id.tvPostCode);
            tvTown = itemView.findViewById(R.id.tvTown);
            AddressTv = itemView.findViewById(R.id.AddressTv);
            Delete = itemView.findViewById(R.id.delete);
        }
    }
}
