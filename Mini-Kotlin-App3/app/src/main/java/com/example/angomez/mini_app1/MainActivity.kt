package com.example.angomez.mini_app1

import android.util.Log
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.transaction


class MainActivity : AppCompatActivity(), ContainerFragment.OnFragmentInteractionListener {

    lateinit var origFragment: ContainerFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        //for the onCreate save the instance state
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //creates the fragment to run only when saved instance state is null
        if(savedInstanceState == null) {
            origFragment = ContainerFragment.newInstance("param 1", "param 2")

            //utilizing fragment manager of android ktx to get rid of boiler plate code
            supportFragmentManager.transaction(allowStateLoss = true){
                replace(R.id.main_fragment, origFragment)
            }

            //creating fragment if I did not have the awesome android ktx lib
            /***supportFragmentManager
                    .beginTransaction()
                    .add(R.id.main_fragment, origFragment)
                    .addToBackStack(origFragment.toString())
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()*/
        }

    }

    //We would use this onSaveInstanceState for saving additional information not captured by each individual view
    /***public override fun onSaveInstanceState(outState: Bundle) {
        outState.putString("message", "This is my message to be reloaded")
        super.onSaveInstanceState(outState)
    }*/

    override fun onFragmentInteraction(uri: Uri) {
        Log.d("example", "Fragment Creation!")
    }

}


