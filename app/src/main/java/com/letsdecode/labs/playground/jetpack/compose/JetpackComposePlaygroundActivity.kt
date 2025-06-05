package com.letsdecode.labs.playground.jetpack.compose

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.letsdecode.labs.R
import com.letsdecode.labs.playground.jetpack.compose.intro.BasicExampleActivity
import com.letsdecode.labs.playground.jetpack.compose.intro.RecompositionActivity
import com.letsdecode.labs.playground.jetpack.compose.intro.StateExampleActivity
import com.letsdecode.labs.playground.jetpack.compose.sideeffects.SideEffectsSelectionActivity
import com.letsdecode.labs.playground.jetpack.compose.statehoisting.NeedOfStateHoistingActivity
import com.letsdecode.labs.playground.jetpack.compose.statehoisting.StateHoistingActivity

class JetpackComposePlaygroundActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_jetpack_compose_playground)
    }

    fun startBasicExampleActivity(view: View) {
        startActivity(Intent(this@JetpackComposePlaygroundActivity, BasicExampleActivity::class.java))
    }

    fun startStateExampleActivity(view: View) {
        startActivity(Intent(this@JetpackComposePlaygroundActivity, StateExampleActivity::class.java))
    }

    fun startRecompositionActivity(view: View) {
        startActivity(Intent(this@JetpackComposePlaygroundActivity, RecompositionActivity::class.java))
    }

    fun startSideEffectSelectionActivity(view: View) {
        startActivity(Intent(this@JetpackComposePlaygroundActivity, SideEffectsSelectionActivity::class.java))
    }

    fun startNeedOfStateHoistingActivity(view: View) {
        startActivity(Intent(this@JetpackComposePlaygroundActivity, NeedOfStateHoistingActivity::class.java))
    }
    fun startStateHoistingActivity(view: View) {
        startActivity(Intent(this@JetpackComposePlaygroundActivity, StateHoistingActivity::class.java))
    }
}