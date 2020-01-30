package com.msg91.sendotp.sample;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class Chequeadapter3 extends RecyclerView.Adapter<Chequeadapter3.ProductViewHolder> {

    private Context mCtx;
    private List<Cheque3> productList;

    public Chequeadapter3(Context mCtx, List<Cheque3> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.activity_eventadmin1, null);
        return new ProductViewHolder(view);

    }

    @Override
    public void onBindViewHolder(final ProductViewHolder holder, int position) {
      final   Cheque3 cheque;   cheque = productList.get(position);

        //loading the image
        holder.name.setText(cheque.getPrize1());
        holder.ph.setText(cheque.getPrize2());
        holder.blog.setText(cheque.getImage());
        holder.datem.setText(cheque.getUser());
       holder.date.setText(cheque.getPrize());
       holder.txtt.setText(cheque.getStatus());
        holder.place.setText(cheque.getDes());
      //  SharedPreferences sharedPreferences = mCtx.getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);

        //Creating editor to store values to shared preferences


    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {



        TextView text,txtt,datem,date,blog,place,name,ph;

        public ProductViewHolder(View itemView) {
            super(itemView);



            name=itemView.findViewById(R.id.namm);
            ph=itemView.findViewById(R.id.phh);
            txtt=itemView.findViewById(R.id.eventdate1);
            date=itemView.findViewById(R.id.eventdis1);
            datem=itemView.findViewById(R.id.eventname1);
            blog=itemView.findViewById(R.id.eventtime1);
            place=itemView.findViewById(R.id.eventplace1);

        }

    }



}