package com.transitionbug;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class TabFragment extends Fragment {
    private RecyclerView mListView;
    private ItemAdapter mAdapter;
    private ArrayList<Item> mItems;

    public static TabFragment newInstance(String name) {
        TabFragment fragment = new TabFragment();
        return fragment;
    }

    public TabFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mItems = new ArrayList<>();
        mAdapter = new ItemAdapter(getContext(), mItems);
    }

    private void getData() {
        mItems.add(new Item("Name 1", R.mipmap.almond_blossoms));
        mItems.add(new Item("Name 2", R.mipmap.cafe_terrace));
        mItems.add(new Item("Name 3", R.mipmap.starry_night));
        mItems.add(new Item("Name 4", R.mipmap.starry_night_over_the_rhone));
        mItems.add(new Item("Name 5", R.mipmap.sunflowers));
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab, container, false);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mListView = (RecyclerView) view.findViewById(R.id.list);
        mListView.setHasFixedSize(true);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mListView.setLayoutManager(layoutManager);
        mListView.setAdapter(mAdapter);
        getData();

        return view;
    }

    private static class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemHolder> {
        private Context mContext;
        private ArrayList<Item> mItems;
        private final String TAG = "ItemAdapter";

        public ItemAdapter(Context mContext, ArrayList<Item> mItems) {
            this.mContext = mContext;
            this.mItems = mItems;
        }

        @Override
        public ItemAdapter.ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_layout, parent, false);
            ItemHolder itemHolder = new ItemHolder(v, new ItemHolder.IMyViewHolderClick() {
                @Override
                public void OnClick(View caller, int position) {
                    Item selectedItem = mItems.get(position);
                    Intent detailIntent = new Intent(mContext, DetailActivity.class);
                    detailIntent .putExtra("item", selectedItem);

                    View v1 = caller.findViewById(R.id.item_name);
                    View v2 = caller.findViewById(R.id.item_bg);

                    Pair<View, String> p1 = Pair.create(v1, v1.getTransitionName());
                    Pair<View, String> p2 = Pair.create(v2, v2.getTransitionName());

                    ActivityOptionsCompat options = ActivityOptionsCompat.
                            makeSceneTransitionAnimation((Activity) mContext, p1, p2);
                    mContext.startActivity(detailIntent , options.toBundle());
                }
            });

            return itemHolder;
        }

        @Override
        public void onBindViewHolder(ItemAdapter.ItemHolder holder, int position) {
            Item item = mItems.get(position);
            holder.itemBackground.setBackground(mContext.getDrawable(item.getImageId()));
            holder.itemName.setText(item.getName());
        }

        @Override
        public int getItemCount() {
            return mItems.size();
        }

        static class ItemHolder extends RecyclerView.ViewHolder  implements View.OnClickListener {
            public IMyViewHolderClick mListener;
            public ImageView itemBackground;
            public TextView itemName;
            public FrameLayout holder;

            ItemHolder(View view, IMyViewHolderClick listener) {
                super(view);
                this.mListener = listener;
                this.itemBackground = (ImageView) view.findViewById(R.id.item_bg);
                this.itemName = (TextView) view.findViewById(R.id.item_name);
                this.holder = (FrameLayout) view.findViewById(R.id.item_holder);
                this.holder.setOnClickListener(this);
            }

            @Override
            public void onClick(View v) {
                mListener.OnClick(v, this.getAdapterPosition());
            }

            public interface IMyViewHolderClick {
                void OnClick(View caller, int position);
            }
        }
    }
}