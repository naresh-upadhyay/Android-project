package com.naresh.kingupadhyay.mathsking;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

public class Questions extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RecyclerView recyclerView = (RecyclerView) inflater.inflate(
                R.layout.content_main, container, false);
        ContentAdapter adapter = new ContentAdapter(recyclerView.getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return recyclerView;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public Button algebra;
        public Button determinants;
        public Button matrices;
        public Button logarithmic;
        public Button series;
        public Button equation;
        public Button complex;
        public Button pnc;
        public Button binomial;
        public Button probability;
        public Button differential;
        public Button functions;
        public Button limit;
        public Button continuity;
        public Button differentiability;
        public Button differentiation;
        public Button monotonicity;
        public Button max_min;
        public Button tan_normal;
        public Button rate;
        public Button lagrange;
        public Button integral;
        public Button indefinite_inti;
        public Button definite_inti;
        public Button area;
        public Button differen_eqn;
        public Button trigonometry;
        public Button rato_identity;
        public Button trigo_eq;
        public Button inverse_trigo;
        public Button heigh_dist;
        public Button two_d;
        public Button straight_line;
        public Button pair_straight_line;
        public Button circle;
        public Button parabola;
        public Button ellipse;
        public Button hyperbola;
        public Button three_d;
        public Button vector;
        public RelativeLayout rl_algebra;
        public RelativeLayout rl_differential;
        public RelativeLayout rl_integral;
        public RelativeLayout rl_trigonometry;
        public RelativeLayout rl_twoD;
        public boolean help= true;

        public ViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.item_questions, parent, false));

            // Adding Snackbar to Action Button inside card
            rl_algebra=itemView.findViewById(R.id.child_layout);
            rl_algebra.setVisibility(View.GONE);
            algebra = (Button)itemView.findViewById(R.id.algebra);
            algebra.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_action_expand, 0);
            algebra.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    if(rl_algebra.getVisibility()==View.GONE){
                        rl_algebra.setVisibility(View.VISIBLE);
                        algebra.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_action_collapse, 0);
                        algebra.setCompoundDrawablePadding(10);
                        //algebra.setTextColor(Color.BLACK);
                    }else {
                        rl_algebra.setVisibility(View.GONE);
                        algebra.setCompoundDrawablesWithIntrinsicBounds(0, 0,R.drawable.ic_action_expand, 0);
                    }

                }
            });
            determinants = (Button)itemView.findViewById(R.id.determinants);
            determinants.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, Details.class);
                    intent.putExtra(Details.EXTRA_POSITION, getAdapterPosition());
                    context.startActivity(intent);
                }
            });
            matrices = (Button)itemView.findViewById(R.id.matrices);
            matrices.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Snackbar.make(v, "Action is pressed",
                            Snackbar.LENGTH_LONG).show();
                }
            });
            logarithmic = (Button)itemView.findViewById(R.id.logarithmic);
            logarithmic.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Snackbar.make(v, "Action is pressed",
                            Snackbar.LENGTH_LONG).show();
                }
            });
            series = (Button)itemView.findViewById(R.id.series);
            series.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Snackbar.make(v, "Action is pressed",
                            Snackbar.LENGTH_LONG).show();
                }
            });
            equation = (Button)itemView.findViewById(R.id.equation);
            equation.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    if(help){
                        help=false;
                    }else {
                    }

                }
            });
            complex = (Button)itemView.findViewById(R.id.matrices);
            complex.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Snackbar.make(v, "Action is pressed",
                            Snackbar.LENGTH_LONG).show();
                }
            });
            pnc = (Button)itemView.findViewById(R.id.logarithmic);
            pnc.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Snackbar.make(v, "Action is pressed",
                            Snackbar.LENGTH_LONG).show();
                }
            });
            binomial = (Button)itemView.findViewById(R.id.series);
            binomial.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Snackbar.make(v, "Action is pressed",
                            Snackbar.LENGTH_LONG).show();
                }
            });
            probability = (Button)itemView.findViewById(R.id.equation);
            probability.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    if(help){
                        help=false;
                    }else {
                    }

                }
            });

            rl_differential=itemView.findViewById(R.id.child_layout2);
            rl_differential.setVisibility(View.GONE);
            differential = (Button)itemView.findViewById(R.id.differential);
            differential.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_action_expand, 0);
            differential.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    if(rl_differential.getVisibility()==View.GONE){
                        rl_differential.setVisibility(View.VISIBLE);
                        differential.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_action_collapse, 0);
                        differential.setCompoundDrawablePadding(10);
                        //algebra.setTextColor(Color.BLACK);
                    }else {
                        rl_differential.setVisibility(View.GONE);
                        differential.setCompoundDrawablesWithIntrinsicBounds(0, 0,R.drawable.ic_action_expand, 0);
                    }

                }
            });
            functions = (Button)itemView.findViewById(R.id.functions);
            functions.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, Details.class);
                    intent.putExtra(Details.EXTRA_POSITION, getAdapterPosition());
                    context.startActivity(intent);
                }
            });
            limit= (Button)itemView.findViewById(R.id.limit);
            limit.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Snackbar.make(v, "Action is pressed",
                            Snackbar.LENGTH_LONG).show();
                }
            });
            continuity = (Button)itemView.findViewById(R.id.continuity);
            continuity.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Snackbar.make(v, "Action is pressed",
                            Snackbar.LENGTH_LONG).show();
                }
            });
            differentiability = (Button)itemView.findViewById(R.id.differentiability);
            differentiability.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Snackbar.make(v, "Action is pressed",
                            Snackbar.LENGTH_LONG).show();
                }
            });

            differentiation = (Button)itemView.findViewById(R.id.differentiation);
            differentiation.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, Details.class);
                    intent.putExtra(Details.EXTRA_POSITION, getAdapterPosition());
                    context.startActivity(intent);
                }
            });
            monotonicity= (Button)itemView.findViewById(R.id.monotonicity);
            monotonicity.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Snackbar.make(v, "Action is pressed",
                            Snackbar.LENGTH_LONG).show();
                }
            });
            max_min = (Button)itemView.findViewById(R.id.max_min);
            max_min.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Snackbar.make(v, "Action is pressed",
                            Snackbar.LENGTH_LONG).show();
                }
            });
            tan_normal = (Button)itemView.findViewById(R.id.tang_normal);
            tan_normal.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Snackbar.make(v, "Action is pressed",
                            Snackbar.LENGTH_LONG).show();
                }
            });

            rate = (Button)itemView.findViewById(R.id.rate);
            rate.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, Details.class);
                    intent.putExtra(Details.EXTRA_POSITION, getAdapterPosition());
                    context.startActivity(intent);
                }
            });
            lagrange= (Button)itemView.findViewById(R.id.lagrange);
            lagrange.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Snackbar.make(v, "Action is pressed",
                            Snackbar.LENGTH_LONG).show();
                }
            });

            rl_integral=itemView.findViewById(R.id.child_layout3);
            rl_integral.setVisibility(View.GONE);
            integral = (Button)itemView.findViewById(R.id.integral);
            integral.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_action_expand, 0);
            integral.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    if(rl_integral.getVisibility()==View.GONE){
                        rl_integral.setVisibility(View.VISIBLE);
                        integral.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_action_collapse, 0);
                        integral.setCompoundDrawablePadding(10);
                        //algebra.setTextColor(Color.BLACK);
                    }else {
                        rl_integral.setVisibility(View.GONE);
                        integral.setCompoundDrawablesWithIntrinsicBounds(0, 0,R.drawable.ic_action_expand, 0);
                    }

                }
            });
            indefinite_inti = (Button)itemView.findViewById(R.id.indefinite);
            indefinite_inti.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, Details.class);
                    intent.putExtra(Details.EXTRA_POSITION, getAdapterPosition());
                    context.startActivity(intent);
                }
            });
            definite_inti = (Button)itemView.findViewById(R.id.Definite);
            definite_inti.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Snackbar.make(v, "Definite is pressed",
                            Snackbar.LENGTH_LONG).show();
                }
            });
            area = (Button)itemView.findViewById(R.id.area);
            area.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Snackbar.make(v, "Action is pressed",
                            Snackbar.LENGTH_LONG).show();
                }
            });
            differen_eqn = (Button)itemView.findViewById(R.id.diff_eqn);
            differen_eqn.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Snackbar.make(v, "Action is pressed",
                            Snackbar.LENGTH_LONG).show();
                }
            });
            rl_trigonometry=itemView.findViewById(R.id.child_layout4);
            rl_trigonometry.setVisibility(View.GONE);
            trigonometry = (Button)itemView.findViewById(R.id.trigonometry);
            trigonometry.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_action_expand, 0);
            trigonometry.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    if(rl_trigonometry.getVisibility()==View.GONE){
                        rl_trigonometry.setVisibility(View.VISIBLE);
                        trigonometry.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_action_collapse, 0);
                        trigonometry.setCompoundDrawablePadding(10);
                        //algebra.setTextColor(Color.BLACK);
                    }else {
                        rl_trigonometry.setVisibility(View.GONE);
                        trigonometry.setCompoundDrawablesWithIntrinsicBounds(0, 0,R.drawable.ic_action_expand, 0);
                    }

                }
            });
            rato_identity = (Button)itemView.findViewById(R.id.ratio_identity);
            rato_identity.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, Details.class);
                    intent.putExtra(Details.EXTRA_POSITION, getAdapterPosition());
                    context.startActivity(intent);
                }
            });
            trigo_eq = (Button)itemView.findViewById(R.id.trigo_eqn);
            trigo_eq.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Snackbar.make(v, "Action is pressed",
                            Snackbar.LENGTH_LONG).show();
                }
            });
            inverse_trigo = (Button)itemView.findViewById(R.id.inverse_trigo);
            inverse_trigo.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Snackbar.make(v, "Action is pressed",
                            Snackbar.LENGTH_LONG).show();
                }
            });
            heigh_dist = (Button)itemView.findViewById(R.id.height_dist);
            heigh_dist.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Snackbar.make(v, "Action is pressed",
                            Snackbar.LENGTH_LONG).show();
                }
            });
            rl_twoD=itemView.findViewById(R.id.child_layout5);
            rl_twoD.setVisibility(View.GONE);
            two_d = (Button)itemView.findViewById(R.id.geometry2d);
            two_d.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_action_expand, 0);
            two_d.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    if(rl_twoD.getVisibility()==View.GONE){
                        rl_twoD.setVisibility(View.VISIBLE);
                        two_d.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_action_collapse, 0);
                        two_d.setCompoundDrawablePadding(10);
                        //algebra.setTextColor(Color.BLACK);
                    }else {
                        rl_twoD.setVisibility(View.GONE);
                        two_d.setCompoundDrawablesWithIntrinsicBounds(0, 0,R.drawable.ic_action_expand, 0);
                    }

                }
            });
            straight_line = (Button)itemView.findViewById(R.id.straight_line);
            straight_line.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, Details.class);
                    intent.putExtra(Details.EXTRA_POSITION, getAdapterPosition());
                    context.startActivity(intent);
                }
            });
            pair_straight_line = (Button)itemView.findViewById(R.id.pair_line);
            pair_straight_line.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Snackbar.make(v, "Action is pressed",
                            Snackbar.LENGTH_LONG).show();
                }
            });
            circle = (Button)itemView.findViewById(R.id.circle);
            circle.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Snackbar.make(v, "Action is pressed",
                            Snackbar.LENGTH_LONG).show();
                }
            });
            parabola = (Button)itemView.findViewById(R.id.parabola);
            series.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Snackbar.make(v, "Action is pressed",
                            Snackbar.LENGTH_LONG).show();
                }
            });
            ellipse = (Button)itemView.findViewById(R.id.ellipse);
            ellipse.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Snackbar.make(v, "Action is pressed",
                            Snackbar.LENGTH_LONG).show();
                }
            });
            hyperbola = (Button)itemView.findViewById(R.id.hyperbola);
            hyperbola.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Snackbar.make(v, "Action is pressed",
                            Snackbar.LENGTH_LONG).show();
                }
            });

           /* rl_algebra=itemView.findViewById(R.id.child_layout);
            rl_algebra.setVisibility(View.GONE);
            algebra = (Button)itemView.findViewById(R.id.algebra);
            algebra.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_action_expand, 0);
            algebra.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    if(rl_algebra.getVisibility()==View.GONE){
                        rl_algebra.setVisibility(View.VISIBLE);
                        algebra.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_action_collapse, 0);
                        algebra.setCompoundDrawablePadding(10);
                        //algebra.setTextColor(Color.BLACK);
                    }else {
                        rl_algebra.setVisibility(View.GONE);
                        algebra.setCompoundDrawablesWithIntrinsicBounds(0, 0,R.drawable.ic_action_expand, 0);
                    }

                }
            });*/
            three_d = (Button)itemView.findViewById(R.id.three_d);
            three_d.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, Details.class);
                    intent.putExtra(Details.EXTRA_POSITION, getAdapterPosition());
                    context.startActivity(intent);
                }
            });
            vector = (Button)itemView.findViewById(R.id.vector);
            vector.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Snackbar.make(v, "Action is pressed",
                            Snackbar.LENGTH_LONG).show();
                }
            });


        }
    }

    /**
     * Adapter to display recycler view.
     */
    public static class ContentAdapter extends RecyclerView.Adapter<ViewHolder> {
        // Set numbers of Card in RecyclerView.
        private static final int LENGTH = 1;

        public ContentAdapter(Context context) {
        }


        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()), parent);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
        }

        @Override
        public int getItemCount() {
            return LENGTH;
        }
    }
}

