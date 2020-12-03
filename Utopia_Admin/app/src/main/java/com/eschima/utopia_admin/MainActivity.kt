package com.eschima.utopia_admin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentTransaction
import com.eschima.utopia_admin.Fragment.Account_Fragment
import com.eschima.utopia_admin.Fragment.Home_Fragment
import com.etebarian.meowbottomnavigation.MeowBottomNavigation
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object{
        private const val ID_home = 1
        private const val ID_account = 3

    }

    lateinit var homeFragment: Home_Fragment
    lateinit var accountFragment: Account_Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        homeFragment  = Home_Fragment()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fram_layout,homeFragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()

        bottom_navigation.add(MeowBottomNavigation.Model(ID_account,R.mipmap.account))
        bottom_navigation.add(MeowBottomNavigation.Model(ID_home,R.mipmap.home))

        bottom_navigation.show(ID_home,true)


        bottom_navigation.setOnClickMenuListener {
            when (it.id){
                ID_home -> move("home")
                ID_account -> move("account")
            }
        }

    }


    private fun move(type : String){

        if(type.equals("home")){

            homeFragment  = Home_Fragment()
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fram_layout,homeFragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit()

        }
        else if(type.equals("account")){
            accountFragment = Account_Fragment()
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fram_layout,accountFragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit()

        }

    }
}
