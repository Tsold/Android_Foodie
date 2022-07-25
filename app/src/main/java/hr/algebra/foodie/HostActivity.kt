package hr.algebra.foodie

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView

class HostActivity : AppCompatActivity() {

    private var itemFragment: ItemFragment =ItemFragment()
    private var favoritesFragment: FavoritesFragment = FavoritesFragment()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_main)
        var bottomnav = findViewById<BottomNavigationView>(R.id.BottomNavMenu)
        onClickHandler(bottomnav)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frameLayout,itemFragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()


    }

    private fun onClickHandler(bottomnav: BottomNavigationView) {

        bottomnav.setOnNavigationItemSelectedListener { item ->

            when(item.itemId){
                R.id.home -> {
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frameLayout,itemFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                }
                R.id.favorite -> {
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frameLayout,favoritesFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                }
            }

            true
        }
    }


}
