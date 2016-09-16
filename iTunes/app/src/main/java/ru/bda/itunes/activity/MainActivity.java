package ru.bda.itunes.activity;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import ru.bda.itunes.R;
import ru.bda.itunes.adapter.MyRecyclerAdapter;
import ru.bda.itunes.controller.ApiController;
import ru.bda.itunes.model.SearchAgent;

public class MainActivity extends AppCompatActivity {

    private EditText mEtSearch;
    private RecyclerView mRecyclerView;
    private ProgressBar mProgressBar;
    private MyRecyclerAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    private List<SearchAgent> mListAgent = new ArrayList<>();
    private String search = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initContent();
        hideSoftKeyboard();
    }

    private void hideSoftKeyboard() {
        if(getCurrentFocus()!=null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

    private void initContent() {
        mEtSearch = (EditText) findViewById(R.id.et_search);
        mEtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                StringBuilder sb = new StringBuilder();
                for (char ch : editable.toString().toCharArray()) {
                    if (ch != ' ') {
                        sb.append(ch);
                    } else {
                        sb.append("+");
                    }
                }
                search = sb.toString();
                Log.d("denLog", search);
                new SearchTask().execute();
            }
        });

        mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mAdapter = new MyRecyclerAdapter(mListAgent, this);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        //mRecyclerView.setHasFixedSize(true);
    }

    private class SearchTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressBar.setVisibility(View.VISIBLE);
            mListAgent = null;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mListAgent = new ArrayList<>();
            mListAgent = ApiController.getInstance().getSearchAgent(search);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            mProgressBar.setVisibility(View.GONE);
            if (mListAgent != null) mAdapter.setAgentList(mListAgent);
            mAdapter.notifyDataSetChanged();
        }
    }
}
