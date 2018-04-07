package com.example.salmanmapkar.demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.github.barteksc.pdfviewer.PDFView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class timetable extends AppCompatActivity {

    int imgCode , imgCd;
    ListView mlistv;
    TextView textview;
    int count , month;
    ArrayAdapter<CharSequence> adapter_sem;
    String[] names = {};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable);
        Calendar c = Calendar.getInstance();
        month = c.get(Calendar.MONTH);
        final Spinner spinner_sem = (Spinner)findViewById(R.id.spinner1);
        if(month > 0 && month <= 6)
        {
            adapter_sem = ArrayAdapter.createFromResource(this,R.array.semesters_even,android.R.layout.simple_spinner_item);
        }
        else if(month > 6 && month <= 12)
        {
            adapter_sem = ArrayAdapter.createFromResource(this,R.array.semesters_odd,android.R.layout.simple_spinner_item);
        }
        adapter_sem.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_sem.setAdapter(adapter_sem);
        final Spinner spinner_branch = (Spinner)findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapter_branch = ArrayAdapter.createFromResource(this,R.array.branch,android.R.layout.simple_spinner_item);
        adapter_branch.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_branch.setAdapter(adapter_branch);
        mlistv = (ListView) findViewById(R.id.listview);
        final CustomAdaptor CAdapter = new CustomAdaptor();
        spinner_sem.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, final int i, long l) {

                if (i == 0) {
                    spinner_branch.setVisibility(View.INVISIBLE);
                    count = 0;
                    mlistv.setAdapter(CAdapter);
                }

                if (i == 1)
                {
                    spinner_branch.setVisibility(View.INVISIBLE);
                    count = 9;
                    names = new String[]{"FE  div A", "FE  div B", "FE  div C", "FE  div D", "FE  div E", "FE  div F", "FE  div G", "FE  div H", "FE  div I"};
                    mlistv.setAdapter(CAdapter);
                    mlistv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                            Intent intent = new Intent(getApplicationContext(),DownloadActivity.class);
                            intent.putExtra("StandardName",names[pos]);
                            startActivity(intent);

                        }
                    });
                }


                if (i == 2) {
                    spinner_branch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            if(i==0) {
                                count=0;
                                mlistv.setAdapter(CAdapter);
                            }
                            if(i==1)
                            {
                                count=2;
                                names = new String[]{"SE Comps div A", "SE Comps div B"};
                                mlistv.setAdapter(CAdapter);
                                mlistv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                                        Intent intent = new Intent(getApplicationContext(),DownloadActivity.class);
                                        intent.putExtra("StandardName",names[pos]);
                                        startActivity(intent);
                                    }
                                });
                            }
                            if(i==2)
                            {
                                count=2;
                                names = new String[]{"SE IT div A", "SE IT div B"};
                                mlistv.setAdapter(CAdapter);
                                mlistv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                                        Intent intent = new Intent(getApplicationContext(),DownloadActivity.class);
                                        intent.putExtra("StandardName",names[pos]);
                                        startActivity(intent);
                                    }
                                });
                            }

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                }

                if (i == 3)
                {
                    spinner_branch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            if(i==0) {
                                count=0;
                                mlistv.setAdapter(CAdapter);
                            }
                            if(i==1)
                            {
                                count=2;
                                names = new String[]{"TE Comps div A", "TE Comps div B"};
                                mlistv.setAdapter(CAdapter);
                                mlistv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                                        Intent intent = new Intent(getApplicationContext(),DownloadActivity.class);
                                        intent.putExtra("StandardName",names[pos]);
                                        startActivity(intent);
                                    }
                                });

                            }
                            if(i==2)
                            {
                                count=2;
                                names = new String[]{"TE IT div A", "TE IT div B"};
                                mlistv.setAdapter(CAdapter);
                                mlistv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                                        Intent intent = new Intent(getApplicationContext(),DownloadActivity.class);
                                        intent.putExtra("StandardName",names[pos]);
                                        startActivity(intent);
                                    }
                                });
                            }

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });

                }

                if (i == 4) {
                    spinner_branch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            if(i==0) {
                                count=0;
                                mlistv.setAdapter(CAdapter);
                            }
                            if(i==1)
                            {
                                count=2;
                                names = new String[]{"FY Comps div A", "FY Comps div B"};
                                mlistv.setAdapter(CAdapter);
                                mlistv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                                        Intent intent = new Intent(getApplicationContext(),DownloadActivity.class);
                                        intent.putExtra("StandardName",names[pos]);
                                        startActivity(intent);
                                    }
                                });
                            }
                            if(i==2)
                            {
                                count=2;
                                names = new String[]{"FY IT div A", "FY IT div B"};
                                mlistv.setAdapter(CAdapter);
                                mlistv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                                        Intent intent = new Intent(getApplicationContext(),DownloadActivity.class);
                                        intent.putExtra("StandardName",names[pos]);
                                        startActivity(intent);
                                    }
                                });
                            }

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                }

                if(i>1)
                    spinner_branch.setVisibility(View.VISIBLE);
                else
                    spinner_branch.setVisibility(View.INVISIBLE);
            }


            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
    class CustomAdaptor extends BaseAdapter
    {

        @Override
        public int getCount() {
            return count;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = getLayoutInflater().inflate(R.layout.timetable_layout,null);
            textview = (TextView)view.findViewById(R.id.textv);
            textview.setText(names[i]);
            return view;
        }
    }
}
