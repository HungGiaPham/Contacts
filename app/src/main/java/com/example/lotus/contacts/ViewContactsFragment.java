package com.example.lotus.contacts;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethod;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;

public class ViewContactsFragment extends Fragment {
    private static final String TAG = "ViewContactsFragment";

    private  static final int STANDARD_APPBAR=0;
    private  static final int SEARCH_APPBAR=1;
    private int mAppBarState;

    private AppBarLayout viewContactsBar,searchBar;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment,container,false);

        viewContactsBar=(AppBarLayout)view.findViewById(R.id.toolbar);
        searchBar=(AppBarLayout)view.findViewById(R.id.searchToolbar);

        setAppBarState(STANDARD_APPBAR);
        FloatingActionButton fab=(FloatingActionButton) view.findViewById(R.id.fabAddContact);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        ImageView ivSearchContact=(ImageView) view.findViewById(R.id.ivSearchIcon);
        ivSearchContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toogleToolBarState();
            }
        });
        ImageView ivBackArrow=(ImageView) view.findViewById(R.id.ivBackArrow);
        ivBackArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toogleToolBarState();
            }
        });
        return view;
    }

    private void toogleToolBarState() {
        if(mAppBarState ==STANDARD_APPBAR)
        {
            setAppBarState(SEARCH_APPBAR);
        }
        else {
            setAppBarState(STANDARD_APPBAR);
        }
    }

    private void setAppBarState(int state) {
        mAppBarState=state;
        if(mAppBarState==STANDARD_APPBAR)
        {
            searchBar.setVisibility(View.GONE);
            viewContactsBar.setVisibility(View.VISIBLE);
            View view=getView();
            InputMethodManager imm=(InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            try {
                imm.hideSoftInputFromWindow(view.getWindowToken(),0);
            }catch(NullPointerException e){

        }
        }
        else if(mAppBarState==SEARCH_APPBAR)
        {
          viewContactsBar.setVisibility(View.GONE);
          searchBar.setVisibility(View.VISIBLE);

          InputMethodManager imm=(InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
          imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,0);

        }
    }

    @Override
    public void onResume() {
        super.onResume();
        setAppBarState(STANDARD_APPBAR);
    }
}
