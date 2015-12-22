package com.appgestor.domidomi.mockedFragments;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.appgestor.domidomi.Activities.ActProductAdd;
import com.appgestor.domidomi.Activities.DetailsActivity;
import com.appgestor.domidomi.Adapters.AdapterComentario;
import com.appgestor.domidomi.Adapters.AdapterSedes;
import com.appgestor.domidomi.Adapters.ExpandableListAdapter;
import com.appgestor.domidomi.Adapters.ExpandableListDataPump;
import com.appgestor.domidomi.Entities.CompaniaList;
import com.appgestor.domidomi.Entities.InformacioCompania;
import com.appgestor.domidomi.Entities.ListComentarios;
import com.appgestor.domidomi.Entities.MasterItem2;
import com.appgestor.domidomi.Entities.MenuList;
import com.appgestor.domidomi.R;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.appgestor.domidomi.Entities.Producto.setProductoStatic;
import static com.appgestor.domidomi.Entities.Sede.getSedeStatic;


public class FragMenu extends BaseVolleyFragment {

    private ExpandableListView expandableListView;
    private ExpandableListAdapter expandableListAdapter;
    private ArrayList<String> expandableListTitle;
    private HashMap<String, List<String>> expandableListDetail;
    protected View rootView;
    private int operador;
    private MenuList menu = null;
    private TextView nombreEmpresa;
    private TextView nit;
    private TextView direccion;
    private TextView telefono;
    private TextView celular;
    private TableLayout tabla;
    private InformacioCompania infor;
    private AdapterSedes adapterSedes;
    private Button enviarComentario;
    private EditText mensaje;
    private SwipeMenuListView listView;

    public static FragMenu newInstance(Bundle param1) {
        FragMenu fragment = new FragMenu();
        fragment.setArguments(param1);
        return fragment;
    }

