package br.com.jrmantovani.soccernews.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.room.Room;


import br.com.jrmantovani.soccernews.MainActivity;
import br.com.jrmantovani.soccernews.data.local.AppDatabase;
import br.com.jrmantovani.soccernews.databinding.FragmentNewsBinding;
import br.com.jrmantovani.soccernews.ui.adapter.NewsAdapter;

public class NewsFragment extends Fragment {

    private FragmentNewsBinding binding;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        NewsViewModel newsViewModel =
                new ViewModelProvider(this).get(NewsViewModel.class);

        binding = FragmentNewsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        binding.rvNews.setLayoutManager(new LinearLayoutManager(getContext()));
        newsViewModel.getNews().observe(getViewLifecycleOwner(), news -> {
            binding.rvNews.setAdapter(new NewsAdapter(news, favoritedNews -> {
                MainActivity activity = (MainActivity) getActivity();
                if (activity != null)
                    activity.getDb().newsDao().save(favoritedNews);
            }));
        });
        newsViewModel.getState().observe(getViewLifecycleOwner(), state -> {
            switch (state) {
                case DOING:
                    //TODO incluir swipeRefreshLayout(loading)
                    break;
                case DONE:
                    //TODO Finalizar SwipeRefreshLayout (loading)
                    break;
                case ERROR:
                    //TODO: Finalizar SwipeRefreshLayout (loading)
                    //TODO: Mostra um erro generico

            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}