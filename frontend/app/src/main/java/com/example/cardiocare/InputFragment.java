package com.example.cardiocare;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class InputFragment extends Fragment {

    public InputFragment() {
        // Required empty public constructor
    }

    public static InputFragment newInstance(String param1, String param2) {
        InputFragment fragment = new InputFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getActivity().setTitle("Input Details ");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_prifile, container, false);

        // Find the WebView by its unique ID
        WebView webView = view.findViewById(R.id.webView);

        webView.loadUrl("https://www.google.com/search?rlz=1C1CHZN_enIN1025IN1026&sxsrf=APwXEdepjpqrEDLRQVT_a2qiaLFZGsvZUA:1688036931527&q=explain+the+variables+in+the+dataset+of+the+heart+disease+dataset&tbm=vid&sa=X&ved=2ahUKEwifhK_Nq-j_AhVAVmwGHRRvC3kQ0pQJegQIThAB&biw=1536&bih=688&dpr=1.25#fpstate=ive&vld=cid:5e44d99b,vid:5OjZTHMvOGM");
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());

        return view;
    }
}