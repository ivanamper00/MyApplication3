package com.botlu.myapplication.controller.activity.fragment;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.botlu.myapplication.R;
import com.botlu.myapplication.adapter.GamesAdapter;
import com.botlu.myapplication.controller.GlobalController;
import com.botlu.myapplication.model.GamesModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GamesAllFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GamesAllFragment extends Fragment {
    GlobalController globalController;
    RecyclerView recyclerView;
    CardView cardView;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public GamesAllFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GamesAllFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GamesAllFragment newInstance(String param1, String param2) {
        GamesAllFragment fragment = new GamesAllFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_games_all, container, false);
        globalController = new GlobalController(getContext());
        recyclerView = view.findViewById(R.id.games_recycler);
        cardView = view.findViewById(R.id.card_no_result);

        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(llm);
        List<GamesModel> gamesModels = globalController.retrieveGames();

        if(gamesModels.size() != 0){
            cardView.setVisibility(View.INVISIBLE);
            GamesAdapter adapter = new GamesAdapter(getContext(),gamesModels);
            recyclerView.setAdapter(adapter);
        }else{
            cardView.setVisibility(View.VISIBLE);
        }
        return view;
    }
}