    public FragMenu() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            operador = getArguments().getInt("position");
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = null;
        switch (operador) {
            case 0:
                rootView = inflater.inflate(R.layout.fragment_menu, container, false);
                expandableListView = (ExpandableListView) rootView.findViewById(R.id.expandableListView);
                break;
            case 1:
                /*rootView = inflater.inflate(R.layout.fragment_informacion, container, false);
                nombreEmpresa = (TextView) rootView.findViewById(R.id.txtNombreEmpresa);
                nit = (TextView) rootView.findViewById(R.id.txtNit);
                direccion = (TextView) rootView.findViewById(R.id.textDireccion);
                telefono = (TextView) rootView.findViewById(R.id.txtTelefono);
                celular = (TextView) rootView.findViewById(R.id.txtCelulat);
                tabla = (TableLayout) rootView.findViewById(R.id.myTable);*/
                break;
            case 2:
                /*rootView = inflater.inflate(R.layout.fragment_comentarios, container, false);
                enviarComentario = (Button) rootView.findViewById(R.id.enviarComentario);
                mensaje = (EditText) rootView.findViewById(R.id.editMensaje);
                listView = (SwipeMenuListView) rootView.findViewById(R.id.listView);*/
                break;
        }
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        switch (operador) {
            case 0:
                setExpandableListView();
                break;
            case 1:
                //cargaInformacionE();
                break;
            case 2:
                /*recuperarMensaje();
                enviarComentario.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (mensaje.getText().toString().length() > 0) {
                            enviarMensaje();
                        }
                    }
                });*/
                break;
        }
    }

    private void enviarMensaje() {
        String url = String.format("%1$s%2$s", getString(R.string.url_base),"comentario");
        StringRequest jsonRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response) {
                        parseJSONComent(response);
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        //onConnectionFailed(error.toString());
                        startActivity(new Intent(getActivity(), DetailsActivity.class).putExtra("STATE", "ERROR"));
                        getActivity().overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams(){
                Map<String, String>  params = new HashMap<String, String>();
                params.put("comentario", mensaje.getText().toString());
                //params.put("compania", String.valueOf(Empresas.getCodigoS().getCodigo()));

                return params;
            }
        };
        addToQueue(jsonRequest);
    }

    private void recuperarMensaje() {
        String url = String.format("%1$s%2$s", getString(R.string.url_base),"getComentarios");
        StringRequest jsonRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response) {
                        parseJSONComent(response);
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        //onConnectionFailed(error.toString());
                        startActivity(new Intent(getActivity(), DetailsActivity.class).putExtra("STATE", "ERROR"));
                        getActivity().overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams(){
                Map<String, String>  params = new HashMap<String, String>();
                //params.put("compania", String.valueOf(Empresas.getCodigoS().getCodigo()));

                return params;
            }
        };
        addToQueue(jsonRequest);
    }

    private void cargaInformacionE(){
        String url = String.format("%1$s%2$s", getString(R.string.url_base),"getInformation");
        StringRequest jsonRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>(){
                    @Override
                    public void onResponse(final String response) {
                        // response
                        new AsyncTask<String[], Long, Long>(){
                            @Override
                            protected Long doInBackground(String[]... params) {
                                parseJSON2(response);
                                return null;
                            }
                            protected void onPreExecute() {
                                //multiColumnList.setVisibility(View.GONE);
                            }
                            @Override
                            public void onProgressUpdate(Long... value) {

                            }
                            @Override
                            protected void onPostExecute(Long result){
                                nombreEmpresa.setText(infor.getNombre());
                                nit.setText(infor.getNit());
                                direccion.setText(String.format("Dir: %1$s", infor.getDireccion()));
                                telefono.setText(String.format("Tel: %1$s", infor.getTelefono()));
                                celular.setText(String.format("Cel: %1$s", infor.getCelular()));

                                if(infor.getHijos() != null){

                                    setAdapterSedes(infor.getHijos());

                                    //adapterSedes = new AdapterSedes(getActivity(), infor.getHijos());
                                    //sedes.setAdapter(adapterSedes);
                                }
                            }
                        }.execute();
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        //onConnectionFailed(error.toString());
                        startActivity(new Intent(getActivity(), DetailsActivity.class).putExtra("STATE", "ERROR"));
                        getActivity().overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams(){
                Map<String, String>  params = new HashMap<>();
                //params.put("codigo", String.valueOf(Empresas.getCodigoS().getCodigo()));
                return params;
            }
        };
        addToQueue(jsonRequest);
    }

    public void setAdapterSedes(ArrayList<MasterItem2> hijos){

        for (int i = 0; i < hijos.size(); i++){
            TableRow currentRow = new TableRow(getContext());
            TableLayout.LayoutParams params = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT);

            TextView currentText = new TextView(getContext());
            currentText.setText(hijos.get(i).getDescripcion());
            currentText.setTextSize(11);
            currentText.setTextColor(Color.BLACK);

            currentRow.setLayoutParams(params);
            currentRow.addView(currentText);

            tabla.addView(currentRow);
        }

    }

    private void setExpandableListView() {
        // response
        new AsyncTask<String[], Long, Long>(){
            @Override
            protected Long doInBackground(String[]... params) {
                expandableListDetail = ExpandableListDataPump.getData(getSedeStatic().getMenus());
                expandableListTitle = new ArrayList<>(expandableListDetail.keySet());
                return null;
            }
            protected void onPreExecute() {
                //multiColumnList.setVisibility(View.GONE);
            }
            @Override
            public void onProgressUpdate(Long... value) {

            }
            @Override
            protected void onPostExecute(Long result){
                expandableListAdapter = new ExpandableListAdapter(getActivity(), expandableListTitle, expandableListDetail);
                expandableListView.setAdapter(expandableListAdapter);
                expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
                    @Override
                    public void onGroupExpand(int groupPosition) {
                        //Toast.makeText(getActivity(),expandableListTitle.get(groupPosition) + " List Expanded.", Toast.LENGTH_SHORT).show();
                    }
                });
                //Cerrar lista desplegable.
                expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
                    @Override
                    public void onGroupCollapse(int groupPosition) {
                        //Toast.makeText(getActivity(), expandableListTitle.get(groupPosition) + " List Collapsed.", Toast.LENGTH_SHORT).show();
                    }
                });
                //Clic en el elemento hijo.
                expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
                    @Override
                    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                        setProductoStatic(getSedeStatic().getMenus().get(groupPosition).getProductos().get(childPosition));

                        startActivity(new Intent(getActivity(), ActProductAdd.class));
                        getActivity().overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

                        return false;
                    }
                });

            }
        }.execute();
    }


    private void parseJSON(String json) {
        if (json != null && json.length() > 0) {
            try {
                Gson gson = new Gson();
                menu = gson.fromJson(json, MenuList.class);

            }catch (IllegalStateException ex) {
                ex.printStackTrace();
            }

        }else{
            startActivity(new Intent(getActivity(), DetailsActivity.class).putExtra("STATE", "EMPTY"));
            getActivity().overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        }

    }

    private void parseJSON2(String json) {

        if (json != null || json.length() > 0 && !json.equals("[null]")) {
            try {
                Gson gson = new Gson();
                CompaniaList comData = gson.fromJson(json, CompaniaList.class);
                for (int i = 0; i < comData.size(); i++) {
                    infor = new InformacioCompania(comData.get(i).getNombre(), comData.get(i).getNit(),
                            comData.get(i).getDireccion(), comData.get(i).getTelefono(), comData.get(i).getCelular(),
                            comData.get(i).getHijos());
                }

            }catch (IllegalStateException ex) {
                ex.printStackTrace();
            }
        }else{
            startActivity(new Intent(getActivity(), DetailsActivity.class).putExtra("STATE", "EMPTY"));
            getActivity().overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        }

    }

    private boolean parseJSONComent(String json) {
        if (json != null && json.length() > 0) {
            try {
                Gson gson = new Gson();
                ListComentarios comentarios = gson.fromJson(json, ListComentarios.class);
                AdapterComentario adapter = new AdapterComentario(getActivity(), comentarios);
                listView.setAdapter(adapter);
                mensaje.setText("");
                return true;
            }catch (IllegalStateException ex) {
                ex.printStackTrace();
            }
        }else{
            startActivity(new Intent(getActivity(), DetailsActivity.class).putExtra("STATE", "EMPTY"));
            getActivity().overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        }
        return false;

    }

}
