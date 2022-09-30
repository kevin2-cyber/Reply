package io.materialstudies.reply.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import io.materialstudies.reply.R
import io.materialstudies.reply.databinding.ActivityMainBinding
import io.materialstudies.reply.ui.nav.BottomNavDrawerFragment
import io.materialstudies.reply.util.contentView
import kotlin.LazyThreadSafetyMode.*

class MainActivity : AppCompatActivity(),
                    Toolbar.OnMenuItemClickListener,
                    NavController.OnDestinationChangedListener{

    private val binding: ActivityMainBinding by contentView(R.layout.activity_main)
    private val bottomNavDrawer: BottomNavDrawerFragment by lazy(NONE) {
        supportFragmentManager.findFragmentById(R.id.bottom_nav_drawer) as BottomNavDrawerFragment
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        TODO("Not yet implemented")
    }

    override fun onDestinationChanged(
        controller: NavController,
        destination: NavDestination,
        arguments: Bundle?
    ) {
        TODO("Not yet implemented")
    }
}