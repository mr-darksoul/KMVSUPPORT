package com.example.mrdarksoul.kmvsupport;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GetTokenResult;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public static final int RC_SIGN_IN = 1;
    private FirebaseDatabase mFirebaseDatabase; // entry object
    private DatabaseReference mArticleDatabaseReference; // reference specific object
    private DatabaseReference mMembersRefrence;
    private ChildEventListener mChildEventListener;
    private ArticleAdapter mArticleAdapter;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    ListView mArticleListView;
    TextView mArticleTextView;
    final static List<Article> mArticleEntries = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase =  FirebaseDatabase.getInstance();
        mArticleDatabaseReference = mFirebaseDatabase.getReference().child("articles");
        mMembersRefrence = mFirebaseDatabase.getReference().child("members");
        mArticleListView = (ListView) findViewById(R.id.list_article);

        mArticleAdapter = new ArticleAdapter(this, R.layout.single_article_list, mArticleEntries);
        mArticleListView.setAdapter(mArticleAdapter);
        //attachDatabaseReadListner();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser currentUser= firebaseAuth.getCurrentUser();
                if(currentUser != null)
                {
                    attachDatabaseReadListner();
                    storeID();
                }
                else
                {
                   onSignedOutCleanup();
                    startActivityForResult(
                            AuthUI.getInstance()
                                    .createSignInIntentBuilder()
                                    .setIsSmartLockEnabled(false)
                                    .setProviders(
                                            AuthUI.EMAIL_PROVIDER,
                                            AuthUI.GOOGLE_PROVIDER)
                                    .build(),
                            RC_SIGN_IN);

                }
            }
        };

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    //set priority 10 for admin ,5 for editor,1 for writer and -5 for pending writer
    public void storeID()
    {
        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
        mMembersRefrence.child("uID").child(currentFirebaseUser.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            if(!dataSnapshot.exists())
            {
                FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
                Members members = new Members(currentFirebaseUser.getUid(),2);
                mMembersRefrence.push().setValue(members);

            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    });



    }
    @Override
    protected void onResume() {
        super.onResume();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(mAuthStateListener != null)
            mFirebaseAuth.removeAuthStateListener(mAuthStateListener);
        detachDatabaseReadListner();
        mArticleAdapter.clear();
    }
    private void detachDatabaseReadListner()
    {
        if(mChildEventListener!=null)
            mArticleDatabaseReference.removeEventListener(mChildEventListener);
        mChildEventListener = null;
    }
    private void onSignedOutCleanup(){
        mArticleAdapter.clear();
        detachDatabaseReadListner();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            if (requestCode == RESULT_OK) {
                Toast.makeText(this, "Signed In", Toast.LENGTH_SHORT).show();
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Signed in cancelled!", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }
    private void attachDatabaseReadListner()
        {
            if(mChildEventListener==null)
                mChildEventListener = new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        //called whenever child added
                        //Log.v("onChildAdded",dataSnapshot.getValue().toString());

                            Article resultArticles = dataSnapshot.getValue(Article.class);
                            mArticleAdapter.add(resultArticles);
                        mArticleAdapter.notifyDataSetChanged();
                     }


                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                        //context is changed
                    }
                    @Override
                    public void onChildRemoved(DataSnapshot dataSnapshot) {
                    }
                    @Override
                    public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.d("DATABASE ERROR", databaseError.getMessage());
                    }
                };
            mArticleDatabaseReference.addChildEventListener(mChildEventListener);
        }



            /*mArticleDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Article article  = dataSnapshot.getValue(Article.class) ; // DATAsnap shot has excat same field as databse and casitng in to object

                    mArticleAdapter.add(article);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });*/


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_sign_out) {
            AuthUI.getInstance().signOut(this);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
