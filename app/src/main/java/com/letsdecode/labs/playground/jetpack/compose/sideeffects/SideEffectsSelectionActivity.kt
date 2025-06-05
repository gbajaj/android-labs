package com.letsdecode.labs.playground.jetpack.compose.sideeffects

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.letsdecode.labs.R

class SideEffectsSelectionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_side_effects_selection)
    }

    fun startWhatAreSideEffectsActivity(view: View) {
        startActivity(
            Intent(
                this@SideEffectsSelectionActivity,
                WhatAreSideEffectsActivity::class.java
            )
        )
    }

    fun startLaunchedEffectActivity(view: View) {
        startActivity(
            Intent(
                this@SideEffectsSelectionActivity,
                LaunchedEffectActivity::class.java
            )
        )
    }

    fun startRememberCoroutineScopeActivity(view: View) {
        startActivity(
            Intent(
                this@SideEffectsSelectionActivity,
                RememberCoroutineScopeActivity::class.java
            )
        )
    }

    fun startDisposableEffectActivity(view: View) {
        startActivity(
            Intent(
                this@SideEffectsSelectionActivity,
                DisposableEffectActivity::class.java
            )
        )
    }

    fun startSideEffectActivity(view: View) {
        startActivity(
            Intent(
                this@SideEffectsSelectionActivity,
                SideEffectActivity::class.java
            )
        )
    }

    fun startDerivedStateActivity(view: View) {
        startActivity(
            Intent(
                this@SideEffectsSelectionActivity,
                DerivedStateActivity::class.java
            )
        )
    }

    fun startRememberUpdatedStateActivity(view: View) {
        startActivity(
            Intent(
                this@SideEffectsSelectionActivity,
                RememberUpdatedStateActivity::class.java
            )
        )
    }

    fun startProduceStateActivity(view: View) {
        startActivity(
            Intent(
                this@SideEffectsSelectionActivity,
                ProduceStateActivity::class.java
            )
        )
    }

    fun startSnapshotFlowActivity(view: View) {
        startActivity(
            Intent(
                this@SideEffectsSelectionActivity,
                SnapshotFlowActivity::class.java
            )
        )
    }

}