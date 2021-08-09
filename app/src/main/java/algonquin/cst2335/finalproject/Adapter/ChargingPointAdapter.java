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

import java.util.ArrayList;
import java.util.List;

import algonquin.cst2335.finalproject.Common.AdapterInterface;
import algonquin.cst2335.finalproject.Model.AddressInfo;
import algonquin.cst2335.finalproject.R;

public class ChargingPointAdapter extends RecyclerView.Adapter<ChargingPointAdapter.ViewHoldervh> {

    /**declare variable
     *
     */
    List<AddressInfo> getAddressList;
    private Context context;
    AdapterInterface adapterInterface;
    List<AddressInfo> searchList;

    /**creating constructor for adding data into list
     *
     * @param getAddressList
     * @param context
     * @param adapterInterface
     */
    public ChargingPointAdapter(List<AddressInfo> getAddressList, Context context, AdapterInterface adapterInterface) {
        this.getAddressList = getAddressList;
        this.context = context;
        this.adapterInterface = adapterInterface;
        this.searchList = getAddressList;
    }

    /**creating fillter class for adapter which will perform searching
     *
     * @param filterllist
     */
    public void filterList(ArrayList<AddressInfo> filterllist) {
        /** below line is to add our filtered
         *
         */
        /** list in our course array list.
         *
         */
        getAddressList = filterllist;
        // below line is to notify our adapter
        /** as change in recycler view data.
         *
         */
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHoldervh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        /**inflating the view for our adapter class
         *
         */
        return new ViewHoldervh(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false));


    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHoldervh holder, int position) {
        AddressInfo addressInfo = getAddressList.get(position);

        /**setting the data into adapter view
         *
         */
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


        holder.Delete.setVisibility(View.GONE);

    }


    @Override
    public int getItemCount() {
        //returning the size of list
        return getAddressList.size();
    }


    public class ViewHoldervh extends RecyclerView.ViewHolder {

        /**here we are declaring the varibale of adapterlayout
         *
         */
        TextView tvTitle, tvPostCode, tvTown, AddressTv, Delete;
        ConstraintLayout constraintlayout;

        public ViewHoldervh(@NonNull View itemView) {
            super(itemView);
            /**initialize the view items
             *
             */
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvPostCode = itemView.findViewById(R.id.tvPostCode);
            tvTown = itemView.findViewById(R.id.tvTown);
            AddressTv = itemView.findViewById(R.id.AddressTv);
            Delete = itemView.findViewById(R.id.delete);
            constraintlayout = itemView.findViewById(R.id.constraintlayout);

        }
    }
}
