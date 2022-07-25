package hr.algebra.foodie

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import hr.algebra.foodie.frame_work.applyAnimation
import hr.algebra.foodie.frame_work.getBooleanPreference
import hr.algebra.foodie.frame_work.isOnline
import hr.algebra.foodie.frame_work.startActivity
import kotlinx.android.synthetic.main.activity_splash_screen.*

private const val DELAY : Long = 6000
const val DATA_IMPORTED  = "hr.algebra.nasa.data_imported"

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_splash_screen)

        startAnimations()
        redirect()
    }


    private fun startAnimations() {
        ivLogo.applyAnimation(R.anim.slide)
    }

    private fun redirect() {

        if (getBooleanPreference(DATA_IMPORTED)) {
            Handler(Looper.getMainLooper()).postDelayed(
                {startActivity<HostActivity>()},
                DELAY
            )
        } else {
            if (isOnline()) {
                // start service
                Intent(this, FoodieService::class.java).apply {
                    FoodieService.enqueueWork(this@SplashScreenActivity, this)
                }
            } else {
                Toast.makeText(this, getString(R.string.please_connect_to_the_internet), Toast.LENGTH_SHORT).show()
                finish()
            }
        }

    }
}